package CloudConnection;
import Entities.SensorData;
import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TTransportException;
import gen.*;

/**
 * CloudConnector is the communication endpoint for the RPC-Thrift connection between ManagementCenter and CloudServer
 * RPC-Calls are being transmitted via TBinaryProtocol
 */
public class CloudConnector {
    private static SensorResourceService.Client client;
    private static TTransport transport;
    private static TProtocol protocol;

    /**
     *
     * The CloudConnector constructor initializes the RPC-Thrift connection via the corresponding socket or default hardcoded values for IDE-testing
     * @param serverName hostname to be used for binding
     * @param thriftServerPort port to be binded
     */
    public CloudConnector(String serverName, int thriftServerPort) {
            try {
                System.out.println("Servername: " + serverName + " Serverport: " + thriftServerPort);
                transport = new TSocket(serverName, thriftServerPort);
                transport.open();
                protocol = new TBinaryProtocol(transport);
                client = new SensorResourceService.Client(protocol);
                System.out.println("[CloudConnector] RPC connection established!");
            } catch (TTransportException e) {
                System.out.println("[CloudConnector] RPC connection failed!");
            }
        }

    /**
     * Parses the passed SensorData object to a sensorResouce object for RPC-Thrift transmission and sends it out
     * @param sensorData to be transmitted resp. persisted in DB
     */
    public void sendSensorData(SensorData sensorData) {
        long measuredTime = 0;
        SensorResource sensorResource = new SensorResource();
        sensorResource.location = sensorData.getLocation();
        sensorResource.timestamp = sensorData.getTimestamp();
        sensorResource.brightness = sensorData.getBrightness();
        sensorResource.temp = sensorData.getTemp();
        sensorResource.volume = sensorData.getVolume();
        sensorResource.humidity = sensorData.getHumidity();
        try {
            client.persistSensorData(sensorResource);
            transport.flush();
        }
        catch (TException e) {
            e.printStackTrace();
            // TODO: Handling Reconnection and Connection fails in P5
        }
    }
}







