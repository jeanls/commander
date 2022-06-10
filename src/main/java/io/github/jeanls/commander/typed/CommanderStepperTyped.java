package io.github.jeanls.commander.typed;

import io.github.jeanls.commander.CommanderTask;
import io.github.jeanls.commander.enums.ExecutorStatus;
import io.github.jeanls.commander.exceptions.DelayException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class CommanderStepperTyped<T> {

    private List<CommanderStepperConstructorTyped<T>> list = new ArrayList<>();
    private final Logger logger = Logger.getLogger("ExecutorStepperTyped");

    public abstract T doProcess(T payload);

    @SafeVarargs
    public final CommanderStepperTyped<T> add(UnaryOperator<CommanderStepperConstructorTyped<T>>... unaryOperators) {
        for (UnaryOperator<CommanderStepperConstructorTyped<T>> unaryOperator : unaryOperators) {
            list.add(unaryOperator.apply(new CommanderStepperConstructorTyped<>()));
        }

        return this;
    }

    public T run(T payload) {
        for (final CommanderStepperConstructorTyped<T> executor : list) {
            for (int x = 0; x < executor.getOn().retryConfig().getAttempts(); x++) {
                try {
                    final CommanderTask<T> commanderTask = new CommanderTask<>(executor, payload);
                    commanderTask.run();
                    if (commanderTask.getStatus() == ExecutorStatus.SUCCESS || commanderTask.getStatus() == ExecutorStatus.SKIPED) {
                        payload = commanderTask.getPayload();
                        break;
                    }
                } catch (Exception e) {
                    logger.log(Level.SEVERE, String.format("Error detected in execution [%s, %s].", x, executor.getOn().getClass().getName()), e);
                    if (executor.getOnError() != null) {
                        executor.getOnError().onError(e, payload, executor.getOn());
                    }
                    if (x == (executor.getOn().retryConfig().getAttempts() - 1)) {
                        this.then(new CommanderStepperResponseTyped<>(list, false, payload));
                        list = new ArrayList<>();
                        throw e;
                    }
                    delay(executor.getOn().retryConfig().getDelay());
                }
            }
        }
        payload = this.then(new CommanderStepperResponseTyped<>(list, true, payload));
        list = new ArrayList<>();
        return payload;
    }

    private void delay(long delay) {
        if (delay != 0) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new DelayException(e.getMessage(), e);
            }
        }
    }

    public abstract T then(final CommanderStepperResponseTyped<T> response);
}
