package io.github.jeanls.commander;

public interface CommanderTyped<I> {

    I doProcess(final I input);

    default boolean canProcess(final I input) {
        return true;
    }
}
