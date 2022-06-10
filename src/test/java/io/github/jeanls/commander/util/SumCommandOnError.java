package io.github.jeanls.commander.util;

import io.github.jeanls.commander.typed.CommanderTyped;
import io.github.jeanls.commander.typed.CommanderTypedError;

public class SumCommandOnError implements CommanderTypedError<Integer> {

    @Override
    public void onError(Throwable cause, Integer input, CommanderTyped<Integer> instance) {

    }
}
