public class Main {

    /**
     * @param args ip, port, location, sleeptimer provided in docker-compose.yml as env variables
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String ip;
        int port;
        String location;
        int sleeptimer;

        try{
            ip = System.getenv("IP");
            port = Integer.parseInt(System.getenv("PORT"));
            location = System.getenv("LOCATION");
            sleeptimer = Integer.parseInt(System.getenv("SLEEPTIMER"));
            SmartHomeSensor sensor = new SmartHomeSensor(ip,port,location,sleeptimer);
            sensor.sendData();
        }
        catch (Exception e){
            ip = "managementcenter";
            port = 5000;
            location = "Wohnzimmer";
        }
    }
}
