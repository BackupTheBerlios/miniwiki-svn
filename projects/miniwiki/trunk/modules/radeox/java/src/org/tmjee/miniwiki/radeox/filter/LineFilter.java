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

import org.tmjee.miniwiki.radeox.filter.regex.LocaleRegexReplaceFilter;

/*
 * LineFilter finds ---- in its input and transforms this
 * to &lt;hr/&gt;
 * <p/>
 * <table>
 * <tr>
 *  <td>
 *    ----
 *  </td>
 *  <td>
 *    &lt;hr class="line"/&gt;
 *  </td>
 * </tr>
 * <td>
 *   ---- ----
 * </td>
 * <td>
 *   &lt;hr class="line"/&gt;
 *   &lt;hr class="line"/&gt;
 * </td>
 * </tr>
 * </table>
 *
 * @author stephan
 * @author tmjee
 * @team sonicteam
 * @version $Id$
 */

public class LineFilter extends LocaleRegexReplaceFilter implements CacheFilter {
  protected String getLocaleKey() {
    return "filter.line";
  }
}