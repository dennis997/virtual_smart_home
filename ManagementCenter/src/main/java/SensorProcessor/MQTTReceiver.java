package SensorProcessor;

import Entities.SensorData;
import org.eclipse.paho.client.mqttv3.*;
import org.json.JSONObject;

import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.UUID;

public class MQTTReceiver{
    private static ArrayList<SensorData> sensorData;
    private static UUID uuid;
    private static MqttClient mqttClient;


    public MQTTReceiver() throws MqttException{
        this.sensorData = new ArrayList<SensorData>();
        this.mqttClient = new MqttClient("tcp://iot.eclipse.org:1883",uuid.toString());
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





    public void receiveData() throws MqttException {
        try{
            this.mqttClient.setCallback(new SimpleMqttCallback());
            this.mqttClient.connect();
            System.out.println("Subsriber connected to MQTT broker");

            // Subscribe to a topic.
            this.mqttClient.subscribe("sensor");
            System.out.println("Subsribed to Sensor");
        }
        catch(MqttException e){
            e.printStackTrace();
        }

    }
}

