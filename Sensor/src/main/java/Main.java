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
            ip = args[0];
            port = Integer.parseInt(args[1]);
            location = args[2];
            sleeptimer = Integer.parseInt(args[3]);
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
