/*
 *      Copyright 2001-2004 Fraunhofer Gesellschaft, Munich, Germany, for its 
 *      Fraunhofer Institute Computer Architecture and Software Technology
 *      (FIRST), Berlin, Germany
 *      
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */


package org.tmjee.miniwiki.radeox.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.tmjee.miniwiki.radeox.api.engine.IncludeRenderEngine;
import org.tmjee.miniwiki.radeox.api.engine.RenderEngine;
import org.tmjee.miniwiki.radeox.api.engine.context.InitialRenderContext;
import org.tmjee.miniwiki.radeox.filter.context.FilterContext;
import org.tmjee.miniwiki.radeox.filter.regex.RegexTokenFilter;
import org.tmjee.miniwiki.radeox.regex.MatchResult;
import org.tmjee.miniwiki.radeox.macro.Macro;
import org.tmjee.miniwiki.radeox.macro.MacroRepository;
import org.tmjee.miniwiki.radeox.macro.Repository;
import org.tmjee.miniwiki.radeox.macro.parameter.MacroParameter;
import org.tmjee.miniwiki.radeox.util.StringBufferWriter;

import java.io.Writer;

/*
 * Class that finds snippets (macros) like
 * {link:neotis|http://www.neotis.de} ---> <a href="....>
 * {!neotis} -> include neotis object, e.g. a wiki page
 * <p/>
 * Macros can built with a start and an end, e.g.
 * {code}
 *     ...
 * {code}
 * <p/>
 * Example :-
 * <table>
 * <tr>
 *   <td>
 *      Example of Macro
 *   </td>
 *   <td>
 *      Description
 *   </td>
 * </tr>
 * <tr>
 *   <td>
 *      {link:neotis|http://www.neotis.de}
 *   </td>
 *   <td>
 *      <a href="....>
 *   </td>
 * </tr>
 * <tr>
 *   <td>
 *      {!neotis}
 *   </td>
 *   <td>
 *      include neotis object, e.g. a wiki page
 *   </td>
 * </tr>
 * <p/>
 * Syntax :-
 * {<macro name>:<macro param 1>|<macro param 1>} <content> {<macro name>}
 *    or
 * {<macro name>:<macro param name 1>=<macro param value 1>|<macro param name 2>=<macro param value 2>} <content> {<macro name>}
 *
 * @author stephan
 * @team sonicteam
 * @version $Id$
 */

public class MacroFilter extends RegexTokenFilter {
  private static Log log = LogFactory.getLog(MacroFilter.class);


  // Map of known macros with name and macro object
  private MacroRepository macros;

  public MacroFilter() {
    // optimized by Jeffrey E.F. Friedl
    super("\\{([^:}]+)(?::([^\\}]*))?\\}(.*?)\\{\\1\\}", SINGLELINE);
    addRegex("\\{([^:}]+)(?::([^\\}]*))?\\}", "", MULTILINE);
 }

  public void setInitialContext(InitialRenderContext context) {
    macros = MacroRepository.getInstance();
    macros.setInitialContext(context);
  }

  protected Repository getMacroRepository() {
    return macros;
  }

  public void handleMatch(StringBuffer buffer, MatchResult result, FilterContext context) {
    String command = result.group(1);

    if (command != null) {
      // {$peng} are variables not macros.
      if (!command.startsWith("$")) {
        MacroParameter mParams = context.getMacroParameter();
//        System.err.println("count="+result.groups());
//        System.err.println("1: "+result.group(1));
//        System.err.println("2: "+result.group(2));
        switch(result.groups()) {
          case 3:
            mParams.setContent(result.group(3));
            mParams.setContentStart(result.beginOffset(3));
            mParams.setContentEnd(result.endOffset(3));
          case 2:
            mParams.setParams(result.group(2));
            // Still left from ORO
//          case 2: System.out.println(result.group(1));
//          case 1: System.out.println(result.group(0));
        }
        mParams.setStart(result.beginOffset(0));
        mParams.setEnd(result.endOffset(0));

        // @DANGER: recursive calls may replace macros in included source code
        try {
          if (getMacroRepository().containsKey(command)) {
            Macro macro = (Macro) getMacroRepository().get(command);
            // recursively filter macros within macros
            if (null != mParams.getContent()) {
              mParams.setContent(filter(mParams.getContent(), context));
            }
            StringBufferWriter writer = new StringBufferWriter(new StringBuffer());
            macro.execute(writer, mParams);
            log.debug(macro.getClass()+"->"+writer.toString());

            String r = filter(writer.getBuffer().toString(), context);
            buffer.append(r);


          } else if (command.startsWith("!")) {
            // @TODO including of other snips
            RenderEngine engine = context.getRenderContext().getRenderEngine();
            if (engine instanceof IncludeRenderEngine) {
              String include = ((IncludeRenderEngine) engine).include(command.substring(1));
              if (null != include) {
                // Filter paramFilter = new ParamFilter(mParams);
                // included = paramFilter.filter(included, null);
                buffer.append(include);
              } else {
                buffer.append(command.substring(1) + " not found.");
              }
            }
            return;
          } else {
            buffer.append(result.group(0));
            return;
          }
        } catch (IllegalArgumentException e) {
          buffer.append("<div class=\"error\">" + command + ": " + e.getMessage() + "</div>");
        } catch (Throwable e) {
          log.warn("MacroFilter: unable to format macro: " + result.group(1), e);
          buffer.append("<div class=\"error\">" + command + ": " + e.getMessage() + "</div>");
          return;
        }
      } else {
        buffer.append("<");
        buffer.append(command.substring(1));
        buffer.append(">");
      }
    } else {
      buffer.append(result.group(0));
    }
  }
}
