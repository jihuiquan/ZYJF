package com.dynamic.foundations.core.lang;

/**
 * Closure
 *
 * @author snowway
 * @since 2/24/11
 */
public interface Closure<T> {

    class VetoException extends RuntimeException {
        private static final long serialVersionUID = 7887659760614542342L;
    }

    class Execution {
        public static void veto() {
            throw new VetoException();
        }
    }

    void execute(T result);
}
