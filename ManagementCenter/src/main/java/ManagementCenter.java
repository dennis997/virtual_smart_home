import CloudConnection.CloudConnector;
import WebServer.HTTPServer;
import SensorProcessor.UDPReceiver;

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
    private HTTPServer httpServer;
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
                thriftServerPort = Integer.parseInt(System.getenv("THRIFT_SERVER_PORT"));
            } else {
                System.out.println("[INFO] Using default IP/Port Configuration");
                serverIP = "localhost";
                sensorSocketPort = 5000;
                httpServerPort = 7000;
                thriftServerPort = 9002;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("[INFO] Server is up and running...");
        this.udpReceiver = new UDPReceiver(sensorSocketPort);
        this.httpServer = new HTTPServer(udpReceiver, httpServerPort);
        this.cloudConnector = new CloudConnector(serverIP, sensorSocketPort);
    }

    /**
     * Starts the SensorReceiver (UDP / MQTT) in separate thread.
     */
    public void runSensorReceiver() {
        new Thread(() -> {
            try {
                this.udpReceiver.receiveData();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Starts the HTTPServer to listen for new clients.
     * @throws Exception
     */
    public void runHTTPServer() throws Exception {
        this.httpServer.listen();
    }

    public void runCloudConnector() throws InterruptedException {
        this.cloudConnector.sendSensorData(this.udpReceiver.getSensorData().get(0));
    }
}