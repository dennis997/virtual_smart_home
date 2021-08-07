package Entities;

import java.sql.Timestamp;
import java.util.UUID;

public class SensorData {

    private String location;
    private String timestamp;
    private int humidity, temp, brightness, volume;

    public SensorData(String location, String timestamp, int humidity, int temp, int brightness, int volume) {
        this.location = location;
        this.timestamp = timestamp;
        this.humidity = humidity;
        this.temp = temp;
        this.brightness = brightness;
        this.volume = volume;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("[" + location + "] ");
        sb.append(timestamp+" | ");
        sb.append("humidity: " + humidity + " % | ");
        sb.append("temp: " + temp + " C | ");
        sb.append("brightness: " + brightness + " % | ");
        sb.append("volume: " + volume + " dB | ");
        return sb.toString();
    }
}
