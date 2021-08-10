import WebServer.HTTPServer;
import SensorProcessor.UDPReceiver;

public class ManagementCenter {
    private String serverIP;
    private int sensorSocketPort;
    private int httpServerPort;
    private UDPReceiver udpReceiver;
    private HTTPServer httpServer;

    ManagementCenter() throws Exception{
        try{
            if (    (System.getenv("SENSOR_RECEIVER_PORT") != null) ||
                    (System.getenv("HTTP_SERVER_PORT") != null)) {

                sensorSocketPort = Integer.parseInt(System.getenv("SENSOR_RECEIVER_PORT"));
                httpServerPort = Integer.parseInt(System.getenv("HTTP_SERVER_PORT"));
            } else {
                System.out.println("[INFO] Using default IP/Port Configuration");
                serverIP = "localhost";
                sensorSocketPort = 5000;
                httpServerPort = 6000;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("[INFO] Server is up and running...");
        this.udpReceiver = new UDPReceiver(sensorSocketPort);
        this.httpServer = new HTTPServer(udpReceiver, httpServerPort);
    }

    public void runSensorReceiver() {
        new Thread(() -> {
            try {
                this.udpReceiver.receiveData();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void runHTTPServer() throws Exception {
        this.httpServer.listen();
    }
}