package com.mmd.localization;

import javax.swing.plaf.synth.SynthUI;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

public class LocalizationChallenge {
    static final Locale australia = new Locale("en", "AU");
    public static void main(String[] args) {

        ZonedDateTime et = ZonedDateTime.of(LocalDateTime.ofInstant(Instant.now(), ZoneId.of("US/Eastern")),
                ZoneId.of("US/Eastern")) ;
        ZonedDateTime aus =ZonedDateTime.of( LocalDateTime.ofInstant(Instant.now(), ZoneId.of("Australia/Sydney")),
                ZoneId.of("Australia/Sydney"));

        workTime(et, "Jane", aus, "Joe");
        System.out.println(et);
        System.out.println(aus);
    }
    public static boolean workDay(DayOfWeek day){
        if (day.equals(DayOfWeek.SATURDAY) || day.equals(DayOfWeek.SUNDAY)){
            return false;
        }
        return true;
    }
    public static void workTime(ZonedDateTime dateTime1, String emp1 , ZonedDateTime dateTime2, String emp2){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("[VV] : EEEE, MMMM d, yyyy, h a");
        for(int i=0; i<240; i++){
            dateTime1 = dateTime1.plusHours(1);
            dateTime2 = dateTime2.plusHours(1);

            if(workDay(dateTime1.getDayOfWeek()) && workDay(dateTime2.getDayOfWeek())){
                if ( (dateTime1.getHour()>=7 && dateTime1.getHour()<=20) &&
                        (dateTime2.getHour()>=7 && dateTime2.getHour()<=20)){

                    System.out.print(emp1 + " " + dateTime1.format(dtf.withLocale(Locale.US)));
                    System.out.print("<--->");
                    System.out.print(emp2 + " " + dateTime2.format(dtf.withLocale(australia)));
                    System.out.println();
                }
            }
        }
    }
}
