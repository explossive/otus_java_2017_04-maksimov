package ru.otus.main;

/**
 *
 */
public class Assert {

    static public void assertTrue(String message, boolean value) {
        if (!value) {
            fail(message);
        }
    }

    static public void assertTrue(boolean value) {
        assertTrue(null, value);
    }

    static public void assertFalse(String message, boolean value) {
        if (value) {
            fail(message);
        }
    }

    static public void assertFalse(boolean value) {
        assertFalse(null, value);
    }

    static public void assertNotNull(String message, Object object) {
        if (object == null) {
            fail(message);
        }
    }

    static public void assertNotNull(Object object) {
        assertNotNull(null, object);
    }

    static public void fail(String message) {
        if (message == null) {
            throw new AssertionError();
        }
        throw new AssertionError(message);
    }
}