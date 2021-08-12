import org.junit.Test;
import static org.junit.Assert.*;

public class SensorTest {
    private SmartHomeSensor testSensor;

    public SensorTest() throws Exception {
        this.testSensor = new SmartHomeSensor("localhost", 5000, "testLocation", 1000,1);
    }

    @Test
    public void testSensorData() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        testSensor.sendData();
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Sent out 10000 datasets ind " + estimatedTime + "ms!");
        assertTrue(estimatedTime < 5000);
    }
}

