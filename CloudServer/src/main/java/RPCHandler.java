import gen.SensorResource;
import gen.SensorResourceService;
import org.apache.thrift.TException;

public class RPCHandler implements SensorResourceService.Iface {
    @Override
    public boolean persistSensorData(SensorResource resource) throws TException {
        return false;
    }

    @Override
    public boolean testConnection() throws TException {
        return false;
    }
}
