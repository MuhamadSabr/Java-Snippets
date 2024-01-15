package com.mmd.external;

import java.time.LocalDateTime;

public class Logger {

    public static void logToConsole(CharSequence message){//Can take either a String or a StringBuilder.
        LocalDateTime dt = LocalDateTime.now();
        System.out.println(dt + " : " + message);
        if(message instanceof StringBuilder ms){
            ms.setLength(0);
        }
    }

}
