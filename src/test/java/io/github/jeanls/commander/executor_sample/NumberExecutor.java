package io.github.jeanls.commander.executor_sample;

import io.github.jeanls.commander.Context;
import io.github.jeanls.commander.Executor;
import io.github.jeanls.commander.commands.MultiplyCommand;
import io.github.jeanls.commander.commands.SumCommand;

public class NumberExecutor extends Executor {

    @Override
    public void orders(final Context context) {
        SumCommand sumCommand = new SumCommand();
        MultiplyCommand multiplyCommand = new MultiplyCommand();

        start(context)
                .add(sumCommand)
                .add(multiplyCommand)
                .run();
    }
}
