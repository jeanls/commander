package io.github.jeanls.commander;

import io.github.jeanls.commander.executor_sample.NumberExecutor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExecutorTest {

    @Test
    void successTest() {
        NumberExecutor numberExecutor = new NumberExecutor();
        final Context context = new Context();
        context.put("numberA", 10);
        context.put("numberB", 20);
        numberExecutor.exec(context);

        assertEquals(300, context.get("result", Integer.class));
    }
}
