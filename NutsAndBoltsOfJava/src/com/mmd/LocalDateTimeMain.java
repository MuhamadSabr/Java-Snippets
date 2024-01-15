package com.mmd;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

public class LocalDateTimeMain {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println(today);

        LocalDate five5 = LocalDate.of(2022, 5, 5);//As u can see .of has two versions
        LocalDate may5th = LocalDate.of(2022, Month.MAY, 5);//the 2nd u can pass Month.month constant
        System.out.println(five5);                                  //instead of all 3 parameters being number.
        System.out.println(may5th);
     LocalDate todayYD=LocalDate.ofYearDay(2023,today.getDayOfYear());//U can use ofYearDay to construct a LocalDate
        System.out.println(todayYD);//of only the year n dayOfYear, as u can see u can get that using .getDayOfYear()

        LocalDate instanceParse = LocalDate.parse("2023-09-19");//U have to pass 4 digit, 2, n 2. no less, no more.
        System.out.println(instanceParse);

        System.out.println(instanceParse.getYear() + ", " + instanceParse.getDayOfYear());//.getMonth returns Month
        System.out.println(instanceParse.getMonthValue() + ", " + instanceParse.getDayOfMonth());// n not its value
        System.out.println(instanceParse.getDayOfWeek().getValue());//There is no getWeek not for month not for year, none.
        //Also getDayOfWeek returns DayOfWeek enum n not an int value, use .getValue on the return type to get its value

        System.out.println(todayYD.get(ChronoField.YEAR));//Accepts a TemporalField arg.
        System.out.println(todayYD.get(ChronoField.MONTH_OF_YEAR));//These can be used in addition of those above.
        System.out.println(todayYD.get(ChronoField.DAY_OF_MONTH));
        System.out.println(todayYD.get(ChronoField.DAY_OF_YEAR));


        System.out.println("Using .equals instead of isEqual: " +todayYD.equals(today));
        System.out.println(todayYD.isEqual(today));//isEqual, isAfter n isBefore take ChronoLocalDate
        System.out.println(todayYD.isLeapYear());
        System.out.println(todayYD.isAfter(today.minusDays(1)));
        System.out.println("Using compareTo instead of isAfter: "+todayYD.compareTo(today.minusDays(1)));
        System.out.println(todayYD.isBefore(today.plusDays(1)));
        System.out.println("Using compareTo instead of isBefore"+ todayYD.plusDays(1).compareTo(todayYD));

        System.out.println(todayYD.isSupported(ChronoField.MINUTE_OF_HOUR));//This version takes a TemporalField.
        System.out.println("ChronoUnit: " + todayYD.isSupported(ChronoUnit.HALF_DAYS));

        System.out.println(todayYD.withDayOfYear(365));//Takes int dayOfYear, modifies object, returns the rest untouched.
        System.out.println(todayYD.withDayOfMonth(30));
        System.out.println(todayYD.withYear(2000));
        System.out.println(todayYD.withMonth(1));
        System.out.println(todayYD.with(ChronoField.YEAR, 0));
        System.out.println(todayYD.with(ChronoField.DAY_OF_WEEK, 7));

        System.out.println(todayYD.plusDays(5));
        System.out.println("plusWeeks: "+todayYD.plusWeeks(5));
        System.out.println(todayYD.plusMonths(5));
        System.out.println(todayYD.plusYears(9999));
        System.out.println(todayYD.plus(12, ChronoUnit.DAYS));//(long AmountToAdd, TemporalUnit) this version

        System.out.println(todayYD.minusDays(-5));//minus a negative value will add the value.
        System.out.println(todayYD.minusMonths(-4));
        System.out.println(todayYD.minusWeeks(-1));
        System.out.println(todayYD.minusYears(-2));
        System.out.println(todayYD.minus(23, ChronoUnit.YEARS));//amntToSubtract, TemporalUnit.

        System.out.println("-".repeat(30));

        //Gives us a Stream of LDates. Takes a LDate Endexclusive. The arg passed must be less than the calling LDate
        var c = today.datesUntil(today.plusMonths(1))
                .min(Comparator.comparing(LocalDate::getMonthValue).reversed());
        System.out.println(c.orElseGet(LocalDate::now));
        System.out.println("-".repeat(30));

        today.datesUntil(today.plusYears(1),//This version takes a 2nd arg which is of type Period used as step
                Period.ofWeeks(10))
                .forEach(System.out::println);
        System.out.println("-".repeat(30));


