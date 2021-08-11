/**
 * SensorData class represents a single dataset of six values to describe the current situation
 * in range of the specific sensor location.
 */
public class SensorData {
    private String location;
    private String timestamp;
    private int humidity, temp, brightness, volume;

    /**
     * Get-method for attribute location
     * @return location as String
     */
    public String getLocation() {
        return location;
    }

    /**
     * SensorData Constructor initializes all six attributes for a complete dataset
     * @param location represents the sensor location and also makes a sensor uniquely identifiable
     * @param timestamp represents the exact time, when the sensor data was measured
     * @param humidity is measured in %
     * @param temp is measured in °C
     * @param brightness is measured in %
     * @param volume is measured in dB
     */
    public SensorData(String location, String timestamp, int humidity, int temp, int brightness, int volume) {
        this.location = location;
        this.timestamp = timestamp;
        this.humidity = humidity;
        this.temp = temp;
        this.brightness = brightness;
        this.volume = volume;
    }

    /**
     * toString method prints an Instance of SensorData as readable console output
     * @return a readable string for console output
     */
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
