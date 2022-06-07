package io.github.jeanls.commander.commands;

import io.github.jeanls.commander.nontyped.Commander;
import io.github.jeanls.commander.nontyped.Context;

public class MultiplyCommand implements Commander {

    @Override
    public void doProcess(final Context context) {
        Integer result = context.get("sum", Integer.class);
        context.put("result", result * 10);
    }

    @Override
    public boolean canProcess(Context context) {
        return true;
    }
}
