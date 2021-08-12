import gen.SensorResourceService;
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
    private TMultiplexedProcessor processor;

    public CloudServer(int port) throws TTransportException {
        serverTransport = new TServerSocket(port);
        processor = new TMultiplexedProcessor();
        threadPoolserver = (TThreadPoolServer) createServer(serverTransport, processor);
    }

    public TServer createServer(TServerTransport serverTransport, TMultiplexedProcessor processor) {
        TThreadPoolServer.Args args = new TThreadPoolServer.Args(serverTransport).processor(processor);
        args.stopTimeoutVal = 3;
        args.stopTimeoutUnit = TimeUnit.SECONDS;
        SynchronousQueue<Runnable> executorQueue = // NOSONAR
                new SynchronousQueue<Runnable>();
        ExecutorService executorService = new ThreadPoolExecutor(args.minWorkerThreads, args.maxWorkerThreads,
                60, TimeUnit.SECONDS, executorQueue);
        args.executorService = executorService;
        return new TThreadPoolServer(args);
    }

    public void start() throws TTransportException, InterruptedException {
        this.threadPoolserver.serve();
        System.out.println("[SERVICEPROVIDER] Server is up and running...");
    }

    public void shutDown() {
        if (threadPoolserver != null && threadPoolserver.isServing()) {
            threadPoolserver.stop();
            System.out.println("[SERVICEPROVIDER] Server stopped...");
        }
    }
}
