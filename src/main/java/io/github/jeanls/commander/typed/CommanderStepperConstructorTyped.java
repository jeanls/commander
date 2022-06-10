package io.github.jeanls.commander.typed;

import io.github.jeanls.commander.enums.ExecutorStatus;
import io.github.jeanls.commander.exceptions.ParameterNotNullException;

public class CommanderStepperConstructorTyped<T> {

    private CommanderTyped<T> on;
    private CommanderTypedSuccess<T> onSuccess;
    private CommanderTypedError<T> onError;
    private ExecutorStatus executorStatus;

    public CommanderStepperConstructorTyped() {
        this.executorStatus = ExecutorStatus.PENDING;
    }

    public CommanderStepperConstructorTyped<T> on(final CommanderTyped<T> commander) {
        if (commander == null) {
            throw new ParameterNotNullException("the method add cannot accept null params.");
        }
        this.on = commander;
        return this;
    }

    public CommanderStepperConstructorTyped<T> onSuccess(final CommanderTypedSuccess<T> commander) {
        this.onSuccess = commander;
        return this;
    }

    public CommanderStepperConstructorTyped<T> onError(final CommanderTypedError<T> commander) {
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
