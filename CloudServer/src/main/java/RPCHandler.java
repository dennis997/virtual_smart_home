import gen.SensorResource;
import gen.SensorResourceService;

/**
 * RPCHandler represents the handler for RPC-Thrift Requests and their called methods
 */
public class RPCHandler implements SensorResourceService.Iface {
    // TODO: Instantiating DB instance here and persist in persistSensorData
    private DBConnector dbConnector;

    /**
     * RPCHandler constructor instantiates a new DBConnector object to use it in the following RPC-methods
     * @throws Exception
     */
    public RPCHandler() {
        dbConnector = new DBConnector();
    }

    /**
     *
     * @param resource object being transmitted via RPC-Thrift
     * @return status whether persisting the object was successful or not
     */
    @Override
    public boolean persistSensorData(SensorResource resource) {
        return dbConnector.persist(resource);
    }

    /**
     * testConnection is a simple method to check the RPC-Thrift connectivity
     * @return status whether RPC-Request made it to the Cloudserver
     */
    @Override
    public boolean testConnection() {
        return true;
    }
}
