package io.github.jeanls.commander.executor_sample;

import io.github.jeanls.commander.commands.MultiplyCommandTyped;
import io.github.jeanls.commander.commands.SumCommandTyped;
import io.github.jeanls.commander.typed.ExecutorTyped;

public class NumberExecutorTyped extends ExecutorTyped<Integer> {

    @Override
    public Integer exec(Integer initalValue) {
        SumCommandTyped sumCommandTyped = new SumCommandTyped();
        MultiplyCommandTyped multiplyCommandTyped = new MultiplyCommandTyped();

        return start(initalValue)
                .add(sumCommandTyped)
                .add(multiplyCommandTyped)
                .run();
    }
}
