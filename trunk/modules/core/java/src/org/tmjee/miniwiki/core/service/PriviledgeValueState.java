package org.tmjee.miniwiki.core.service;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public enum PriviledgeValueState {
    
    ON,
    OFF;

    public static PriviledgeValueState state(String string) {
        if (ON.equals(string)) {
            return ON;
        }
        if (OFF.equals(string)) {
            return OFF;
        }
        return null;
    }
}
