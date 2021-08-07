import Entities.SensorData;
import com.google.gson.Gson;
import org.json.JSONObject;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class ManagementCenter {

    private static InetAddress IPAddress;
    private static DatagramSocket serverSocket;
    private static ArrayList<SensorData> sensorData;
    private static Gson gson;

    ManagementCenter(String ip, int port) throws Exception{
        this.IPAddress = InetAddress.getByName(ip);
        this.serverSocket = new DatagramSocket(port);
        this.sensorData = new ArrayList<SensorData>();
        this.gson = new Gson();
    }


    public SensorData parseReceivedData(String sensorDataString) {
        JSONObject jsonSensorData = new JSONObject(sensorDataString);

        String location = (String) jsonSensorData.get("location");
        String timestamp = (String) jsonSensorData.get("timestamp");
        int humidity = (int) jsonSensorData.get("humidity");
        int temp = (int) jsonSensorData.get("temp");
        int brightness = (int) jsonSensorData.get("brightness");
        int volume = (int) jsonSensorData.get("volume");

        SensorData sensorDataObject = new SensorData(location, timestamp, humidity, temp, brightness, volume);
        System.out.println(sensorDataObject);
        return sensorDataObject;
    }

    public void receiveData() throws Exception {
        byte[] data = new byte[256];
        System.out.println("[INFO] Ready to receive data...");

        while(true) {
            DatagramPacket receivePacket = new DatagramPacket(data, data.length);
            this.serverSocket.receive(receivePacket);
            String sensorDataString = new String(receivePacket.getData()).trim();
            sensorData.add(parseReceivedData(sensorDataString));
        }
    }
}
