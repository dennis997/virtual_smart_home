import Entities.SensorData;
import gen.SensorResource;
import gen.SensorResourceService;
import org.apache.thrift.TException;

public class RPCHandler implements SensorResourceService.Iface {
    // TODO: Instantiating DB instance here and persist in persistSensorData
    private DBConnector dbConnector;

    public RPCHandler() throws Exception {
        dbConnector = new DBConnector();
    }

    @Override
    public boolean persistSensorData(SensorResource resource) {
        return dbConnector.persist(resource);
    }

    @Override
    public boolean testConnection() throws TException {
        return true;
    }
}
