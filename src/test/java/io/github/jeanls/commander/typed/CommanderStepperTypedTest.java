package io.github.jeanls.commander.typed;

import io.github.jeanls.commander.commands.MultiplyCommandTyped;
import io.github.jeanls.commander.commands.SumCommandTyped;
import io.github.jeanls.commander.commands.TestFailAttemptsCommand;
import io.github.jeanls.commander.exceptions.ParameterNotNullException;
import io.github.jeanls.commander.util.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CommanderStepperTypedTest {

    @Test
    void successTest() {
        SumCommandTyped sumCommandTyped = spy(new SumCommandTyped());
        MultiplyCommandTyped multiplyCommandTyped = spy(new MultiplyCommandTyped());
        SumCommandOnSuccess sumCommandOnSuccess = spy(new SumCommandOnSuccess());
        SumCommandOnError sumCommandOnError = spy(new SumCommandOnError());

        CommanderStepperTypedSample executorStepperTypedSample = new CommanderStepperTypedSample(sumCommandTyped, multiplyCommandTyped, sumCommandOnSuccess, sumCommandOnError);
        Integer result = executorStepperTypedSample.doProcess(0);

        verify(sumCommandTyped, times(1)).canProcess(any());
        verify(sumCommandTyped, times(1)).doProcess(any());
        verify(sumCommandOnSuccess, times(1)).onSuccess(any());
        verify(sumCommandOnError, times(0)).onError(any(), any(), any());

        verify(multiplyCommandTyped, times(1)).canProcess(any());
        verify(multiplyCommandTyped, times(1)).doProcess(any());

        assertEquals(1000, result);
    }

    @Test
    void onErrorCallTest() {
        SumCommandTyped sumCommandTyped = spy(new SumCommandTyped());
        MultiplyCommandTyped multiplyCommandTyped = spy(new MultiplyCommandTyped());
        SumCommandOnSuccess sumCommandOnSuccess = spy(new SumCommandOnSuccess());
        SumCommandOnError sumCommandOnError = spy(new SumCommandOnError());

        Mockito.doThrow(RuntimeException.class).when(sumCommandTyped).doProcess(any());

        CommanderStepperTypedSample executor = new CommanderStepperTypedSample(sumCommandTyped, multiplyCommandTyped, sumCommandOnSuccess, sumCommandOnError);
        assertThrows(RuntimeException.class, () -> executor.doProcess(0));

        verify(sumCommandTyped, times(3)).canProcess(any());
        verify(sumCommandTyped, times(3)).doProcess(any());
        verify(sumCommandOnSuccess, times(0)).onSuccess(any());
        verify(sumCommandOnError, times(3)).onError(any(), any(), any());

        verify(multiplyCommandTyped, times(0)).canProcess(any());
        verify(multiplyCommandTyped, times(0)).doProcess(any());
    }

    @Test
    void rollbackTest() {
        CommanderStepperTypedSampleWithRollback executorStepperTypedSample = new CommanderStepperTypedSampleWithRollback(new SumCommandTyped(), new MultiplyCommandTyped());
        Integer result = executorStepperTypedSample.doProcess(0);
        assertEquals(0, result);
    }

    @Test
    void retryWithDelayTest() {
        SumCommandTyped sumCommandTyped = Mockito.spy(new SumCommandTyped());

        Mockito.doThrow(RuntimeException.class).when(sumCommandTyped).doProcess(any());

        CommanderStepperTypedSampleWithRollback executorStepperTypedSample = new CommanderStepperTypedSampleWithRollback(sumCommandTyped, new MultiplyCommandTyped());
        assertThrows(RuntimeException.class, () -> executorStepperTypedSample.doProcess(0));

        verify(sumCommandTyped, times(3)).doProcess(any());
    }

    @Test
    void addTestWithNullParams() {
        CommanderStepperWithNullParams executorStepperWithNullParams = new CommanderStepperWithNullParams();

        assertThrows(ParameterNotNullException.class, () -> executorStepperWithNullParams.doProcess(0));
    }

    @Test
    void successAfterAttemptsWithErrorTest() {
        TestFailAttemptsCommand testFailAttemptsCommand = spy(new TestFailAttemptsCommand());
        SumCommandOnSuccess sumCommandOnSuccess = spy(new SumCommandOnSuccess());
        SumCommandOnError sumCommandOnError = spy(new SumCommandOnError());

        CommanderStepperAttempts executorStepperAttempts = new CommanderStepperAttempts(testFailAttemptsCommand, sumCommandOnSuccess, sumCommandOnError);
        try {
            executorStepperAttempts.doProcess(0);
        } catch (Exception e) {

        }
        verify(testFailAttemptsCommand, times(3)).doProcess(any());
        verify(sumCommandOnSuccess, times(1)).onSuccess(any());
        verify(sumCommandOnError, times(2)).onError(any(), any(), any());
    }
}
