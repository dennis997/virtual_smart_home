import WebServer.HTTPServer;
import WebServer.Handler.HistoryHandler;
import SensorProcessor.UDPReceiver;

public class ManagementCenter {


    private String serverIP;
    private int sensorSocketPort;
    private int httpServerPort;
    private UDPReceiver udpReceiver;
    private HTTPServer httpServer;

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

                // TODO: Implementing reusable Logging Class for standardized message output on server console
                System.out.println("[INFO] Server is up and running...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.udpReceiver = new UDPReceiver(serverIP, sensorSocketPort);
        this.httpServer = new HTTPServer(httpServerPort);
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