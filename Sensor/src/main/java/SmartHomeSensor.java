import java.io.IOException;
import java.net.*;
import java.sql.Timestamp;
import java.util.UUID;

import com.google.gson.Gson;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.*;
import org.json.JSONObject;

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
    private static int mqtt;
    private static String broker;
    private static int brokerPort;
    private static Logger logger;
    private static String topic;
    private IMqttClient publisher;
    /**
     *
     * @param ip of ManagementCenter within the internal docker network
     * @param port of ManagementCenter within the internal docker network
     * @param location of sensor
     * @param sleeptimer specifies the interval to send out data in ms
     * @throws Exception
     */
    public SmartHomeSensor(String ip, int port, String location, int sleeptimer, int mqtt, String topic) throws Exception {
        logger = Logger.getLogger(SmartHomeSensor.class);
        BasicConfigurator.configure();
        this.clientSocket = new DatagramSocket();
        this.port = port;
        this.IPAddress = InetAddress.getByName(ip);
        this.location = location;
        this.sleeptimer = sleeptimer;
        this.uuid = UUID.randomUUID();
        this.mqtt = mqtt;
        this.topic = topic;

        /*
        If mqtt equals 1, mqtt is activated. If its 0, we use Udp.
        The server endpoint we're using is a public MQTT broker hosted by the Paho project,
        which allows anyone with an internet connection to test clients without the need of any authentication.
         */
        if(mqtt == 1){
            //start in Docker
            if ((System.getenv("BROKER") != null) || (System.getenv("BROKER_PORT") != null)) {
                broker = System.getenv("BROKER");
                brokerPort = Integer.parseInt(System.getenv("BROKER_PORT"));
            } else { // start in IDE
                broker = "localhost";
                brokerPort = 1883;
            }
            connectMqttBroker();
        }
    }

    /**
     * Handles connect to MQTTBroker
     * @return status whether connection was successfully established
     */
    public Boolean connectMqttBroker() throws MqttException {

        publisher = new MqttClient("tcp://"+broker+":"+brokerPort,uuid.toString());
        //The code used to establish a connection to the server typically looks like this:
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true); //auto-reconnect after failure
        options.setCleanSession(true); //unsent messages from previous session will be discarded
        options.setConnectionTimeout(10); //connection timeout at 10 seconds
        logger.info("Connecting to MQTT broker...");
        try {
            publisher.connect(options);
        } catch (MqttException me) {
            logger.error("Cannot connect to MQTT broker!");
            return false;
        }
        logger.info("Connected to MQTT broker!");
        return true;
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
                getRandomNumber(35,95),
                topic
        );
        Gson gson = new Gson();
        return gson.toJson(sensorData).getBytes();
    }

    /**
     * Sends out the byte[] of sensorData over the initialized UDPSocket or MQTT API
     * @throws InterruptedException
     */

    public void sendData() throws InterruptedException, MqttException {
        if (mqtt == 1) {
            while(true) {
                // TODO: JUST FOR TESTING! DELETE AFTERWARDS!!!
                byte[] sensorData = generateSensorData();
                MqttMessage mqttMessage = new MqttMessage(sensorData);
                // TODO: QoS should be 1 or 2 in P5!
                mqttMessage.setQos(0); // Quality of Service: we don`t care that we lose Packages, just like UDP.
                mqttMessage.setRetained(true); // This flag indicates to the broker that it should retain this message until consumed by a subscriber.
                try {
                    publisher.publish(topic, mqttMessage); // Publishing the message with the corresponding topic
                }
                catch (MqttException e){
                    logger.info("Sensor lost connection to broker, trying to reconnect...");
                    Thread.sleep(5000);
                    sendData();
                }
                Thread.sleep(sleeptimer);
            }
        } else {
            while (true) {
                byte[] sensorData = generateSensorData();
                DatagramPacket sendPacket = new DatagramPacket(sensorData, sensorData.length, this.IPAddress, this.port);
                try {
                    clientSocket.send(sendPacket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Thread.sleep(sleeptimer);
            }
        }
    }

    /**
     * Sends out specified amount of sensordata over the initialized UDPSocket
     * @throws InterruptedException
     */
    public void sendData(int amount) throws InterruptedException {
        if (mqtt == 1) {
            for (int i = 0; i < amount; i++){
                byte[] sensorData = generateSensorData();
                MqttMessage mqttMessage = new MqttMessage(sensorData);
                mqttMessage.setQos(1);
                mqttMessage.setRetained(true); //This flag indicates to the broker that it should retain this message until consumed by a subscriber.
                try {
                    publisher.publish(topic, mqttMessage);
                }
                catch (MqttException e){
                    logger.error("Lost connection to MQTT-Broker!");

                }
            }
        } else {
            for (int i = 0; i < amount; i++){
                byte[] sensorData = generateSensorData();
                DatagramPacket sendPacket = new DatagramPacket(sensorData, sensorData.length, this.IPAddress, this.port);
                try {
                    clientSocket.send(sendPacket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
