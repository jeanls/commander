package io.github.jeanls.commander.typed;

public interface CommanderTyped<I> {

    I doProcess(final I input);

    default boolean canProcess(final I input) {
        return true;
    }

    default I rollback(final I input) {
        return input;
    }
}
