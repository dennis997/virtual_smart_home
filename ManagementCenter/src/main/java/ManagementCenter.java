import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

public class ManagementCenter {

    private static InetAddress IPAddress;
    private static DatagramSocket serverSocket;
    private static ArrayList<SensorData> sensorData;

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



    public SensorData parseData(String data){
        //
        String location= "ww";

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int humidity = 0, temp = 0, brightness = 0, volume = 0;

        JSONObject jo = new JSONObject(data);
        SensorData sd = new SensorData(location,timestamp,humidity,temp,brightness,volume);

        return sd;
    }


}
