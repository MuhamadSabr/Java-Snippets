package com.mmd.gameconsole;

import java.util.function.Predicate;

sealed public interface SealedInterface permits BetterInterface, TestClass {
    abstract boolean testData(Predicate<String> lambda, String... strings);//final modifier is not allowed with interface methods.
                            //Also the abstract keyword is redundant.
}
