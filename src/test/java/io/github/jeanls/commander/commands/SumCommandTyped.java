package io.github.jeanls.commander.commands;

import io.github.jeanls.commander.CommanderTyped;

public class SumCommandTyped implements CommanderTyped<Integer> {

    @Override
    public Integer doProcess(Integer input) {
        return input + 10;
    }

    @Override
    public boolean canProcess(Integer input) {
        return true;
    }
}
