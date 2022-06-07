package io.github.jeanls.commander.typed;

import io.github.jeanls.commander.enums.ExecutorStatus;

import java.util.Collections;
import java.util.List;

public class ExecutionResponseTyped<T> {

    private final List<ExecutorConstructorTyped<T>> list;
    private final boolean successful;
    private T payload;

    public ExecutionResponseTyped(List<ExecutorConstructorTyped<T>> list, boolean successful, T payload) {
        this.list = list;
        this.successful = successful;
        this.payload = payload;
    }

    public void rollback() {
        Collections.reverse(list);
        for (final ExecutorConstructorTyped<T> executor : list) {
            try {
                if (executor.getExecutorStatus() != ExecutorStatus.PENDING) {
                    payload = executor.getOn().rollback(payload);
                }
            } catch (Exception e) {
                if (executor.getOnError() != null) {
                    executor.getOnError().onError(e, payload, executor.getOn());
                }
                throw e;
            }
        }
    }

    public boolean isSuccessful() {
        return successful;
    }

    public T getPayload() {
        return payload;
    }
}
