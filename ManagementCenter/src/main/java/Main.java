public class Main {

    /**
     * Instantiates ManagementCenter instance and calls the methods to start its services
     * @param args none provided by Dockerfile
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ManagementCenter managementCenter = new ManagementCenter();
        managementCenter.runSensorReceiver();
        managementCenter.runHTTPServer();
        managementCenter.runCloudConnector();
    }
}