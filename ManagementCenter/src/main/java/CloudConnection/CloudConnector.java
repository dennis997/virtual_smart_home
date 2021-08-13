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
    private static SensorResourceService.Client client;
    private static TTransport transport;
    private static TProtocol protocol;


    public CloudConnector(String serverName, int thriftServerPort) {
            try {
                System.out.println("Servername: " + serverName + " Sererport: " + thriftServerPort);
                transport = new TSocket(serverName, thriftServerPort);
                transport.open();
                protocol = new TBinaryProtocol(transport);
                client = new SensorResourceService.Client(protocol);
                System.out.println("[CloudConnector] RPC connection established!");
            } catch (TTransportException e) {
                System.out.println("[CloudConnector] RPC connection failed!");
            }
        }

    public void sendSensorData(SensorData sensorData) {
        SensorResource sensorResource = new SensorResource();
        sensorResource.location = sensorData.getLocation();
        sensorResource.timestamp = sensorData.getTimestamp();
        sensorResource.brightness = sensorData.getBrightness();
        sensorResource.temp = sensorData.getTemp();
        sensorResource.volume = sensorData.getVolume();
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







