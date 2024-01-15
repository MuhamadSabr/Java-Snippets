package com.mmd.localization;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.zone.ZoneRules;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

record Employee(String name, Locale locale, ZoneId zone){
    public Employee(String name, Locale locale, String zoneId) {
        this(name, locale, ZoneId.of(zoneId));
    }
    public Employee(String name, String locale, String zoneId) {
        this(name, Locale.forLanguageTag(locale), ZoneId.of(zoneId));
    }
    String getDateInfo(ZonedDateTime zdt, DateTimeFormatter dtf){
        return "%s [%s] : %s".formatted(name, zone, zdt.format(dtf.localizedBy(locale)));
    }

}

public class challengeCopy {
    public static void main(String[] args) {
        Employee jane = new Employee("Jane", Locale.US, "US/Eastern");
        Employee joe  = new Employee("Joe", "en-AU", "Australia/Sydney");

        ZoneRules janeRules = jane.zone().getRules();
        ZoneRules joeRules  = joe .zone().getRules();

        System.out.println(jane + " " + janeRules);
        System.out.println(joe + " " + joeRules);

        System.out.println(Duration.between(LocalDateTime.ofInstant(Instant.now(), joe.zone()),
                                            LocalDateTime.ofInstant(Instant.now(), jane.zone())));

        ZonedDateTime janeNow = ZonedDateTime.now(jane.zone());
        ZonedDateTime jowNow  = ZonedDateTime.of(janeNow.toLocalDateTime(), joe.zone());
        long hoursBetween = Duration.between(jowNow, janeNow).toHours();
        long minutesBetween=Duration.between(jowNow, janeNow).toMinutesPart();
        System.out.println("Jos is " + Math.abs(hoursBetween) + " hours and " +
                Math.abs(minutesBetween) + " minutes " +
                (hoursBetween>0 ? "ahead of" : "behind"));

        System.out.println(
                "Joe in daylight savings? " + joeRules.isDaylightSavings(jowNow.toInstant()) + " " +
                        joeRules.getDaylightSavings(jowNow.toInstant()) + ": " +
                        jowNow.format(DateTimeFormatter.ofPattern("zzzz z"))
        );
        System.out.println(
                "Jane in daylight savings? " + janeRules.isDaylightSavings(janeNow.toInstant()) + " " +
                        janeRules.getDaylightSavings(janeNow.toInstant()) + ": " +
                        janeNow.format(DateTimeFormatter.ofPattern("zzzz z"))
        );

        int days = 10;
        var map = schedule(joe, jane, days);
        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.SHORT);

        for(LocalDate ldt : map.keySet()){
//            System.out.println(ldt.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)));
            for(ZonedDateTime zdt : map.get(ldt)){
                System.out.println("\t" +
                        jane.getDateInfo(zdt, dtf) + " <---> " +
                        joe.getDateInfo(zdt.withZoneSameInstant(joe.zone()), dtf)
                );
            }
        }

    }

    public static Map<LocalDate, List<ZonedDateTime>> schedule(Employee first, Employee second, int days){
        Predicate<ZonedDateTime> rules = zdt->
                zdt.getDayOfWeek() != DayOfWeek.SATURDAY
                && zdt.getDayOfWeek() != DayOfWeek.SUNDAY
                && zdt.getHour() >=7 && zdt.getHour()<21;

        LocalDate startingDate = LocalDate.now().plusDays(2);

        return startingDate.datesUntil(startingDate.plusDays(days+1))
                .map(dt-> dt.atStartOfDay(first.zone()))
                .flatMap(dtz-> IntStream.range(0, 24).mapToObj(dtz::withHour))
                .filter(rules)
                .map(dtz-> dtz.withZoneSameInstant(second.zone()))
                .filter(rules)
                .collect(Collectors.groupingBy(ZonedDateTime::toLocalDate
                , TreeMap::new, Collectors.toList()));
    }
}
