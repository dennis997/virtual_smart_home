import org.junit.Test;

public class SensorTest {
    private SmartHomeSensor testSensor;

    public SensorTest() throws Exception {
        this.testSensor = new SmartHomeSensor("localhost", 5000, "testLocation", 1000);
    }

    @Test
    public void testSensorData() throws InterruptedException {
        testSensor.sendData();
    }
}

