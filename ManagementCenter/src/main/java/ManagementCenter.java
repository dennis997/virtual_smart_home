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
    private String serverName;
    private int sensorSocketPort;
    private int httpServerPort;
    private int thriftServerPort;
    private UDPReceiver udpReceiver;
    private MQTTReceiver mqttReceiver;
    private HTTPServer httpServer;
    private int MQTT;
    private int mqttBrokerPort;
    private String mqttBrokerName;
    private CloudConnector cloudConnector;

    /**
     * Constructor obtains environment variables provided by docker-compose.yml to initialize the UDPReceiver,
     * HTTPServer Sockets and Cloucconnector TThreadPoolServer. Prints out status messages afterwards.
     * @throws Exception
     */
    ManagementCenter() throws Exception{
        try{
            if ( //start in Docker
                    (System.getenv("SENSOR_RECEIVER_PORT") != null) || (System.getenv("HTTP_SERVER_PORT") != null) ||
                    (System.getenv("THRIFT_SERVER_PORT") != null) || (System.getenv("BROKER") != null) ||
                    (System.getenv("BROKER_PORT") != null) || (System.getenv("SERVER_NAME") != null))
            {
                sensorSocketPort = Integer.parseInt(System.getenv("SENSOR_RECEIVER_PORT"));
                httpServerPort = Integer.parseInt(System.getenv("HTTP_SERVER_PORT"));
                MQTT = Integer.parseInt(System.getenv("MQTT"));
                mqttBrokerPort = Integer.parseInt(System.getenv("BROKER_PORT"));
                mqttBrokerName = System.getenv("BROKER");
                thriftServerPort = Integer.parseInt(System.getenv("THRIFT_SERVER_PORT"));
                serverName = System.getenv("SERVER_NAME");


            } else { //start in IDE
                System.out.println("[INFO] Using default IP/Port Configuration");
                serverName = "localhost";
                mqttBrokerName = "localhost";
                mqttBrokerPort = 1883;
                sensorSocketPort = 5000;
                MQTT = 0; //UDP or MQTT?
                httpServerPort = 7001;
                thriftServerPort = 9002;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("[INFO] Server is up and running...");

        this.cloudConnector = new CloudConnector(serverName, thriftServerPort);

        if (MQTT==1){
            System.out.println("MQTT chosen");
            this.mqttReceiver = new MQTTReceiver(mqttBrokerName, mqttBrokerPort, cloudConnector);
            this.httpServer = new HTTPServer(mqttReceiver, httpServerPort);

        }
        else{
            System.out.println("UDP chosen");
            this.udpReceiver = new UDPReceiver(sensorSocketPort, cloudConnector);
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
}