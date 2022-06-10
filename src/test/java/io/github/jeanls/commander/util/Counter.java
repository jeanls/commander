package io.github.jeanls.commander.util;

public class Counter {

    private static Counter instance;

    private int val = 0;

    private Counter() {

    }

    public static Counter getInstance() {
        if (instance == null) {
            return new Counter();
        }
        return instance;
    }

    public void count() {
        val = val + 1;
    }

    public int getVal() {
        return val;
    }
}
