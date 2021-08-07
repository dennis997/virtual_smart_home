import Entities.SensorData;
import HTTPServer.HTTPHandler;
import SensorProcessor.UDPReceiver;

public class ManagementCenter {
    private String serverIP;
    private int sensorSocketPort;
    private int httpServerPort;
    private UDPReceiver udpReceiver;
    private HTTPHandler httpHandler;


    ManagementCenter() throws Exception{
        try{
            if (    (System.getenv("IP") != null ||
                    (System.getenv("SENSOR_RECEIVER_PORT") != null)) ||
                    (System.getenv("HTTP_SERVER_PORT") != null)) {
                serverIP = System.getenv("IP");
                sensorSocketPort = Integer.parseInt(System.getenv("SENSOR_RECEIVER_PORT"));
                httpServerPort = Integer.parseInt(System.getenv("HTTP_SERVER_PORT"));
            } else {
                System.out.println("[INFO] Using default IP/Port Configuration");
                serverIP = "localhost";
                sensorSocketPort = 5000;
                httpServerPort = 6000;

                System.out.println("[INFO] Server is up and running...");
                System.out.println("[SensorProcessor] Listening on Port " + this.sensorSocketPort);
                System.out.println("[HTTPServer] Listening on Port " + this.httpServerPort);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.udpReceiver = new UDPReceiver(serverIP, sensorSocketPort);
        this.httpHandler = new HTTPHandler();
    }

    public void runSensorReceiver() throws Exception {
        this.udpReceiver.receiveData();
    }

    public void runHTTPServer() throws Exception {

    }

}

// TODO: Implementing reusable Logging Class for standardized message output on server console