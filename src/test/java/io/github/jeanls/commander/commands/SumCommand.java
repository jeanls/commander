package io.github.jeanls.commander.commands;

import io.github.jeanls.commander.nontyped.Commander;
import io.github.jeanls.commander.nontyped.Context;

public class SumCommand implements Commander {

    @Override
    public void doProcess(final Context context) {
        final Integer numberA = context.get("numberA", Integer.class);
        final Integer numberB = context.get("numberB", Integer.class);
        context.put("sum", numberA + numberB);
    }

    @Override
    public boolean canProcess(Context context) {
        return true;
    }
}
