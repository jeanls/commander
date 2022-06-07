package io.github.jeanls.commander.typed;

public interface CommanderTypedError<I> {

    void onError(final Throwable cause, I input, CommanderTyped<I> instance);
}
