package com.mmd;

import java.text.DateFormatSymbols;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.stream.Collectors;

public class MoreMain {
    public static void main(String[] args) {

        System.setProperty("user.timezone", "America/Los_Angeles");//U should always setProperties in Main class n
        //needs to be set before any dateTime manipulation
        System.out.println(ZoneId.systemDefault());//Returns type ZoneId of the system zone that this app is running on
        System.out.println(TimeZone.getDefault());// Returns type TimeZone

        ZoneId.SHORT_IDS.forEach((k, v) -> System.out.println(k + " : " + v));//Returns Map<String, String>
        System.out.println(ZoneId.getAvailableZoneIds().size());//Returns a Set<String>
//        ZoneId.getAvailableZoneIds().stream()
//                .sorted()
//                .map(ZoneId::of)
//                .forEach(cz-> System.out.println( cz.getRules() ));//Returns type ZoneRules.
//        System.out.println("-".repeat(30));

        var zoneId = ZoneId.getAvailableZoneIds();
        var timeZArr = TimeZone.getAvailableIDs(); //Returns a String[] n not a Set<String> like ZoneId's method

        Set<String> timeZone = new HashSet<>(Set.of(timeZArr));
//        zoneId.removeAll(timeZone); //There was nothing in ZoneId that was not in timeZone
        timeZone.removeAll(zoneId); //There were Ids that were in TimeZone but not in ZoneId
        System.out.println(timeZone);
        ZoneId.SHORT_IDS.forEach((k, v) -> System.out.print(k +", "));
        System.out.println();
        ZoneId jst = ZoneId.of("JST", ZoneId.SHORT_IDS);

        System.out.println(LocalDateTime.now());
        Instant instantNow = Instant.now();
        System.out.println(instantNow);

        for(ZoneId z : List.of(
                ZoneId.of("Australia/Sydney"),
                ZoneId.of("Europe/Paris"),
                ZoneId.of("America/New_York") )){
            DateTimeFormatter zoneFormat = DateTimeFormatter.ofPattern("z:zzzz");//z is time zone abbreviation
            // zzzz is the time zone's region or full name. Number of z determines level of detail
            //zz is a longer form of the time zone abbreviation, if extra information available.

            System.out.println(z);
            ZonedDateTime zonedDateTime = instantNow.atZone(z);//Creating a ZonedDateTime by using .asZone(ZoneId)
            System.out.println("\t" + zonedDateTime.format(zoneFormat));
            System.out.println("\t" + z.getRules().getDaylightSavings(instantNow));//It is in Duration format
            //PT stands for Period Of Time. A number and the time unit used for the Duration(H, S, M).
            //That will only get offset if the DaylightSavings flag is true.
            System.out.println("\t" + z.getRules().isDaylightSavings(instantNow));
        }

        System.out.println(ZonedDateTime.now());

        Instant dobInstant = Instant.parse("2023-09-21T16:02:29z");//Only way to create an instance of Instant
        System.out.println(LocalDateTime.ofInstant(instantNow, ZoneId.systemDefault()));//Creating LocalDateTime ofInstant
        System.out.println(LocalDate.ofInstant(instantNow, ZoneId.systemDefault()));//LocalDate ofInstant(instant, ZoneId)
        System.out.println(LocalTime.ofInstant(instantNow, ZoneId.systemDefault()));//LocalTime ofInstant(instant, ZoneId)

        LocalDateTime dob = LocalDateTime.ofInstant(dobInstant, ZoneId.systemDefault());
        System.out.println("Your kid's birthdate, LA time: " + dob.format(
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)//Can't use FULL unless it includes zone info
        ));

        ZonedDateTime dobZonedDateTime = ZonedDateTime.ofInstant(dobInstant, ZoneId.of("Asia/Baghdad"));
        System.out.println("Your kid's birthdate, Baghdad time: " + dobZonedDateTime.format(
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        ));
        System.out.println("Your kid's birthdate, UTC time: " + dobZonedDateTime
                .withZoneSameInstant(ZoneId.of("UTC")).format(
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        ));

