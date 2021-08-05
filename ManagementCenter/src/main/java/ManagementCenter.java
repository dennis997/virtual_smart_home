import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ManagementCenter {

    private static InetAddress IPAddress;
    private static DatagramSocket serverSocket;

    ManagementCenter(String ip, int port) throws Exception{
        this.IPAddress = InetAddress.getByName(ip);
        this.serverSocket = new DatagramSocket(port);
    }

    public void receiveData() throws Exception {
        byte[] data = new byte[256];
        System.out.println("[INFO] Ready to receive data...");

        while(true) {
            DatagramPacket receivePacket = new DatagramPacket(data, data.length);
            this.serverSocket.receive(receivePacket);
            String sensorData = new String( receivePacket.getData()).trim();
            System.out.println(sensorData);
        }
    }
}
