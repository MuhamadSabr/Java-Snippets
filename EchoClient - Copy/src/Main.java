import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.http.HttpTimeoutException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Main {
    public static void main(String[] args) {
        try(Socket socket = new Socket("localhost", 5000)){
            socket.setSoTimeout(5000);
            BufferedReader echos = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter stringToEcho   = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            Scanner scanner = new Scanner(System.in);
            String echoString;
            String responseEcho;

            do {
                System.out.println("Enter string to be echoed:");
                echoString = scanner.nextLine();//Put the String taken from the user into a String then put the String into outputStream of the socket down below.
                stringToEcho.println(echoString);
                if(!echoString.equalsIgnoreCase("exit")){
                    responseEcho = echos.readLine();//Read the inputStream of the socket and print it below.
                    System.out.println(responseEcho);
                }
            }while (!echoString.equalsIgnoreCase("exit"));

        } catch (SocketException sx){
            System.out.println("http timeout: " + sx.getMessage());
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}