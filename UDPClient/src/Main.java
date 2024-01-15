import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Create a Datagram socket for the client.
        try (DatagramSocket datagramSocket = new DatagramSocket()) {

            //Server address and port.
            InetAddress serverAddress = InetAddress.getLocalHost();//Throws UnknownHostException that u have to handle obviously
            int serverPort = 5000;

            //Define scanner to get user input from the console and a variable to hold its value.
            Scanner scanner = new Scanner(System.in);
            String echo;

            do{

                //Data to be sent taken from the console and changed to bytes.
                System.out.println("Enter your message:");
                echo = scanner.nextLine();
                byte[] sendBuffer = echo.getBytes();

                //Create a Datagram packet to send the message. Don't forget to specify the server address and its port.
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);

                //Send the message to the server.
                datagramSocket.send(sendPacket);

                //Create a DatagramPacket to receive data from the server. Passing a specific buffer, address and port of the server.
                byte[] receiveBuffer = new byte[50];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length, serverAddress, serverPort);

                //Receive the packet.
                datagramSocket.receive(receivePacket);

                //Process the packet.
                String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println(receivedData);

            }while (!echo.equalsIgnoreCase("exit"));

        } catch (SocketException | UnknownHostException e){
            System.out.println("Socket Exception " + e.getMessage());
        }
          catch (IOException e){
            System.out.println("I/O Exception: " + e.getMessage());
          }

    }
}