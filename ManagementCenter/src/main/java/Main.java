public class Main {
        public static void main(String[] args) throws Exception {
        try{
            String ip = args[0];
            int port = Integer.parseInt(args[1]);

            ManagementCenter managementCenter = new ManagementCenter(ip, port);
            System.out.println("[INFO] Server is listening on port " + port + "...");
            managementCenter.receiveData();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}