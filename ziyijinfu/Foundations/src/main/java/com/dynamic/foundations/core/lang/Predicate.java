package com.dynamic.foundations.core.lang;

/**
 * Predicate
 *
 * @author snowway
 * @since 2/24/11
 */
public interface Predicate<T> {

    Predicate TRUE = new Predicate() {
        @Override
        public boolean eval(Object input) {
            return true;
        }
    };

    boolean eval(T input);
}
