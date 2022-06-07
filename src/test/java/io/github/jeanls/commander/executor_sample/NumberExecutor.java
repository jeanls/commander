package io.github.jeanls.commander.executor_sample;

import io.github.jeanls.commander.commands.MultiplyCommand;
import io.github.jeanls.commander.commands.SumCommand;
import io.github.jeanls.commander.nontyped.Context;
import io.github.jeanls.commander.nontyped.Executor;

public class NumberExecutor extends Executor {

    @Override
    public void exec(final Context context) {
        SumCommand sumCommand = new SumCommand();
        MultiplyCommand multiplyCommand = new MultiplyCommand();

        start(context)
                .add(sumCommand)
                .add(multiplyCommand)
                .run();
    }
}
