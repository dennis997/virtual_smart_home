package SensorProcessor;

import Entities.SensorData;
import org.eclipse.paho.client.mqttv3.*;
import org.json.JSONObject;

import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.UUID;

public class MQTTReceiver implements MqttCallback{
    private static ArrayList<SensorData> sensorData;
    private static UUID uuid = UUID.randomUUID();
    private static MqttClient mqttClient;

//+broker
    public MQTTReceiver() throws MqttException{
        this.sensorData = new ArrayList<SensorData>();
        String broker = System.getenv("Broker");
        String brokerPort = System.getenv("Brokerport");
        this.mqttClient = new MqttClient("tcp://"+broker+":"+brokerPort,uuid.toString());
    }

    public ArrayList<SensorData> getSensorData() {
        return sensorData;
    }


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



    public void receiveData(){
        try{
            this.mqttClient.setCallback(this);
            this.mqttClient.connect();
            System.out.println("Subscriber connected to MQTT broker");

            // Subscribe to a topic.
            this.mqttClient.subscribe("sensor");
            System.out.println("Subscribed to Sensor");
        }
        catch(MqttException e){
            e.printStackTrace();
        }

    }
    /*
    This method is called when the connection to the server is lost.
     */
    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("Connection to MQTT broker lost!");

    }
    /*
    This method is called when a message arrives from the server.
    This method is invoked synchronously by the MQTT client.
    An acknowledgment is not sent back to the server until this method returns cleanly.
    If an implementation of this method throws an Exception, then the client will be shut down.
    When the client is next re-connected, any QoS 1 or 2 messages will be redelivered by the server.
    Any additional messages which arrive while an implementation of this method is running,
    will build up in memory, and will then back up on the network.
    If an application needs to persist data, then it should ensure the data is persisted prior to returning from this method,
    as after returning from this method, the message is considered to have been delivered, and will not be reproducible.
    It is possible to send a new message within an implementation of this callback (for example, a response to this message),
    but the implementation must not disconnect the client, as it will be impossible to send an acknowledgment for the message being processed,
    and a deadlock will occur.
     */
    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        sensorData.add(parseReceivedData(new String(mqttMessage.getPayload())));
    }
    /*
    Called when delivery for a message has been completed, and all acknowledgments have been received.
    For QoS 0 messages it is called once the message has been handed to the network for delivery.
    For QoS 1 it is called when PUBACK is received and for QoS 2 when PUBCOMP is received.
    The token will be the same token as that returned when the message was published.
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken MqttDeliveryToken) {
        try {
            System.out.println("Delivery completed: "+ MqttDeliveryToken.getMessage() );
        } catch (MqttException e) {
            System.out.println("Failed to get delivery token message: " + e.getMessage());
        }
    }
}

