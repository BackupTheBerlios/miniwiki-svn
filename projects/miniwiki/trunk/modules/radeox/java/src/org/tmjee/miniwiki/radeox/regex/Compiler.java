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


package org.tmjee.miniwiki.radeox.regex;

/*
 * Class that compiles regular expressions to patterns
 *
 * @author stephan
 * @team sonicteam
 * @version $Id$
 */

public abstract class Compiler {
  /**
   * Create a new Compiler object depending on the used implementation
   *
   * @return Compiler object with the used implementation
   */
  public static Compiler create() {
    return new JdkCompiler();
  }

  /**
   * Whether the compiler should create multiline patterns
   * or single line patterns.
   *
   * @param multiline True if the pattern is multiline, otherwise false
   */
  public abstract void setMultiline(boolean multiline);

  /**
   * Compile a String regular expression to a regex pattern
   *
   * @param regex String representation of a regular expression
   * @return Compiled regular expression
   */
  public abstract Pattern compile(String regex);
}