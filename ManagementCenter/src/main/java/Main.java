public class Main {


        public static void main(String[] args) throws Exception {
        try{
            String ip = System.getenv("IP");
            int port = Integer.parseInt(System.getenv("PORT"));

            ManagementCenter managementCenter = new ManagementCenter(ip, port);
            System.out.println("[INFO] Server is listening on port " + port + "...");
            managementCenter.receiveData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}