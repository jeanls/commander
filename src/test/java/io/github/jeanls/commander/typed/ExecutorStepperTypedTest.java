package io.github.jeanls.commander.typed;

import io.github.jeanls.commander.commands.MultiplyCommandTyped;
import io.github.jeanls.commander.commands.SumCommandTyped;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExecutorStepperTypedTest {

    @Test
    void successTest() {
        ExecutorStepperTypedSample executorStepperTypedSample = new ExecutorStepperTypedSample(new SumCommandTyped(), new MultiplyCommandTyped());
        Integer result = executorStepperTypedSample.doProcess(0);
        assertEquals(1000, result);
    }

    @Test
    void rollbackTest() {
        ExecutorStepperTypedSampleWithRollback executorStepperTypedSample = new ExecutorStepperTypedSampleWithRollback(new SumCommandTyped(), new MultiplyCommandTyped());
        Integer result = executorStepperTypedSample.doProcess(0);
        assertEquals(0, result);
    }
}

class ExecutorStepperTypedSample extends ExecutorStepperTyped<Integer> {

    public ExecutorStepperTypedSample(SumCommandTyped sumCommandTyped, MultiplyCommandTyped multiplyCommandTyped) {
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
    public Integer then(ExecutionResponseTyped<Integer> response) {
        if (!response.isSuccessful()) {
            response.rollback();
        }
        return response.getPayload();
    }
}

class ExecutorStepperTypedSampleWithRollback extends ExecutorStepperTyped<Integer> {

    public ExecutorStepperTypedSampleWithRollback(SumCommandTyped sumCommandTyped, MultiplyCommandTyped multiplyCommandTyped) {
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
    public Integer then(ExecutionResponseTyped<Integer> response) {
        response.rollback();
        return response.getPayload();
    }
}

