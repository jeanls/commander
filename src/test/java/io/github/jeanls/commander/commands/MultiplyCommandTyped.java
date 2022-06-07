package io.github.jeanls.commander.commands;

import io.github.jeanls.commander.typed.CommanderTyped;

public class MultiplyCommandTyped implements CommanderTyped<Integer> {

    @Override
    public Integer doProcess(Integer input) {
        return input * 100;
    }

    @Override
    public boolean canProcess(Integer input) {
        return true;
    }

    @Override
    public Integer rollback(Integer input) {
        return input / 100;
    }
}
