package com.mmd.gameconsole;

sealed public class SpecialAbstractClass permits FinalKid, SealedKid, Non_SealedKid,
                                                    SpecialAbstractClass.A{//If all subclasses are nested classes the permits clause is not
    //required though if u optionally included it u have to prefix the nested class with the outer class name. permits outerClass.nestedClass

    final class A extends SpecialAbstractClass{};

    {
        System.out.println("Special Abstract Class's instance initializer");
    }
}
