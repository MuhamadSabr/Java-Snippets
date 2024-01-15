package com.mmd.constructors;

import java.time.LocalDateTime;

public enum Generations {//enums are final implicitly just as records.
    //  private com.mmd.constructors.Generations(); there is this implicit constructor. Even if you define ur constructor it has to be
    //  private no other access modifier is allowed.
    GEN_Z, //These are all public static final fields. with the same type of this enum in this case Generations
    MILLENNIAL(1984, 2002){
        {System.out.println("Instance initializer for Millennial");}
        static { System.out.println("Static initializer for Millennial");}
        public int sum(){
            return x+y;
        }
        private int x;
        private int y;
    }, //Each constant is a Generations class, public final static.
    GEN_X(1965, 1983),
    BABY_BOOMER(1946, 1964),
    SILENT_GENERATION(1927, 1945),
    GREATEST_GENERATION(1901, 1926);
                        //Implicit values[] n values() n valueOf(java.lang.String)
                        //and there seems to be an empty static{} initializer.
    private final int startYear;
    private final int endYear;

    Generations() {// Again if you define ur constructor then the default one is never created, u might want to create it explicitly.
        this(2003, LocalDateTime.now().getYear());
    }

    Generations(int startYear, int endYear) {
        this.endYear = endYear;
        this.startYear = startYear;
        System.out.println(this);
    }

    @Override
    public String toString() {
        return this.name() + ": " + startYear + " - " + endYear;
    }
}//At the end since constructors r private it means u can't create an instance of it.
