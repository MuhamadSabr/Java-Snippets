package com.mmd.bigdecimal;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        double policyAmount = 1_000_000;
        int    beneficiaries = 3;
        float percentageFloat = 1.0f /beneficiaries;
        double percentageDouble= 1.0 /beneficiaries;

        System.out.printf("Payout float: %,.2f%n", percentageFloat*policyAmount);
        System.out.printf("Payout double: %,.2f%n", percentageDouble*policyAmount);

        double totalUsingFloat = policyAmount - ((policyAmount*percentageFloat) * beneficiaries);
        double totalUsingDouble= policyAmount - ((policyAmount*percentageDouble) * beneficiaries);

        System.out.printf("totalUsingFloat: %,.2f%n", totalUsingFloat);
        System.out.printf("totalUsingDouble: %,.2f%n", totalUsingDouble);
        System.out.println();

//        double[] doubles= {15.456, 8, 10000.000001, .123};//The double constructor can be unpredictable, either use BigDecimal.valueOf()
//        Arrays.setAll(bds, ui-> BigDecimal.valueOf(doubles[ui]));//  or, use the  String constructor to create BigDecimals
//        //valueOf converts the double passed to it, to a String using the DoubleWrapper's String method,then calls the String constructor
//        //This is more predictable but is not with no problem.
//
//        System.out.printf("%-14s %-15s %-8s %s%n", "Value", "Unscaled Value", "Scale", "Precision");
//        for(BigDecimal bd : bds){
//            System.out.printf("%-15s %-15d %-8d %d %n",
//                    bd, bd.unscaledValue(), bd.scale(), bd.precision());
//        }

        System.out.println("-".repeat(50));

        BigDecimal bd1 = new BigDecimal("1.2345689038493457213432345");
//        BigDecimal bd2 = new BigDecimal(1.2345689038493457213432345);//Will give u more than u bargained for
        BigDecimal bd2 = BigDecimal.valueOf(1.2345689038493457213432345);//Double has a scale of 16, so u get only 16 decimals n the rest
                                                                        //is cut.
//
//        System.out.printf("%-35s %-35s %-8s %s%n", "Value", "Unscaled Value", "Scale", "Precision");
//            System.out.printf("%-30s %-30d %-8d %d %n",
//                    bd1, bd1.unscaledValue(), bd1.scale(), bd1.precision());
//        System.out.printf("%-30s %-30d %-8d %d %n",
//                bd2, bd2.unscaledValue(), bd2.scale(), bd2.precision());



        String[] tests= {"15.456", "8", "10000.000001", ".123"};
        BigDecimal[] bds = new BigDecimal[tests.length];
        Arrays.setAll(bds, ui-> new BigDecimal(tests[ui]));

        //Rounding is necessary, otherwise u r gonna get ArithmeticException
        //Here java tries to use default rounding which is not to round at all. But in the case of three scale tha least significant num
        //won't just be dropped.
        System.out.printf("%-14s %-15s %-8s %s%n", "Value", "Unscaled Value", "Scale", "Precision");
        for(BigDecimal bd : bds){
            System.out.printf("%-15s %-15d %-8d %d %n",
                    bd, bd.unscaledValue(), bd.scale(), bd.precision());
            bd=bd.setScale(2, RoundingMode.HALF_UP);
            System.out.printf("%-15s %-15d %-8d %d %n",
                    bd, bd.unscaledValue(), bd.scale(), bd.precision());
        }
        System.out.println("-".repeat(50));

        BigDecimal policyPayout = new BigDecimal("1000000.00");
        System.out.printf("%-15s %-15d %-8d %d %n",
                policyPayout, policyPayout.unscaledValue(), policyPayout.scale(), policyPayout.precision());

//        BigDecimal percent = BigDecimal.ONE.divide(BigDecimal.valueOf(beneficiaries));//Non-terminating decimal expansion. no exact
//        BigDecimal percent = BigDecimal.ONE.divide(BigDecimal.valueOf(beneficiaries),              // representable decimal result
//                                                    MathContext.DECIMAL32);//MathContext.UNLIMITED is the default used.
        BigDecimal percent = BigDecimal.ONE.divide(BigDecimal.valueOf(beneficiaries),
        new MathContext(60, RoundingMode.UP));//U can create ur own MathContext(customPrecision,RoundingMode(optional))
        System.out.println(percent);

        BigDecimal chequeAmount = policyPayout.multiply(percent);//The resulting scale is the 1st operand's scale+2nd operand's scale
        chequeAmount = chequeAmount.setScale(2, RoundingMode.HALF_EVEN);
        System.out.printf("%.2f%n",chequeAmount);

        System.out.printf("%-15s %-15d %-8d %d %n",
                chequeAmount, chequeAmount.unscaledValue(), chequeAmount.scale(), chequeAmount.precision());

        BigDecimal totalChequesAmount = chequeAmount.multiply(BigDecimal.valueOf(beneficiaries));
        System.out.printf("Combined: %.2f%n", totalChequesAmount);
        System.out.println("Remaining: " + policyPayout.subtract(totalChequesAmount));

    }
}
