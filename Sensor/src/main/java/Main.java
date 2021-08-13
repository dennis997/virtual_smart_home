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
        int mqtt;

        try{
            if(EnvIsSet()){ //Docker
                ip = System.getenv("IP");
                port = Integer.parseInt(System.getenv("PORT"));
                location = System.getenv("LOCATION");
                sleeptimer = Integer.parseInt(System.getenv("SLEEPTIMER"));
                mqtt = Integer.parseInt(System.getenv("MQTT"));
                SmartHomeSensor sensor = new SmartHomeSensor(ip,port,location,sleeptimer, mqtt);
                sensor.sendData();
            }
            else{ //IDE
                ip="localhost";
                port = 5000;
                location = "kitchen";
                sleeptimer = 5000;
                mqtt = 0; // UDP or MQTT
                SmartHomeSensor sensor = new SmartHomeSensor(ip,port,location,sleeptimer,mqtt);
                System.out.println("Sending Data!");
                sensor.sendData();

            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static boolean EnvIsSet(){
        if (
                (System.getenv("IP") != null) && (System.getenv("PORT") != null) && (System.getenv("LOCATION") != null)
                && (System.getenv("SLEEPTIMER") != null) && (System.getenv("MQTT") != null)){
            return true;
        }
        return false;
    }
}
