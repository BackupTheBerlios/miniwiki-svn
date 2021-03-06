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

import org.tmjee.miniwiki.radeox.api.engine.context.InitialRenderContext;
import org.tmjee.miniwiki.radeox.filter.context.FilterContext;
import org.tmjee.miniwiki.radeox.filter.regex.LocaleRegexTokenFilter;
import org.tmjee.miniwiki.radeox.regex.MatchResult;

import java.text.MessageFormat;

/*
 * Transforms header style lines into subsections. A header starts with a 1 for
 * first level headers and 1.1 for secend level headers. Headers are
 * numbered automatically
 * <p/>
 * Example :-
 * <table>
 * <tr>
 * <td>
 * 1 Test  or  = Test
 * </td>
 * <td>
 *  <h3 class="header-1">Test</h3>
 * </td>
 * </tr>
 * <tr>
 * <td>
 *   1 Test<br/>
 *   1.1 Banana<br/>
 *   or
 *   = Test<br/>
 *   == Banana<br/>
 * </td>
 * <td>
 *   <h3 class="header-1">Test</h3>
 *   <h3 class="header-1-1">Banana</h3>
 * </td>
 * </tr>
 * </table>
 *
 * @author leo
 * @team other
 * @version $Id$
 */

public class HeadingFilter extends LocaleRegexTokenFilter implements CacheFilter {
  private MessageFormat formatter;


  protected String getLocaleKey() {
    return "filter.heading";
  }

  public void handleMatch(StringBuffer buffer, MatchResult result, FilterContext context) {
    buffer.append(handleMatch(result, context));
  }

  public void setInitialContext(InitialRenderContext context) {
    super.setInitialContext(context);
    String outputTemplate = outputMessages.getString(getLocaleKey() + ".print");
    formatter = new MessageFormat("");
    formatter.applyPattern(outputTemplate);
  }

  public String handleMatch(MatchResult result, FilterContext context) {
    String match = result.group(1);
    match = match.replaceAll("\\.", "");
    match = match.replaceAll(".", "1-");
    match = match.substring(0, match.length() - 1);
    return formatter.format(new Object[]{match, result.group(3)});
  }
}
