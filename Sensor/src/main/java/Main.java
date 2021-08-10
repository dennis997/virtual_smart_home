public class Main {

/**
 @args: ip, port, location
 */
    public static void main(String[] args) throws Exception{

        String ip;
        int port;
        String location;
        int sleeptimer;
        int mqtt;

        try{
            ip = System.getenv("IP");
            port = Integer.parseInt(System.getenv("PORT"));
            location = System.getenv("LOCATION");
            sleeptimer = Integer.parseInt(System.getenv("SLEEPTIMER"));
            mqtt = Integer.parseInt(System.getenv("MQTT"));
            SmartHome_Sensor sensor = new SmartHome_Sensor(ip,port,location,sleeptimer, mqtt);
            sensor.sendData();
        }
        catch (Exception e){
            ip = "managementcenter";
            port = 5000;
            location = "Wohnzimmer";
        }
    }
}
