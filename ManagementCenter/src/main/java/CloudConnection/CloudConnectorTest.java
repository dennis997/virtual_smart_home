package CloudConnection;
import Entities.SensorData;
import org.testng.annotations.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CloudConnectorTest {
    private CloudConnector cloudConnector;

    public CloudConnectorTest() throws Exception {
        this.cloudConnector = new CloudConnector("localhost", 9002);
    }

    @Test
    public void testPersistingPerformance() throws Exception {

        long sum = 0;
        for (int i = 0; i < 20; i++) {
            long startTime = System.currentTimeMillis();
            cloudConnector.sendSensorData(new SensorData("Testlocation", "TestTimestamp", 0, 0, 0, 0, "testTopic"));
            long time = System.currentTimeMillis() - startTime;
            System.out.println("Finished Round: " + (i+1) + " ("+time+"ms)");
            sum += time;
        }
        System.out.println("Finished!");
        System.out.println("Average time out of 20 test runs: " + sum/20 + "ms");
        assertTrue(sum/20 < 5000);
    }
}
