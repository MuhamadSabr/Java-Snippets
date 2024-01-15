import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class RequestHandler extends Thread{
    DatagramSocket datagramSocket;
    private final DatagramPacket receivePacket;
    public RequestHandler(DatagramSocket datagramSocket, DatagramPacket receivePacket){
        this.datagramSocket = datagramSocket;
        this.receivePacket = receivePacket;
    }
    @Override
    public void run(){

        //Process the received data
        String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("Texted received: " + receivedData);

        //Get the client's address and port from the DatagramPacket you just received.
        InetAddress clientAddress = receivePacket.getAddress();
        int clientPort = receivePacket.getPort();

        //Prepare your response, and create a DatagramPacket from the response, and client address and port.
        byte[] replyBuffer = ("echoed by the server: " + receivedData).getBytes();
        DatagramPacket sendPacket = new DatagramPacket(replyBuffer, replyBuffer.length, clientAddress, clientPort);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        //Send your response.
        try {
            datagramSocket.send(sendPacket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
