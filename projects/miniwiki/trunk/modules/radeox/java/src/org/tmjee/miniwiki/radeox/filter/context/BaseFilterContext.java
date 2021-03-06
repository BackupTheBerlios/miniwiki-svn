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


package org.tmjee.miniwiki.radeox.filter.context;

import org.tmjee.miniwiki.radeox.api.engine.context.RenderContext;
import org.tmjee.miniwiki.radeox.macro.parameter.BaseMacroParameter;
import org.tmjee.miniwiki.radeox.macro.parameter.MacroParameter;


/**
 * Base impementation for FilterContext
 *
 * @author Stephan J. Schmidt
 * @version $Id$
 */

public class BaseFilterContext implements FilterContext {
  protected RenderContext context;

  public MacroParameter getMacroParameter() {
    return new BaseMacroParameter(context);
  }

  public void setRenderContext(RenderContext context) {
    this.context = context;
  }

  public RenderContext getRenderContext() {
    return context;
  }
}
