package io.github.jeanls.commander.typed;

import java.util.ArrayList;
import java.util.List;

public abstract class ExecutorTyped<I> {

    private List<CommanderTyped<I>> commanders = new ArrayList<>();
    private I input;

    public abstract I exec(I input);

    public ExecutorTyped<I> add(final CommanderTyped<I> commander) {
        commanders.add(commander);
        return this;
    }

    protected ExecutorTyped<I> start(final I input){
        this.input = input;
        return this;
    }

    public I run() {
        try {
            I lastInput = input;
            for (final CommanderTyped<I> commander : commanders) {
                if (commander.canProcess(lastInput)) {
                    lastInput = commander.doProcess(lastInput);
                }
            }
            commanders = new ArrayList<>();
            return lastInput;
        } catch (final Exception e) {
            commanders = new ArrayList<>();
            throw e;
        }
    }
}
