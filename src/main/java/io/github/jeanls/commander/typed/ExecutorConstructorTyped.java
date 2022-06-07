package io.github.jeanls.commander.typed;

import io.github.jeanls.commander.enums.ExecutorStatus;

public class ExecutorConstructorTyped<T> {

    private CommanderTyped<T> on;
    private CommanderTypedSuccess<T> onSuccess;
    private CommanderTypedError<T> onError;
    private ExecutorStatus executorStatus;

    public ExecutorConstructorTyped() {
        this.executorStatus = ExecutorStatus.PENDING;
    }

    public ExecutorConstructorTyped<T> on(final CommanderTyped<T> commander) {
        this.on = commander;
        return this;
    }

    public ExecutorConstructorTyped<T> onSuccess(final CommanderTypedSuccess<T> commander) {
        this.onSuccess = commander;
        return this;
    }

    public ExecutorConstructorTyped<T> onError(final CommanderTypedError<T> commander) {
        this.onError = commander;
        return this;
    }

    public CommanderTyped<T> getOn() {
        return on;
    }

    public CommanderTypedSuccess<T> getOnSuccess() {
        return onSuccess;
    }

    public CommanderTypedError<T> getOnError() {
        return onError;
    }

    public ExecutorStatus getExecutorStatus() {
        return executorStatus;
    }

    public void setExecutorStatus(ExecutorStatus executorStatus) {
        this.executorStatus = executorStatus;
    }
}
