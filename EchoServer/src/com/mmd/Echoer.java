package com.mmd;

import java.io.*;
import java.net.Socket;

public class Echoer extends Thread{
    {
        System.out.println("A new client connected and is being handled");
    }
    private Socket socket;
    public Echoer(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try{
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter    output= new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            while (true){
                String echo = input.readLine();//Read a line from the inputStream of the socket.
                System.out.println("Received from client side: " + echo);

                if(echo.equalsIgnoreCase("exit"))
                    break;

                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }

                output.println("From server: " + echo);//To the outputStream of the socket.
                System.out.println("From server: " + echo);

            }

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
