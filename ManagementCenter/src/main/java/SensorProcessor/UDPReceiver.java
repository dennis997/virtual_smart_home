package SensorProcessor;
import Entities.SensorData;
import org.json.JSONObject;

import java.net.*;
import java.util.ArrayList;

public class UDPReceiver {
    private DatagramSocket serverSocket;
    private static ArrayList<SensorData> sensorData;

    public UDPReceiver(String ip, int serverSocketPort) throws SocketException, UnknownHostException {
        this.serverSocket = new DatagramSocket(serverSocketPort, InetAddress.getByName(ip));
        this.sensorData = new ArrayList<SensorData>();
    }

    // TODO: Implementing different get functions do differentiate by SensorID for history, singles and current data

    public ArrayList<SensorData> getSensorData() {return sensorData;}

    private SensorData parseReceivedData(String sensorDataString) {
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
        System.out.println("[SensorProcessor] Listening on Port " + this.serverSocket.getLocalPort());
        System.out.println("[INFO] Ready to receive data...");
        while(true) {
            DatagramPacket receivePacket = new DatagramPacket(data, data.length);
            this.serverSocket.receive(receivePacket);
            String sensorDataString = new String(receivePacket.getData()).trim();
            sensorData.add(parseReceivedData(sensorDataString));
        }
    }
}
