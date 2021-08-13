import gen.SensorResourceService;
import org.apache.log4j.BasicConfigurator;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * CloudServer is the communication endpoint for the RPC-Thrift connection between ManagementCenter and CloudServer
 * RPC-Calls are being transmitted via TBinaryProtocol
 */
public class CloudServer {
    private TThreadPoolServer threadPoolserver;
    private TServerTransport serverTransport;
    private int cloudServerPort;

    /**
     * CloudServer Constructor initializes the Thrift Socket, Logging and creates and starts the TThreadPoolServer
     * @throws TTransportException
     */
    public CloudServer() throws TTransportException {

        try {
            if (System.getenv("THRIFT_SERVER_PORT") != null) {
                cloudServerPort = Integer.parseInt(System.getenv("THRIFT_SERVER_PORT"));
            } else {
                cloudServerPort = 9002;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Initializes the Log4j library with the correct appenders to display logs
        BasicConfigurator.configure();
        serverTransport = new TServerSocket(cloudServerPort);
        try {
            threadPoolserver = createServer(serverTransport);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * A TThreadPoolServer is being created to handle incoming RPC-Thrift Requests in separate Threads
     * @param serverTransport
     * @return TServerSocket object with the corresponding port to listen to
     * @throws Exception
     */
    public TThreadPoolServer createServer(TServerTransport serverTransport) throws Exception {
        return new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport)
                .processor(new SensorResourceService.Processor<>(new RPCHandler())));
    }

    /**
     * Starts the TThreadPoolserver with blocking call so a new Thread is being spawned for this service module
     */
    public void start(){
        new Thread(() -> {
            try {
                this.threadPoolserver.serve();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println("[SERVICEPROVIDER] Server is up and running...");
    }

    /**
     * Stops the TThreadPoolserver
     */
    public void shutDown() {
        if (threadPoolserver != null && threadPoolserver.isServing()) {
            threadPoolserver.stop();
            System.out.println("[SERVICEPROVIDER] Server stopped...");
        }
    }
}
