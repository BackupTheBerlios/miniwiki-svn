package org.tmjee.miniwiki.core;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.Test;
import org.tmjee.miniwiki.core.service.AllServiceTestSuite;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class AllTestSuite extends TestCase {

    public AllTestSuite(String name) {
        super(name);
    }

    public static Test suite() {
        TestSuite test = new TestSuite();

        test.addTestSuite(AllServiceTestSuite.class);

        return test;
    }

}
