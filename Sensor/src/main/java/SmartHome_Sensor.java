import java.io.IOException;
import java.net.*;
import java.sql.Timestamp;
import java.util.UUID;
import org.json.JSONObject;


public class SmartHome_Sensor{
    private static UUID uuid;
    private static String location;
    private static InetAddress IPAddress;
    private static int port;
    private static DatagramSocket clientSocket;
    private static int sleeptimer;

    public SmartHome_Sensor(String ip, int port, String location, int sleeptimer) throws Exception {
        this.clientSocket = new DatagramSocket();
        this.port = port;
        this.IPAddress = InetAddress.getByName(ip);
        this.location = location;
        this.sleeptimer = sleeptimer;
        this.uuid = UUID.randomUUID();
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public byte[] generateSensorData() {
        byte[] sendData = new byte[256];

        String timestamp = new Timestamp(System.currentTimeMillis()).toString();
        int humidity = getRandomNumber(30,99);
        int temp = getRandomNumber(17,30);
        int brightness = getRandomNumber(0,100);
        int volume = getRandomNumber(35,95);

        JSONObject jsonData = new JSONObject();
        jsonData.put("id",uuid);
        jsonData.put("location",location);
        jsonData.put("timestamp", timestamp);
        jsonData.put("humidity", humidity);
        jsonData.put("temp", temp);
        jsonData.put("brightness", brightness);
        jsonData.put("volume", volume);

        //System.out.println("["+uuid+" "+location+"]"+" "+timestamp+" "+humidity+" "+temp+" "+brightness+" "+volume);

        return jsonData.toString().getBytes();
    }


    public void sendData() throws InterruptedException {

        while(true){
            byte[] sensorData = generateSensorData();
            DatagramPacket sendPacket = new DatagramPacket(sensorData, sensorData.length, this.IPAddress , this.port);
            try {
                clientSocket.send(sendPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Thread.sleep(sleeptimer);
        }
    }
}
