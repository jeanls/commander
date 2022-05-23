package io.github.jeanls.commander;

public interface Commander {

    void doProcess(final Context context);

    default boolean canProcess(final Context context) {
        return true;
    }
}
