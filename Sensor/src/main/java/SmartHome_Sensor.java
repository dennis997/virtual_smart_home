import java.io.IOException;
import java.net.*;
import java.sql.Timestamp;
import java.util.UUID;

import org.eclipse.paho.client.mqttv3.*;
import org.json.JSONObject;


public class SmartHome_Sensor{
    private static UUID uuid;
    private static String location;
    private static InetAddress IPAddress;
    private static int port;
    private static DatagramSocket clientSocket;
    private static int sleeptimer;
    private static int mqtt;
    private static IMqttClient pub;

    public SmartHome_Sensor(String ip, int port, String location, int sleeptimer, int mqtt) throws Exception {
        this.clientSocket = new DatagramSocket();
        this.port = port;
        this.IPAddress = InetAddress.getByName(ip);
        this.location = location;
        this.sleeptimer = sleeptimer;
        this.uuid = UUID.randomUUID();
        this.mqtt = mqtt;
        /*
        If mqtt equals 1, mqtt is activated. If its 0, we use Udp.
        The server endpoint we're using is a public MQTT broker hosted by the Paho project,
        which allows anyone with an internet connection to test clients without the need of any authentication.
         */
        if(mqtt == 1){
            String broker = System.getenv("Broker");
            IMqttClient publisher = new MqttClient("tcp://"+broker+":1883",uuid.toString());
            //The code used to establish a connection to the server typically looks like this:
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true); //auto-reconnect after failure
            options.setCleanSession(true); //unsent messages from previous session will be discarded
            options.setConnectionTimeout(10); //connection timeout at 10 seconds
            System.out.println("trying to connect");
            publisher.connect(options);
            System.out.println("connected");
            pub = publisher;
        }
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public byte[] generateSensorData() {
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

        System.out.println("Sending sensordata [" + location + "]");

        return jsonData.toString().getBytes();
    }


    public void sendData() throws InterruptedException {

        if (mqtt == 1) {
            while(true) {
                byte[] sensorData = generateSensorData();
                MqttMessage mqttMessage = new MqttMessage(sensorData);
                mqttMessage.setQos(0); // Quality of Service: we don`t care that we lose Packages, just like UDP.
                mqttMessage.setRetained(true); //This flag indicates to the broker that it should retain this message until consumed by a subscriber.
                try {
                    System.out.println("publishing sensor data!");
                    pub.publish("sensor", mqttMessage); //publishing the message with topic "sensor" (?)
                }
                catch (MqttException e){
                    e.printStackTrace();
                }
                Thread.sleep(sleeptimer);
            }
        } else {
            while (true) {
                byte[] sensorData = generateSensorData();
                DatagramPacket sendPacket = new DatagramPacket(sensorData, sensorData.length, this.IPAddress, this.port);
                try {
                    System.out.println("[DEBUG] " + this.IPAddress.toString() + ":" + this.port);
                    clientSocket.send(sendPacket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Thread.sleep(sleeptimer);
            }
        }
    }
}
