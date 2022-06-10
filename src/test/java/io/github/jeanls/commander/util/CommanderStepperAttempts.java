package io.github.jeanls.commander.util;

import io.github.jeanls.commander.commands.TestFailAttemptsCommand;
import io.github.jeanls.commander.typed.CommanderStepperResponseTyped;
import io.github.jeanls.commander.typed.CommanderStepperTyped;

public class CommanderStepperAttempts extends CommanderStepperTyped<Integer> {

    public CommanderStepperAttempts(TestFailAttemptsCommand testFailAttemptsCommand, SumCommandOnSuccess sumCommandOnSuccess, SumCommandOnError sumCommandOnError) {
        this.testFailAttemptsCommand = testFailAttemptsCommand;
        this.sumCommandOnSuccess = sumCommandOnSuccess;
        this.commandOnError = sumCommandOnError;
    }

    private final TestFailAttemptsCommand testFailAttemptsCommand;
    private final SumCommandOnSuccess sumCommandOnSuccess;
    private final SumCommandOnError commandOnError;

    @Override
    public Integer doProcess(Integer val) {
        return add(
                x -> x.on(testFailAttemptsCommand)
                        .onSuccess(sumCommandOnSuccess)
                        .onError(commandOnError)
        ).run(val);
    }

    @Override
    public Integer then(CommanderStepperResponseTyped<Integer> response) {
        if (!response.isSuccessful()) {
            response.rollback();
        }
        return response.getPayload();
    }
}
