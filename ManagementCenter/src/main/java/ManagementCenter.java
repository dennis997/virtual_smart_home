import SensorProcessor.MQTTReceiver;
import CloudConnection.CloudConnector;
import Entities.SensorData;
import WebServer.HTTPServer;
import SensorProcessor.UDPReceiver;
import org.apache.thrift.transport.TTransportException;

/**
 * The ManagementCenter is the core component, managing the three services HTTPServer, UDPReceiver and Cloudconnector (the
 * communication to the Service Provider via Apache Thrift).
 */
public class ManagementCenter {
    private String serverIP;
    private int sensorSocketPort;
    private int httpServerPort;
    private int thriftServerPort;
    private UDPReceiver udpReceiver;
    private MQTTReceiver mqttReceiver;
    private HTTPServer httpServer;
    private int MQTT;
    private CloudConnector cloudConnector;

    /**
     * Constructor obtains environment variables provided by docker-compose.yml to initialize the UDPReceiver,
     * HTTPServer Sockets and Cloucconnector TThreadPoolServer. Prints out status messages afterwards.
     * @throws Exception
     */
    ManagementCenter() throws Exception{
        try{
            if (    (System.getenv("SENSOR_RECEIVER_PORT") != null) ||
                    (System.getenv("HTTP_SERVER_PORT") != null) ||
                    (System.getenv("THRIFT_SERVER_PORT") != null)){

                sensorSocketPort = Integer.parseInt(System.getenv("SENSOR_RECEIVER_PORT"));
                httpServerPort = Integer.parseInt(System.getenv("HTTP_SERVER_PORT"));
                MQTT = Integer.parseInt(System.getenv("MQTT"));
                thriftServerPort = Integer.parseInt(System.getenv("THRIFT_SERVER_PORT"));
            } else {
                System.out.println("[INFO] Using default IP/Port Configuration");
                serverIP = "localhost";
                sensorSocketPort = 5000;
                httpServerPort = 7000;
                MQTT = 1;
                httpServerPort = 7000;
                thriftServerPort = 9002;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("[INFO] Server is up and running...");
        this.udpReceiver = new UDPReceiver(sensorSocketPort);
        this.httpServer = new HTTPServer(udpReceiver, httpServerPort);
        this.cloudConnector = new CloudConnector(serverIP, thriftServerPort);

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

    /**
     * Starts the SensorReceiver (UDP / MQTT) in separate thread.
     */
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

    /**
     * Starts the HTTPServer to listen for new clients.
     * @throws Exception
     */
    public void runHTTPServer() throws Exception {
        new Thread(() -> {
            try {
                this.httpServer.listen();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void runCloudConnector() throws InterruptedException, TTransportException {
        this.cloudConnector.sendSensorData(new SensorData("hier", "uhrzeit", 50, 20, 30, 40));
    }
}