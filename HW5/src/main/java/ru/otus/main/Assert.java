package ru.otus.main;

/**
 *
 */
public class Assert {

    /**
     * @param message
     * @param value
     */
    static public void assertTrue(String message, boolean value) {
        if (!value) {
            fail(message);
        }
    }

    /**
     * @param value
     */
    static public void assertTrue(boolean value) {
        assertTrue(null, value);
    }

    /**
     * @param message
     * @param value
     */
    static public void assertFalse(String message, boolean value) {
        if (value) {
            fail(message);
        }
    }

    /**
     * @param value
     */
    static public void assertFalse(boolean value) {
        assertFalse(null, value);
    }

    /**
     * @param message
     * @param object
     */
    static public void assertNotNull(String message, Object object) {
        if (object == null) {
            fail(message);
        }
    }

    /**
     * @param object
     */
    static public void assertNotNull(Object object) {
        assertNotNull(null, object);
    }

    /**
     * @param message
     */
    static public void fail(String message) {
        if (message == null) {
            throw new AssertionError();
        }
        throw new AssertionError(message);
    }
}