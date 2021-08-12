import org.apache.thrift.transport.TTransportException;

public class Main {
    public static void main(String[] args) throws TTransportException, InterruptedException {
        try {
            CloudServer server = new CloudServer(9002);
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

