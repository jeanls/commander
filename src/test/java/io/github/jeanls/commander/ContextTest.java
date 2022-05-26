package io.github.jeanls.commander;

import io.github.jeanls.commander.exceptions.KeyAlreadyExistsException;
import io.github.jeanls.commander.exceptions.KeyNotNullException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContextTest {

    @Test
    void putfWithKeyParamTest() {
        final Context context = new Context();
        context.put("a", 10);
        context.putf("a", 20);
        assertEquals(20, context.get("a", Integer.class));
    }

    @Test
    void putfWithKeyParamKeyNullTest() {
        final Context context = new Context();
        assertThrows(KeyNotNullException.class, () -> context.putf(null, 20));
    }

    @Test
    void putfWithoutKeyTest() {
        final Context context = new Context();
        context.putf(10);
        context.putf(50);
        assertEquals(50, context.get(Integer.class));
    }

    @Test
    void putWithKeyTest() {
        final Context context = new Context();
        context.put("a", 10);
        assertEquals(10, context.get("a", Integer.class));
    }

    @Test
    void putWithKeyAndKeyIsNullTest() {
        final Context context = new Context();
        assertThrows(KeyNotNullException.class, () -> context.put(null, 20));
    }

    @Test
    void putWithKeyAndKeyAlreadyExistsTest() {
        final Context context = new Context();
        context.put("a", 10);
        assertThrows(KeyAlreadyExistsException.class, () -> context.put("a", 20));
    }

    @Test
    void putWithoutKeyTest() {
        final Context context = new Context();
        context.put(10);
        assertEquals(10, context.get(Integer.class));
    }

    @Test
    void clearWithKeyTest() {
        final Context context = new Context();
        context.put("a", 10);
        assertEquals(10, context.get("a", Integer.class));
        context.clear("a");
        assertNull(context.get("a", Integer.class));
    }

    @Test
    void clearWithoutKeyTest() {
        final Context context = new Context();
        context.put(10);
        assertEquals(10, context.get(Integer.class));
        context.clear(Integer.class);
        assertNull(context.get(Integer.class));
    }
}
