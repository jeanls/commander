package io.github.jeanls.commander.nontyped;

import io.github.jeanls.commander.exceptions.KeyAlreadyExistsException;
import io.github.jeanls.commander.exceptions.KeyNotNullException;

import java.util.HashMap;
import java.util.Map;

public class Context {

    private final Map<String, Object> data;
    private static final String KEY_ALREADY_EXISTS_MSG = "The key [%s] already exists.";

    public Context() {
        this.data = new HashMap<>();
    }

    public <T> void putf(final String key, final T o) {
        if (key == null) {
            throw new KeyNotNullException("The key cannot be null.");
        }
        data.put(key, o);
    }

    public <T> void putf(final T o) {
        final String key = o.getClass().getName();
        this.putf(key, o);
    }

    public <T> void put(final String key, final T o) {
        if (key == null) {
            throw new KeyNotNullException("The key cannot be null.");
        }
        if(data.containsKey(key)) {
            throw new KeyAlreadyExistsException(String.format(KEY_ALREADY_EXISTS_MSG, key));
        }
        data.put(key, o);
    }

    public <T> void put(final T o) {
        final String key = o.getClass().getName();
        this.put(key, o);
    }

    public void clear(final String key) {
        data.remove(key);
    }

    public <T> void clear(final Class<T> clazz) {
        data.remove(clazz.getName());
    }

    public <T> T get(final String key, final Class<T> clazz) {
        if (!data.containsKey(key)) {
            return null;
        }
        return clazz.cast(data.get(key));
    }

    public <T> T get(final Class<T> clazz) {
        final String key = clazz.getName();
        if (!data.containsKey(key)) {
            return null;
        }
        return clazz.cast(data.get(key));
    }
}
