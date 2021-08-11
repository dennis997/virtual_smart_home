import SensorProcessor.MQTTReceiver;
import WebServer.HTTPServer;
import SensorProcessor.UDPReceiver;

public class ManagementCenter {
    private String serverIP;
    private int sensorSocketPort;
    private int httpServerPort;
    private UDPReceiver udpReceiver;
    private MQTTReceiver mqttReceiver;
    private HTTPServer httpServer;
    private int MQTT;

    ManagementCenter() throws Exception{
        try{
            if (    (System.getenv("SENSOR_RECEIVER_PORT") != null) ||
                    (System.getenv("HTTP_SERVER_PORT") != null)) {

                sensorSocketPort = Integer.parseInt(System.getenv("SENSOR_RECEIVER_PORT"));
                httpServerPort = Integer.parseInt(System.getenv("HTTP_SERVER_PORT"));
                MQTT = Integer.parseInt(System.getenv("MQTT"));
            } else {
                System.out.println("[INFO] Using default IP/Port Configuration");
                serverIP = "localhost";
                sensorSocketPort = 5000;
                httpServerPort = 6000;
                MQTT = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("[INFO] Server is up and running...");

        if (MQTT==1){
            System.out.println("MQTT chosen");
            this.mqttReceiver = new MQTTReceiver();
            this.httpServer = new HTTPServer(mqttReceiver, httpServerPort);

        }
        else{
            System.out.println("UDP chosen");
            this.udpReceiver = new UDPReceiver(sensorSocketPort);
            this.httpServer = new HTTPServer(udpReceiver, httpServerPort);
        }
    }

    public void runSensorReceiver() {
        if (MQTT == 1){
            new Thread(() -> {
                try {
                    this.mqttReceiver.receiveData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

        }
        else{
            new Thread(() -> {
                try {
                    this.udpReceiver.receiveData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public void runHTTPServer() throws Exception {
        this.httpServer.listen();
    }
}