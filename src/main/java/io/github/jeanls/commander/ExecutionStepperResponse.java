package io.github.jeanls.commander;

import io.github.jeanls.commander.enums.ExecutorStatus;

public class ExecutionStepperResponse {

    private ExecutorStatus executorStatus;
    private Exception exception;

    public ExecutionStepperResponse(ExecutorStatus executorStatus) {
        this.executorStatus = executorStatus;
    }

    public ExecutionStepperResponse(ExecutorStatus executorStatus, Exception exception) {
        this.executorStatus = executorStatus;
        this.exception = exception;
    }

    public ExecutorStatus getExecutorStatus() {
        return executorStatus;
    }

    public void setExecutorStatus(ExecutorStatus executorStatus) {
        this.executorStatus = executorStatus;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
