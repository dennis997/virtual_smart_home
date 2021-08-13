import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SensorTest {
    private SmartHomeSensor testSensor;

    public SensorTest() throws Exception {
        this.testSensor = new SmartHomeSensor("localhost", 5000, "testLocation", 1000,1);
    }

    @Test
    public void testSensorData() throws InterruptedException {

        ArrayList<Long> timeList = new ArrayList<Long>();
        long sum = 0;
        for (int i = 0; i < 20; i++) {
            long startTime = System.currentTimeMillis();
            testSensor.sendData(10000);
            long time = System.currentTimeMillis() - startTime;
            System.out.println("Finished Round: " + (i+1) + " ("+time+"ms)");
            sum += time;
        }
        System.out.println("Finished!");
        System.out.println("Average time out of 20 test runs: " + sum/20 + "ms");
        assertTrue(sum/20 < 5000);
    }
}

