public class Main {

    public static void main(String[] args) throws Exception {
        ManagementCenter managementCenter = new ManagementCenter();
        managementCenter.runSensorReceiver();
        managementCenter.runHTTPServer();
    }
}