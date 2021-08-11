package SensorProcessor;
import Entities.SensorData;
import org.json.JSONObject;

import java.net.*;
import java.util.ArrayList;

/**
 * UDPReceiver represents one of the two main services running on the ManagementCenter. It receives SensorData on a
 * listening UDPSocket and saves it to an ArrayList, which is being accessed by the Webinterface of the HTTP-Server and
 * by Thrift for the DBServer-Connection
 */
public class UDPReceiver {
    private DatagramSocket serverSocket;
    private static ArrayList<SensorData> sensorData;

    /**
     * Constructor creates the datastructure to save the sensorData and initializes a new UDP Socket
     * @param serverSocketPort is the UDP Port to listen for incoming SensorData
     * @throws SocketException
     */
    public UDPReceiver(int serverSocketPort) throws SocketException{
        this.serverSocket = new DatagramSocket(serverSocketPort);
        this.sensorData = new ArrayList<SensorData>();
    }

    /**
     * Get-Method to return all the saved SensorData
     * @return ArrayList of SensorData
     */
    public ArrayList<SensorData> getSensorData() {return sensorData;}

    /**
     * Takes received JsonString and parses it to create a new SensorData object to save in provided ArrayList
     * @param sensorDataString is the received Dataset
     * @return the created SensorData object
     */
    private SensorData parseReceivedData(String sensorDataString) {
        /*
        In some cases JSON-String is malformed and not accepted by builtin JsonReader. Throws exception every time
        and therefore unreliable
         */
        //Gson gson = new Gson();
        //SensorData sensorDataObject = gson.fromJson(sensorDataString, SensorData.class);

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

    /**
     * Listens on specified Datagramsocket with provided port and adds the received data to ArrayList after parsing it
     * @throws Exception
     */
    public void receiveData() throws Exception {
        byte[] data = new byte[256];
        System.out.println("[SensorProcessor] Listening on Port " + this.serverSocket.getLocalPort());
        System.out.println("[SensorProcessor] Ready to receive data...");
        while(true) {
            DatagramPacket receivePacket = new DatagramPacket(data, data.length);
            this.serverSocket.receive(receivePacket);
            String sensorDataString = new String(receivePacket.getData()).trim();
            sensorData.add(parseReceivedData(sensorDataString));
        }
    }
}
