public class Main {

    public static void main(String[] args) throws Exception {
        String ip;
        int port;

    try{
        if (!(System.getenv("IP") != null || !(System.getenv("PORT") != null))) {
            ip = System.getenv("IP");
            port = Integer.parseInt(System.getenv("PORT"));
        } else {
            System.out.println("[INFO] Using default IP/Port Configuration");
            ip = "localhost";
            port = 5000;
        }

        ManagementCenter managementCenter = new ManagementCenter(ip, port);
        System.out.println("[INFO] Server is listening on port " + port + "...");
        managementCenter.receiveData();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}