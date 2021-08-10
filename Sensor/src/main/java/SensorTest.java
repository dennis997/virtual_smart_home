import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.Test;
import static org.junit.Assert.*;

public class SensorTest {
    private SmartHome_Sensor testSensor;

    public SensorTest() throws Exception {
        this.testSensor = new SmartHome_Sensor("localhost", 5000, "testLocation", 1000,1);
    }

    @Test
    public void testSensorData() throws InterruptedException{
        testSensor.sendData();
    }
}

