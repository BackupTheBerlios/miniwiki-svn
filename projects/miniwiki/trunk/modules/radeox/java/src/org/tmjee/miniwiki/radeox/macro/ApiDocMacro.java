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

package org.tmjee.miniwiki.radeox.macro;

import org.tmjee.miniwiki.radeox.macro.api.ApiDoc;
import org.tmjee.miniwiki.radeox.macro.parameter.MacroParameter;

import java.io.IOException;
import java.io.Writer;

/*
 * Lists all known API documentation repositories and
 * mappings
 *
 * @author stephan
 * @team sonicteam
 * @version $Id$
 */

public class ApiDocMacro extends BaseLocaleMacro {
  public String getLocaleKey() {
    return "macro.apidocs";
  }

  public void execute(Writer writer, MacroParameter params)
      throws IllegalArgumentException, IOException {
    ApiDoc apiDoc = ApiDoc.getInstance();
    apiDoc.appendTo(writer);
    return;
  }
}
