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

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.tmjee.miniwiki.radeox.macro.code.AllCodeMacroTests;

public class AllMacroTests extends TestCase {
  public AllMacroTests(String name) {
    super(name);
  }

  public static Test suite() {
    TestSuite s = new TestSuite();
    s.addTestSuite(ApiMacroTest.class);
    s.addTestSuite(ApiDocMacroTest.class);
    s.addTestSuite(AsinMacroTest.class);
    s.addTestSuite(FilePathMacroTest.class);
    s.addTestSuite(IsbnMacroTest.class);
    s.addTestSuite(LinkMacroTest.class);
    s.addTestSuite(ParamMacroTest.class);
    s.addTestSuite(TableMacroTest.class);
    s.addTestSuite(XrefMacroTest.class);
    s.addTestSuite(MailToMacroTest.class);
    s.addTestSuite(RfcMacroTest.class);
    s.addTestSuite(BaseMacroParameterTest.class);

    s.addTest(AllCodeMacroTests.suite());
    return s;
  }
}
