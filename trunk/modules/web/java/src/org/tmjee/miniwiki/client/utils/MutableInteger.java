package org.tmjee.miniwiki.client.utils;

/**
 * Created by IntelliJ IDEA.
 * User: 1269870
 * Date: Jun 6, 2008
 * Time: 11:55:49 AM
 * To change this template use File | Settings | File Templates.
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
