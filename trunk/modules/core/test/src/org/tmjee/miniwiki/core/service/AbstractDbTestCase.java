package org.tmjee.miniwiki.core.service;

import junit.framework.TestCase;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public abstract class AbstractDbTestCase extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
        _setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        _tearDown();
    }


    protected void _setUp() {

    }

    protected void _tearDown() {

    }
}