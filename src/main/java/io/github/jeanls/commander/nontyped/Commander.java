package io.github.jeanls.commander.nontyped;

public interface Commander {

    void doProcess(final Context context);

    default boolean canProcess(final Context context) {
        return true;
    }

    default void rollback() {

    }
}
