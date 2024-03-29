package com.dynamic.foundations.core.lang;

/**
 * Transformer
 *
 * @author snowway
 * @since 2/24/11
 */
public interface Transformer<F, T> {

    T transform(F input);
}