        LocalTime localTime = LocalTime.of(21,2); //Stores hh as 0 to 23 inclusive
        System.out.println(localTime);
        localTime           = LocalTime.of(21, 3, 17);
        System.out.println(localTime);
        localTime           = LocalTime.of(21, 5, 43, 313245454);
        System.out.println(localTime);
        localTime           = LocalTime.parse("21:07:39.45454");
        System.out.println(localTime);
        localTime           = LocalTime.NOON;
        System.out.println(localTime);
        localTime           = LocalTime.MIDNIGHT;
        System.out.println(localTime);
        System.out.println(LocalTime.MIN);
        System.out.println(LocalTime.MAX);
        System.out.println(LocalTime.now().get(ChronoField.HOUR_OF_AMPM));//Returns hour between 1-12 with no PM or AM
        System.out.println("CLOCK: " + LocalTime.now().get(ChronoField.CLOCK_HOUR_OF_AMPM));
        System.out.println(LocalTime.now().get(ChronoField.AMPM_OF_DAY));//Before noon returns 0, after noon returns 1

        System.out.println(LocalTime.now().get(ChronoField.MICRO_OF_SECOND));
        System.out.println(LocalTime.now().getLong(ChronoField.MICRO_OF_DAY));//U have to use getLong for MICRO_OF_DAY

        System.out.println(LocalTime.now().get(ChronoField.NANO_OF_SECOND));
        System.out.println(LocalTime.now().getLong(ChronoField.NANO_OF_DAY));

        System.out.println(LocalTime.now().get(ChronoField.MINUTE_OF_HOUR));
        System.out.println(LocalTime.now().get(ChronoField.MINUTE_OF_DAY));

        System.out.println(LocalTime.now().get(ChronoField.HOUR_OF_DAY));
        System.out.println(LocalTime.now().get(ChronoField.CLOCK_HOUR_OF_DAY));

        System.out.println(LocalTime.now().range(ChronoField.HOUR_OF_DAY));//returns a ValueRange of supported values
        System.out.println(LocalTime.now().range(ChronoField.CLOCK_HOUR_OF_DAY));// 1 - 24 inclusive
        System.out.println(LocalTime.now().range(ChronoField.HOUR_OF_AMPM));    // 1 - 11 inclusive
        System.out.println(LocalTime.now().range(ChronoField.CLOCK_HOUR_OF_AMPM));//1 - 12 inclusive
        System.out.println(LocalTime.now().range(ChronoField.NANO_OF_SECOND));
        System.out.println(LocalTime.now().range(ChronoField.NANO_OF_DAY));
        System.out.println(LocalTime.now().range(ChronoField.SECOND_OF_MINUTE));
        System.out.println(LocalTime.now().range(ChronoField.SECOND_OF_DAY));
        System.out.println(LocalTime.now().range(ChronoField.MINUTE_OF_HOUR));
        System.out.println(LocalTime.now().range(ChronoField.MINUTE_OF_DAY));
        System.out.println("-".repeat(25));


        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        localDateTime               = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        System.out.println(localDateTime);

        localDateTime               = LocalDateTime.of(2023, 9, 20, 13, 53);
        System.out.println(localDateTime);
        localDateTime               = LocalDateTime.of(2023, Month.SEPTEMBER, 20, 13, 54);
        System.out.println(localDateTime);
        localDateTime = LocalDateTime.of(2023, Month.of(9), 20, 13, 56, 23);
        System.out.println(localDateTime);
        localDateTime = LocalDateTime.of(2023, Month.valueOf("SEPTEMBER"), 20, 13, 57);
        System.out.println(localDateTime);
        localDateTime = LocalDateTime.of(2023, 9, 20, 13, 59, 34, 23454545);
        System.out.println(localDateTime);
        localDateTime = LocalDateTime.of(2023, Month.SEPTEMBER, 20, 14, 0, 15, 2345);
        System.out.println(localDateTime);

        localDateTime = LocalDateTime.parse("2023-09-20T14:02:49.309958899");
        System.out.printf("percent tD for date n tr for time : %tD %tr %n",localDateTime, localDateTime);
        System.out.printf("percent tF n tT %1$tF %1$tT %n", localDateTime);//Both r default except there is no nano-second

       System.out.println(today.format(DateTimeFormatter.ISO_LOCAL_DATE));
       System.out.println(todayYD.format(DateTimeFormatter.ISO_DATE));
       System.out.println(today.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
       System.out.println(today.format((DateTimeFormatter.BASIC_ISO_DATE)));
       System.out.println(todayYD.format(DateTimeFormatter.ISO_WEEK_DATE));

       System.out.println(localDateTime.format(DateTimeFormatter.ISO_DATE_TIME));
       System.out.println(localDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.MEDIUM)));
       DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd,yy/MM");
       System.out.println(localDateTime.format(pattern));

       System.out.println(LocalDate.EPOCH);
       System.out.println(Instant.EPOCH);
       System.out.println(System.currentTimeMillis());
       System.out.println(System.nanoTime());


    }
}
