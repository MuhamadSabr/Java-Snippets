package com.mmd.gameconsole;

import java.util.function.Predicate;

final public class TestClass implements SealedInterface {
    @Override
    public boolean testData(Predicate<String> lambda, String... strings) {
        return false;
    }
}