        System.out.println(LocalDateTime.now().with(TemporalAdjusters.firstDayOfNextMonth()));

        System.out.println(Period.between(LocalDate.EPOCH, dob.toLocalDate()));
        System.out.println(Duration.between(Instant.EPOCH, dob.toInstant(ZoneOffset.UTC)));

        System.out.println(Arrays.toString(ChronoUnit.values()));
        for(ChronoUnit u : ChronoUnit.values()){
            if(LocalDate.EPOCH.isSupported(u)){
                System.out.println(u + " is supported by LocalDate EPOCH");
            }
            else System.out.println(u + " is not supported by LocalDate EPOCH");
        }

        System.out.println("firstInMonth "+LocalDateTime.now().with(TemporalAdjusters.firstInMonth(DayOfWeek.THURSDAY)));
        System.out.println("lastInMonth "+LocalDate.now().with(TemporalAdjusters.lastInMonth(DayOfWeek.THURSDAY)));
        System.out.println("next "+LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.THURSDAY)));
        System.out.println("previous "+LocalDateTime.now().with(TemporalAdjusters.previous(DayOfWeek.WEDNESDAY)));
        System.out.println("nextOrSame "+LocalDateTime.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.THURSDAY)));
        System.out.println("previousOrSame "+LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY)));
        System.out.println("dayOfWeekInMonth "+LocalDate.now().with(TemporalAdjusters.dayOfWeekInMonth(2,DayOfWeek.FRIDAY)));
        System.out.println(": "+LocalDate.now().with(TemporalAdjusters.dayOfWeekInMonth(-6, DayOfWeek.FRIDAY)));

        System.out.println("firstDayOfMonth "+LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()));
        System.out.println("lastDayOfMonth "+LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()));
        System.out.println("firstDayOfNextMonth "+LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth()));
        System.out.println("firstDayOfYear "+LocalDate.now().with(TemporalAdjusters.firstDayOfYear()));
        System.out.println("lastDayOfYear "+LocalDate.now().with(TemporalAdjusters.lastDayOfYear()));
        System.out.println("firstDayOfNextYear "+LocalDate.now().with(TemporalAdjusters.firstDayOfNextYear()));

        System.out.println("-".repeat(35));

        System.out.println("ZERO "+ Duration.ZERO);
        System.out.println(Duration.ofNanos(5));
        System.out.println(Duration.ofMillis(5));
        System.out.println(Duration.ofSeconds(5));
        System.out.println(Duration.ofMinutes(5));
        System.out.println(Duration.ofHours(5));
        System.out.println(Duration.ofDays(5));
        System.out.println(Duration.parse("PT5H5M5.005000005s"));
        System.out.println(Duration.between(LocalTime.now(), LocalTime.now().plus(Duration.ofHours(1)).withMinute(34).withNano(345)));
        System.out.println(Duration.from(Duration.of(20, ChronoUnit.HOURS)));

        System.out.println(Period.of(13, 13, 67));
        System.out.println(Period.parse("P13M13D"));
        System.out.println(Period.from(Period.between(LocalDate.now(), LocalDate.now().plusDays(1))));
        System.out.println(Period.ZERO);
        System.out.println(Period.ofDays(2));
        System.out.println(Period.ofWeeks(21));
        System.out.println(Period.ofMonths(2));
        System.out.println(Period.ofYears(2).getUnits());

        System.out.println("-".repeat(30));

        System.out.println(ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.now().plus(Period.ofDays(1454))));
        System.out.println(ChronoUnit.CENTURIES.between(LocalDate.EPOCH, LocalDate.now()));
        System.out.println(ChronoUnit.YEARS.between(LocalDate.now(), LocalDate.ofYearDay(2020,4)));

    }
}
