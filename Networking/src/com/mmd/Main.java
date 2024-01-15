package com.mmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

//        try {
//
//            URL url = new URL("http://www.example.com");
//
//            //openStream performs two operations, 1st establishes URLConnection, 2nd opens the connection                                                      directly
//            try(BufferedReader input = new BufferedReader(new InputStreamReader(url.openStream()))){//BufferedReader accepts a reader, that is why we don't pass openStream
//                String line= "";
//                while ((line=input.readLine())!=null){
//                    System.out.println(line);
//                }
//            }
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }

        try {

            URL url = new URL("http://www.example.com");

            //Create a communication link between the app and the above url.
            URLConnection urlConnection = url.openConnection();
//            urlConnection.connect(); //Looks like it is optional.

            //Read from the stream using InputStream, the getInputStream returns an InputStream.
            var input = urlConnection.getHeaderFields();

            input.forEach((k, v) -> {
                System.out.println("key: " + k);
                v.forEach(vv-> System.out.println("     " + v));
            });


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
          catch (IOException e){
            e.printStackTrace();
          }

    }
}