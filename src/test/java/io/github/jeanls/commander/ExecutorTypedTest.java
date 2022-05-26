package io.github.jeanls.commander;

import io.github.jeanls.commander.executor_sample.NumberExecutorTyped;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExecutorTypedTest {

    @Test
    void successTest() {
        NumberExecutorTyped numberExecutorTyped = new NumberExecutorTyped();
        Integer result = numberExecutorTyped.exec(0);
        assertEquals(1000, result);
    }
}
