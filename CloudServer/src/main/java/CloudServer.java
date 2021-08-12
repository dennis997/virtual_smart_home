import gen.SensorResourceService;
import org.apache.log4j.BasicConfigurator;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

public class CloudServer {
    private TThreadPoolServer threadPoolserver;
    private TServerTransport serverTransport;
    private int cloudServerPort;

    public CloudServer() throws TTransportException, InterruptedException {

        try {
            if (System.getenv("THRIFT_SERVER_PORT") != null) {
                cloudServerPort = Integer.parseInt(System.getenv("THRIFT_SERVER_PORT"));
            } else {
                cloudServerPort = 9002;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            BasicConfigurator.configure();
        serverTransport = new TServerSocket(cloudServerPort);
        try {
            threadPoolserver = createServer(serverTransport);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TThreadPoolServer createServer(TServerTransport serverTransport) throws Exception {
        return new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport)
                .processor(new SensorResourceService.Processor<>(new RPCHandler())));
    }

    public void start() throws TTransportException, InterruptedException {
        new Thread(() -> {
            try {
                this.threadPoolserver.serve();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println("[SERVICEPROVIDER] Server is up and running...");
    }

    public void shutDown() {
        if (threadPoolserver != null && threadPoolserver.isServing()) {
            threadPoolserver.stop();
            System.out.println("[SERVICEPROVIDER] Server stopped...");
        }
    }
}
