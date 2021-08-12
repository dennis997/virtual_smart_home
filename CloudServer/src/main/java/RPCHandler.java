import Entities.SensorData;
import gen.SensorResource;
import gen.SensorResourceService;
import org.apache.thrift.TException;

public class RPCHandler implements SensorResourceService.Iface {
    // TODO: Instantiating DB instance here and persist in persistSensorData
    // private DB DB

    public RPCHandler() throws InterruptedException {
        // new DBHandler Instance here
    }

    @Override
    public boolean persistSensorData(SensorResource resource) throws TException {
        // TODO: Calls have to be synchronized due to write conflict issues
        SensorData testSensorData = new SensorData(
                resource.location,
                resource.timestamp,
                resource.humidity,
                resource.temp,
                resource.brightness,
                resource.volume);

        System.out.println(testSensorData);
        return false;
    }

    @Override
    public boolean testConnection() throws TException {
        return true;
    }
}
