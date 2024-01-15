package com.mmd.expression.lambda;

@FunctionalInterface
public interface Operation<T> {
    @FunctionalMethod
    T operate(T value1, T value2);
}
