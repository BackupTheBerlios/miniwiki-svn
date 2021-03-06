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


package org.tmjee.miniwiki.radeox.example;

import org.tmjee.miniwiki.radeox.engine.context.BaseRenderContext;
import org.tmjee.miniwiki.radeox.api.engine.context.RenderContext;
import org.tmjee.miniwiki.radeox.api.engine.context.InitialRenderContext;
import org.tmjee.miniwiki.radeox.engine.context.BaseInitialRenderContext;
import org.tmjee.miniwiki.radeox.api.engine.RenderEngine;
import org.tmjee.miniwiki.radeox.engine.BaseRenderEngine;

import java.util.Locale;

/*
 * Example how to use BaseRenderEngine
 *
 * @author Stephan J. Schmidt
 * @version $Id$
 */

public class RenderEngineExample {
  public static void main(String[] args) {
    String test = "__SnipSnap__ {link:Radeox|http://radeox.org} ==Other Bold==";

    RenderContext context = new BaseRenderContext();
    RenderEngine engine = new BaseRenderEngine();
    System.out.println("Rendering with default:");
    System.out.println(engine.render(test, context));

    System.out.println("Rendering with alternative Wiki:");
    InitialRenderContext initialContext = new BaseInitialRenderContext();
    initialContext.set(RenderContext.INPUT_LOCALE, new Locale("otherwiki", ""));
    RenderEngine engineWithContext = new BaseRenderEngine(initialContext);
    System.out.println(engineWithContext.render(test, context));
  }
}
