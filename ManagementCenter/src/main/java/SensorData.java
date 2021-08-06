import java.sql.Timestamp;
import java.util.UUID;

public class SensorData {


    private String location;
    private UUID uuid;
    private Timestamp timestamp;
    private int humidity, temp, brightness, volume;



    public SensorData(String location, Timestamp timestamp, int humidity, int temp, int brightness, int volume) {
        this.location = location;
        this.timestamp = timestamp;
        this.humidity = humidity;
        this.temp = temp;
        this.brightness = brightness;
        this.volume = volume;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }



}
