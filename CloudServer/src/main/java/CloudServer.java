import gen.SensorResourceService;
import org.apache.log4j.BasicConfigurator;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CloudServer {
    private TThreadPoolServer threadPoolserver;
    private TServerTransport serverTransport;

    public CloudServer(int port) throws TTransportException, InterruptedException {
        BasicConfigurator.configure();
        serverTransport = new TServerSocket(port);
        threadPoolserver = createServer(serverTransport);
    }

    public TThreadPoolServer createServer(TServerTransport serverTransport) throws InterruptedException {
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
