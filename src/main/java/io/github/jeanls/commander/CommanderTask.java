package io.github.jeanls.commander;

import io.github.jeanls.commander.enums.ExecutorStatus;
import io.github.jeanls.commander.typed.CommanderStepperConstructorTyped;

public class CommanderTask<T> implements Task {

    private final CommanderStepperConstructorTyped<T> executor;
    private T payload;

    public CommanderTask(CommanderStepperConstructorTyped<T> executor, T payload) {
        this.executor = executor;
        this.payload = payload;
    }

    @Override
    public void run() {
        try {
            if (executor.getOn().canProcess(payload)) {
                payload = executor.getOn().doProcess(payload);
                if (executor.getOnSuccess() != null) {
                    executor.getOnSuccess().onSuccess(payload);
                }
            } else {
                executor.setExecutorStatus(ExecutorStatus.SKIPED);
                return;
            }
            executor.setExecutorStatus(ExecutorStatus.SUCCESS);
        } catch (Exception e) {
            executor.setExecutorStatus(ExecutorStatus.ERROR);
            throw e;
        }
    }

    public T getPayload() {
        return payload;
    }

    public ExecutorStatus getStatus() {
        return executor.getExecutorStatus();
    }
}
