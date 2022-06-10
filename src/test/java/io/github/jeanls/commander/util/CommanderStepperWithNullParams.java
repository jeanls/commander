package io.github.jeanls.commander.util;

import io.github.jeanls.commander.typed.CommanderStepperResponseTyped;
import io.github.jeanls.commander.typed.CommanderStepperTyped;

public class CommanderStepperWithNullParams extends CommanderStepperTyped<Integer> {

    @Override
    public Integer doProcess(Integer val) {
        return add(
                x -> x.on(null)
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
