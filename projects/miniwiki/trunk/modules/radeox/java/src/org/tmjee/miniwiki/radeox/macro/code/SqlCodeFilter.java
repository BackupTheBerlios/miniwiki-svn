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


package org.tmjee.miniwiki.radeox.macro.code;


/*
 * SqlCodeFilter colourizes SQL source code
 *
 * @author stephan
 * @team sonicteam
 * @version $Id$
 */

public class SqlCodeFilter extends DefaultRegexCodeFormatter implements SourceCodeFormatter {

  private static final String KEYWORDS =
      "\\b(SELECT|INSERT INTO|DELETE|UPDATE|CREATE|DROP|IF EXISTS|TABLE|TEMPORARY|WHERE|LEFT JOIN|ON|FROM|GROUP|BY|HAVING|AND|OR|ORDER)\\b";

  private static final String OBJECTS =
      "\\b(VARCHAR)" +
      "\\b";

  private static final String QUOTES =
      "\"(([^\"\\\\]|\\.)*)\"";

  private static final String COMMENTS = 
      "^#(.*)$";
 
  private static final String EMPTYLINES =
  	  "^\\s*$";

  public SqlCodeFilter() {
    super(QUOTES, "<span class='sql-quote'>\"$1\"</span>");
    addRegex(OBJECTS, "<span class='sql-object'>$1</span>");
    addRegex(KEYWORDS, "<span class='sql-keyword'>$1</span>");
    addRegex(COMMENTS, "<span class='sql-comment'>&#35;$1</span>");
    addRegex(EMPTYLINES, "");
  }

  public String getName() {
    return "sql";
  }
}
