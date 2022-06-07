package io.github.jeanls.commander.typed;

import io.github.jeanls.commander.enums.ExecutorStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public abstract class ExecutorStepperTyped<T> {

    private List<ExecutorConstructorTyped<T>> list = new ArrayList<>();

    public abstract T doProcess(T payload);

    @SafeVarargs
    public final ExecutorStepperTyped<T> add(UnaryOperator<ExecutorConstructorTyped<T>>... unaryOperators) {
        if (unaryOperators == null) {
            return this;
        }

        for (UnaryOperator<ExecutorConstructorTyped<T>> unaryOperator : unaryOperators) {
            list.add(unaryOperator.apply(new ExecutorConstructorTyped<>()));
        }

        return this;
    }

    public T run(T payload) {
        for (final ExecutorConstructorTyped<T> executor : list) {
            try {
                if (executor.getOn().canProcess(payload)) {
                    payload = executor.getOn().doProcess(payload);
                }
                if (executor.getOnSuccess() != null) {
                    executor.getOnSuccess().onSuccess(payload);
                }
                executor.setExecutorStatus(ExecutorStatus.SUCCESS);
            } catch (Exception e) {
                executor.setExecutorStatus(ExecutorStatus.ERROR);
                if (executor.getOnError() != null) {
                    executor.getOnError().onError(e, payload, executor.getOn());
                }
                this.then(new ExecutionResponseTyped<>(list, false, payload));
                list = new ArrayList<>();
                throw e;
            }
        }
        payload = this.then(new ExecutionResponseTyped<>(list, true, payload));
        list = new ArrayList<>();
        return payload;
    }

    public abstract T then(final ExecutionResponseTyped<T> response);
}
