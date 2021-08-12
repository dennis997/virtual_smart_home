import org.apache.thrift.transport.TTransportException;

public class Main {
    public static void main(String[] args) throws TTransportException, InterruptedException {
        CloudServer server = new CloudServer();
        server.start();
    }
}

