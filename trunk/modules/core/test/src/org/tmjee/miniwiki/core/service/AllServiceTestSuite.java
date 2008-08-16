package org.tmjee.miniwiki.core.service;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.Test;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class AllServiceTestSuite extends TestCase {
    public AllServiceTestSuite(String name) {
        super(name);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite();

        suite.addTestSuite(UserManagementServiceTest.class);

        return suite;
    }

}
