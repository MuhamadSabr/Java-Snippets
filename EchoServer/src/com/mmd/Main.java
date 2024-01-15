package com.mmd;

import java.io.*;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            while (true){
                //Listen to clients on 5000.    //.accept() returns a Socket, and this will be the socket used to communicate with the client.
                new Echoer(serverSocket.accept()).start(); //It blocks until a client connects to the server. For each connecting client, we kick of a new thread
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}