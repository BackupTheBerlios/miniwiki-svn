package org.tmjee.miniwiki.client.utils;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class MutableInteger {

    private int integer;

    public MutableInteger(int integer) {
        this.integer = integer;
    }

    public void inc(int amount) {
        integer = integer + amount;
    }

    public void inc() {
        integer = integer + 1;
    }

    public int getValue() {
        return integer;
    }

}
