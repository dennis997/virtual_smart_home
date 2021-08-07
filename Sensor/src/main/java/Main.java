public class Main {

/**
 @args: ip, port, location
 */
    public static void main(String[] args) throws Exception{

        String ip;
        int port;
        String location;
        int sleeptimer;

        try{
            ip = System.getenv("DESTINATION");
            port = Integer.parseInt(System.getenv("PORT"));
            location = System.getenv("LOCATION");
            sleeptimer = Integer.parseInt(System.getenv("SLEEPTIMER"));
            SmartHome_Sensor sensor = new SmartHome_Sensor(ip,port,location,sleeptimer);
            sensor.sendData();

        }
        catch (Exception e){

            ip = "localhost";
            port = 5000;
            location = "Wohnzimmer";
        }
    }
}
