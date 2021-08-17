package CloudConnection;
import Entities.SensorData;
import gen.SensorResourceService;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TTransportException;
import gen.SensorResource;

import java.util.ArrayList;
import java.util.List;

/**
 * CloudConnector is the communication endpoint for the RPC-Thrift connection between ManagementCenter and CloudServer
 * RPC-Calls are being transmitted via TBinaryProtocol
 */
public class CloudConnector {
    private static SensorResourceService.Client client;
    private static TTransport transport;
    private static TProtocol protocol;
    private static Logger logger;
    private String serverName;
    private int thriftServerPort;
    private List<SensorResource> queuedData;

    /**
     *
     * The CloudConnector constructor initializes the RPC-Thrift connection via the corresponding socket or default hardcoded values for IDE-testing
     * @param serverName hostname to be used for binding
     * @param thriftServerPort port to be binded
     */
    public CloudConnector(String serverName, int thriftServerPort) {
        this.serverName = serverName;
        this.thriftServerPort = thriftServerPort;
        queuedData = new ArrayList<SensorResource>();
        logger = Logger.getLogger(CloudConnector.class);
        BasicConfigurator.configure();
        logger.setLevel(Level.INFO);
        try {
            System.out.println("Servername: " + serverName + " Serverport: " + thriftServerPort);
            transport = new TSocket(serverName, thriftServerPort);
            transport.open();
            protocol = new TBinaryProtocol(transport);
            client = new SensorResourceService.Client(protocol);
            logger.info("RPC connection established!");
        } catch (TTransportException e) {
            logger.error("RPC connection failed!");;
        }
        }


    /**
     * Opens dedicated thread to deal with each lost connection due to waiting times
     * @throws InterruptedException
     */
    public void waitForReconnect() throws InterruptedException {
        new Thread(() -> {
            try {
                logger.info("Reconnecting...");
                reconnect();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Tries to reconnect to RPC-Endpoint 5 times with 5s timeout. If successful, the retained datasets are being
     * sent out.
     * @throws InterruptedException
     */
    public void reconnect() throws InterruptedException {
        int count = 0;
        int timeout = 5000;
        while (count < 20) {
            try {
                transport = new TSocket(serverName, thriftServerPort);
                transport.open();
                protocol = new TBinaryProtocol(transport);
                client = new SensorResourceService.Client(protocol);
                logger.info("RPC Connection re-established!");
                sendRetainedData();
                logger.info("Retained sensordata was sent out to cloudserver!");
                break;
            } catch (TTransportException e) {
                logger.error("Attempt " + count +  " to re-establish RPC Connection failed!");
                count++;
                if (count == 20) {
                    logger.error("Lost connection to RPC-Endpoint!");
                }
            }
            Thread.sleep(timeout);
        }
    }

    /**
     * Sends out retained sensordata and prints out informational message from which mc and location it comes.
     */
    public void sendRetainedData() throws TTransportException, InterruptedException {
        try {
            for (SensorResource res : queuedData) {
                client.persistSensorData(res);
                transport.flush();
            }
            queuedData.stream().forEach(res ->
                    logger.info(res.topic + " is now transmitting sensordata from [" + res.topic + "] " + res.location +" again"));
            queuedData.clear();

        } catch (Exception e) {
            logger.error("Failed to sent out retained sensordata!");
        }
    }

    /**
     * Retransmits sensorData to cloudserver
     * @param sensorResource to be retransmitted
     * @return status if retransmission was successful
     */
    public Boolean retransmit(SensorResource sensorResource) {
        boolean persisted = false;
        logger.info("Retransmission of sensordata from [" + sensorResource.topic + "] " +sensorResource.location + " in progress...");
        try {
            persisted = client.persistSensorData(sensorResource);
            transport.flush();
            return persisted;
        } catch (TException e) {
            logger.error("Retransmission of sensordata from [" + sensorResource.topic + "] " +sensorResource.location + " failed!");
            return persisted;
        }
    }

    /**
     * Parses the passed SensorData object to a sensorResouce object for RPC-Thrift transmission and sends it out
     * @param sensorData to be transmitted resp. persisted in DB
     */
    public void sendSensorData(SensorData sensorData) throws InterruptedException {
        boolean check;
        SensorResource sensorResource = new SensorResource();
        sensorResource.location = sensorData.getLocation();
        sensorResource.timestamp = sensorData.getTimestamp();
        sensorResource.brightness = sensorData.getBrightness();
        sensorResource.temp = sensorData.getTemp();
        sensorResource.volume = sensorData.getVolume();
        sensorResource.humidity = sensorData.getHumidity();
        sensorResource.topic = sensorData.getTopic();
        try {
            check = client.persistSensorData(sensorResource);
            if (check) {
                logger.info("RPC transmitted successfully!");
            } else {
                retransmit(sensorResource);
            }
            transport.flush();
        }
        catch (TException te) {
            queuedData.add(sensorResource);
            waitForReconnect();
        }
    }


}







