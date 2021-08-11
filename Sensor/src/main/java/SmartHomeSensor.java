import java.io.IOException;
import java.net.*;
import java.sql.Timestamp;
import java.util.UUID;
import com.google.gson.*;


/**
 * SmartHomeSensor class represents a every single instance of a sensor and its ability to generate
 * and send data to the specified ManagementCenter.
 */
public class SmartHomeSensor {
    private static UUID uuid;
    private static String location;
    private static InetAddress IPAddress;
    private static int port;
    private static DatagramSocket clientSocket;
    private static int sleeptimer;

    /**
     *
     * @param ip of ManagementCenter within the internal docker network
     * @param port of ManagementCenter within the internal docker network
     * @param location of sensor
     * @param sleeptimer specifies the interval to send out data in ms
     * @throws Exception
     */
    public SmartHomeSensor(String ip, int port, String location, int sleeptimer) throws Exception {
        this.clientSocket = new DatagramSocket();
        this.port = port;
        this.IPAddress = InetAddress.getByName(ip);
        this.location = location;
        this.sleeptimer = sleeptimer;
        this.uuid = UUID.randomUUID();
    }

    /**
     *
     * @param min range limit of random generated data
     * @param max min range limit of random generated data
     * @return
     */
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    /**
     * Generate six pseudo-random sensor values to be send out
     * @return byte[] to be send out via UDP-socket
     */
    public byte[] generateSensorData() {
        SensorData sensorData = new SensorData(
                location,
                new Timestamp(System.currentTimeMillis()).toString(),
                getRandomNumber(30,99),
                getRandomNumber(17,30),
                getRandomNumber(0,100),
                getRandomNumber(35,95)
        );
        Gson gson = new Gson();
        System.out.println("Sending sensordata [" + location + "]");
        return gson.toJson(sensorData).getBytes();
    }

    /**
     * sendData sends out the byte[] of sensorData over the initialized UDPSocket
     * @throws InterruptedException
     */
    public void sendData() throws InterruptedException {

        while(true){
            byte[] sensorData = generateSensorData();
            DatagramPacket sendPacket = new DatagramPacket(sensorData, sensorData.length, this.IPAddress , this.port);
            try {
                System.out.println("[DEBUG] " + this.IPAddress.toString() + ":" + this.port );
                clientSocket.send(sendPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Thread.sleep(sleeptimer);
        }
    }
}
