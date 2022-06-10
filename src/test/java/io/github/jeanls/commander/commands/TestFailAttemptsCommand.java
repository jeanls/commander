package io.github.jeanls.commander.commands;

import io.github.jeanls.commander.RetryOptions;
import io.github.jeanls.commander.typed.CommanderTyped;

public class TestFailAttemptsCommand implements CommanderTyped<Integer> {

    private int counter = 1;

    @Override
    public Integer doProcess(Integer input) {
        if (counter < 3) {
            counter++;
            throw new RuntimeException("Error");
        }
        return input;
    }

    @Override
    public boolean canProcess(Integer input) {
        return true;
    }

    @Override
    public RetryOptions retryConfig() {
        return new RetryOptions(3, 1);
    }
}
