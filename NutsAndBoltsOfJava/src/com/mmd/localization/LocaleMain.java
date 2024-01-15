package com.mmd.localization;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class LocaleMain {
    public static void main(String[] args) {
        Locale ar   = new Locale("ar");//a locale of just language.
        Locale arIq = new Locale("ar", "IQ"); //a locale of language n country.
        Locale.setDefault(Locale.US);
        System.out.println("Default Locale: " + Locale.getDefault());

        Locale arLb = new Locale.Builder().setLanguage("ar").setRegion("LB").build();
        System.out.println(arLb);

        var dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        for(Locale locale : List.of(Locale.US, ar, Locale.FRENCH, Locale.UK, arIq, Locale.CANADA, arLb)){
            System.out.println(locale.getDisplayName() + ": "+ locale.getDisplayName(locale)+"\n"+
                    LocalDateTime.now().format(dtf.withLocale(locale)));
            NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
            System.out.println(numberFormat.format(123456789.12345));
        }System.out.println("-".repeat(35));

        DateTimeFormatter wDayMonth = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");
        LocalDate may5th = LocalDate.of(2020, 5, 5);

        for(var locale : List.of(Locale.CANADA, Locale.CANADA_FRENCH, Locale.FRANCE, Locale.GERMANY,
                                Locale.TAIWAN, Locale.SIMPLIFIED_CHINESE, Locale.KOREA)){
            System.out.println(
                    locale.getDisplayName() +": " + locale.getDisplayName(locale)+"\n\t" +
                            may5th.format(wDayMonth.withLocale(locale))
            );
            System.out.printf(locale, "\t%1$tA, %1$tB %1$te, %1$tY %n", may5th);
            "".formatted(locale, "\t%1$tA, %1$tB %1$te, %1$tY%n", may5th);
            NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
            numberFormat.setMaximumFractionDigits(4);//By default is 3. Also NumberFormat is a mutable class.
            NumberFormat currency = NumberFormat.getCurrencyInstance(locale);
//            currency.getCurrency().getCurrencyCode();//.getCurrency on NumberFormat returns Currency instance.
            Currency localeCurrency = Currency.getInstance(locale);
            System.out.println(numberFormat.format(123456789.12345));
            System.out.println(currency.format(555.555)+" [" +
                    localeCurrency.getCurrencyCode()+ "]"+
                    localeCurrency.getDisplayName(locale)+"/"+
                    localeCurrency.getDisplayName());
        }//Remember to use Locale.COUNTRYCODE and not Locale.LANGUAGECODE. there is no currency connected to language anyway

        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.GERMANY);
        System.out.println("Enter the loan amount in " + scanner.locale()+" format");
        System.out.println(scanner.locale());//Shows u the already in use locale
        BigDecimal loan = scanner.nextBigDecimal();//U can type group n decimal separators in scanner.
        System.out.println("Your loan amount: " + loan);


    }
}
