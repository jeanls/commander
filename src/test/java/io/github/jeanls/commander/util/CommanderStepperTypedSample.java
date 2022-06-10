package io.github.jeanls.commander.util;

import io.github.jeanls.commander.commands.MultiplyCommandTyped;
import io.github.jeanls.commander.commands.SumCommandTyped;
import io.github.jeanls.commander.typed.CommanderStepperResponseTyped;
import io.github.jeanls.commander.typed.CommanderStepperTyped;

public class CommanderStepperTypedSample extends CommanderStepperTyped<Integer> {

    public CommanderStepperTypedSample(SumCommandTyped sumCommandTyped, MultiplyCommandTyped multiplyCommandTyped, SumCommandOnSuccess sumCommandOnSuccess, SumCommandOnError commandOnError) {
        this.sumCommandTyped = sumCommandTyped;
        this.multiplyCommandTyped = multiplyCommandTyped;
        this.sumCommandOnSuccess = sumCommandOnSuccess;
        this.commandOnError = commandOnError;
    }

    private final SumCommandTyped sumCommandTyped;
    private final MultiplyCommandTyped multiplyCommandTyped;
    private final SumCommandOnSuccess sumCommandOnSuccess;
    private final SumCommandOnError commandOnError;

    @Override
    public Integer doProcess(Integer val) {
        return add(
                x -> x.on(sumCommandTyped)
                        .onSuccess(sumCommandOnSuccess)
                        .onError(commandOnError),
                x -> x.on(multiplyCommandTyped)
                        .onSuccess(input -> {

                        })
                        .onError((cause, input, instance) -> {
//
                        })
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
