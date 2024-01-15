package com.mmd;

import java.io.IOException;
import java.net.*;

public class MainHTTP {
    public static void main(String[] args) {

        //The HTTPURLConnection extends the URLConnection.
        try {
            URL url = new URL("http://www.example.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();



        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
