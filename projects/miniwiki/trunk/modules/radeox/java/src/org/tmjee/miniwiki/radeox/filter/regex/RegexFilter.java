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


package org.tmjee.miniwiki.radeox.filter.regex;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.tmjee.miniwiki.radeox.filter.FilterSupport;
import org.tmjee.miniwiki.radeox.filter.context.FilterContext;

import java.util.ArrayList;
import java.util.List;

/*
 * Class that stores regular expressions, can be subclassed
 * for special Filters
 *
 * @author stephan
 * @team sonicteam
 * @version $Id$
 */

public abstract class RegexFilter extends FilterSupport {
  private static Log log = LogFactory.getLog(RegexFilter.class);

  protected List pattern = new ArrayList();
  protected List substitute = new ArrayList();

  public final static boolean SINGLELINE = false;
  public final static boolean MULTILINE = true;

  // TODO future use
  //private RegexService regexService;

  public RegexFilter() {
    super();
  }

  /**
   * create a new regular expression that takes input as multiple lines
   */
  public RegexFilter(String regex, String substitute) {
    this();
    addRegex(regex, substitute);
  }

  /**
   * create a new regular expression and set
   */
  public RegexFilter(String regex, String substitute, boolean multiline) {
    addRegex(regex, substitute, multiline);
  }

  public void clearRegex() {
    pattern.clear();
    substitute.clear();
  }
  public void addRegex(String regex, String substitute) {
    addRegex(regex, substitute, MULTILINE);
  }

  public void addRegex(String regex, String substitute, boolean multiline) {
    // compiler.compile(regex, (multiline ? Perl5Compiler.MULTILINE_MASK : Perl5Compiler.SINGLELINE_MASK) | Perl5Compiler.READ_ONLY_MASK));
    try {
      org.tmjee.miniwiki.radeox.regex.Compiler compiler = org.tmjee.miniwiki.radeox.regex.Compiler.create();
      compiler.setMultiline(multiline);
      this.pattern.add(compiler.compile(regex));
      // Pattern.DOTALL
      this.substitute.add(substitute);
    } catch (Exception e) {
      log.warn("bad pattern: " + regex + " -> " + substitute+" "+e);
    }
  }

  public abstract String filter(String input, FilterContext context);
}
