import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Main {
    public static void main(String[] args) {

        //Create a DatagramSocket to listen on a specific port.
        try (DatagramSocket datagramSocket = new DatagramSocket(5000)) {

            while (true){
                byte[] receiveBuffer = new byte[50];

                //Create a DatagramPacket to receive data. Pass the buffer and its length to it.
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

                //Receives the packet and puts in the buffer. Blocks until data is received.
                datagramSocket.receive(receivePacket);

                new RequestHandler(datagramSocket, receivePacket).start();

            }

        } catch (SocketException e){
            System.out.println("Socket Exception: " + e.getMessage());
        }
        catch (IOException e){
            System.out.println("I/O Exception: " + e.getMessage());
        }
    }
}