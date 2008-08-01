package org.tmjee.miniwiki.core.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class Main {

    private static final Log LOG = LogFactory.getLog(Main.class);

    public static void main(String[] args) throws Exception {
        System.out.println(
            Main.class.getResourceAsStream("/testing_db.properties")
        );
    }
}
