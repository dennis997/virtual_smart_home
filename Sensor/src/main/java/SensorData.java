/**
 * SensorData class represents a single dataset of six values to describe the current situation
 * in range of the specific sensor location.
 */
public class SensorData {
    private String location;
    private String timestamp;
    private int humidity, temp, brightness, volume;
    private String topic;

    /**
     * Get-method for attribute location
     * @return location as String
     */
    public String getLocation() {
        return location;
    }
    /**
     * Get-method for attribute timestamp
     * @return timestamp as String
     */
    public String getTimestamp() { return timestamp; }
    /**
     * Get-method for attribute humidity
     * @return humidity as Integer
     */
    public int getHumidity() { return humidity; }
    /**
     * Get-method for attribute temp
     * @return temp as Integer
     */
    public int getTemp() { return temp; }
    /**
     * Get-method for attribute brightness
     * @return brightness as Integer
     */
    public int getBrightness() { return brightness; }
    /**
     * Get-method for attribute volume
     * @return volume as Integer
     */
    public int getVolume() { return volume; }

    /**
     * Get-method for attribute topic
     * @return topic as String
     */
    public String getTopic() { return topic; }



    /**
     * SensorData Constructor initializes all six attributes for a complete dataset
     * @param location represents the sensor location and also makes a sensor uniquely identifiable
     * @param timestamp represents the exact time, when the sensor data was measured
     * @param humidity is measured in %
     * @param temp is measured in °C
     * @param brightness is measured in %
     * @param volume is measured in dB
     */
    public SensorData(String location, String timestamp, int humidity, int temp, int brightness, int volume, String topic) {
        this.location = location;
        this.timestamp = timestamp;
        this.humidity = humidity;
        this.temp = temp;
        this.brightness = brightness;
        this.volume = volume;
        this.topic = topic;
    }

    /**
     * toString method prints an Instance of SensorData as readable console output
     * @return a readable string for console output
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("[" + topic + "] ");
        sb.append("[" + location + "] ");
        sb.append(timestamp+" | ");
        sb.append("humidity: " + humidity + " % | ");
        sb.append("temp: " + temp + " C | ");
        sb.append("brightness: " + brightness + " % | ");
        sb.append("volume: " + volume + " dB | ");
        sb.append("[" + topic + "] ");
        return sb.toString();
    }
}
