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

import junit.framework.Test;
import junit.framework.TestSuite;
import org.tmjee.miniwiki.radeox.filter.KeyFilter;

public class KeyFilterTest extends FilterTestSupport {
  protected void setUp() throws Exception {
    filter = new KeyFilter();
    super.setUp();
  }

  public static Test suite() {
    return new TestSuite(KeyFilterTest.class);
  }

  public void testAltKey() {
    assertEquals("<span class=\"key\">Alt-1</span>", filter.filter("Alt-1", context));
  }

  public void testCtrlKey() {
    assertEquals("<span class=\"key\">Ctrl-1</span>", filter.filter("Ctrl-1", context));
  }

  public void testShiftKey() {
    assertEquals("<span class=\"key\">Shift-1</span>", filter.filter("Shift-1", context));
  }

}
