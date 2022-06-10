package io.github.jeanls.commander.typed;

import io.github.jeanls.commander.RetryOptions;

public interface CommanderTyped<I> {

    I doProcess(final I input);

    default boolean canProcess(final I input) {
        return true;
    }

    default I rollback(final I input) {
        return input;
    }

    default RetryOptions retryConfig() {
        return new RetryOptions(1, 0);
    }
}
