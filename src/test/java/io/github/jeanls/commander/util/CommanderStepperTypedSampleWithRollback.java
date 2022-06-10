package io.github.jeanls.commander.util;

import io.github.jeanls.commander.commands.MultiplyCommandTyped;
import io.github.jeanls.commander.commands.SumCommandTyped;
import io.github.jeanls.commander.typed.CommanderStepperResponseTyped;
import io.github.jeanls.commander.typed.CommanderStepperTyped;

public class CommanderStepperTypedSampleWithRollback extends CommanderStepperTyped<Integer> {

    public CommanderStepperTypedSampleWithRollback(SumCommandTyped sumCommandTyped, MultiplyCommandTyped multiplyCommandTyped) {
        this.sumCommandTyped = sumCommandTyped;
        this.multiplyCommandTyped = multiplyCommandTyped;
    }

    private final SumCommandTyped sumCommandTyped;
    private final MultiplyCommandTyped multiplyCommandTyped;

    @Override
    public Integer doProcess(Integer val) {
        return add(
                x -> x.on(sumCommandTyped),
                x -> x.on(multiplyCommandTyped)
                        .onSuccess(input -> {

                        })
                        .onError((cause, input, instance) -> {
//                            instance.rollback(input);
                        })
        ).run(val);
    }

    @Override
    public Integer then(CommanderStepperResponseTyped<Integer> response) {
        response.rollback();
        return response.getPayload();
    }
}

