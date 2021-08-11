package CloudConnection;
import Entities.SensorData;
import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TTransportException;
import gen.*;

public class CloudConnector {
    private SensorResourceService.Client client;
    private TTransport transport;
    private TProtocol protocol;
    private int serviceProviderPort;
    private String serviceProviderIp;

    public CloudConnector(String serviceProviderIp, int serviceProviderPort) throws InterruptedException{

            try {
                transport = new TSocket(serviceProviderIp, serviceProviderPort);
                transport.open();
                protocol = new TBinaryProtocol(transport);

                SensorResourceService.Client client = new SensorResourceService.Client(protocol);
                System.out.println("[CloudConnector] RPC connection established!");
            } catch (TTransportException e) {
                System.out.println("[CloudConnector] RPC connection failed!");
            }
        }

    public void sendSensorData(SensorData sensorData) throws InterruptedException {
        boolean persisted = false;
        SensorResource sensorResource = new SensorResource();
        sensorResource.location = sensorData.getLocation();
        sensorResource.timestamp = sensorData.getTimestamp();
        sensorResource.brightness = sensorData.getBrightness();
        sensorResource.temp = sensorData.getTemp();
        sensorResource.volume = sensorData.getVolume();

        try {
             persisted = client.persistSensorData(sensorResource);
        }
        catch (TException e) {
            // TODO: Handling Reconnection and Connection fails in P5
        }
    }
}







