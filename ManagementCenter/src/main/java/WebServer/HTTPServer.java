package WebServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class HTTPServer implements Runnable {
    private ServerSocket httpSocket;

    public HTTPServer(int serverPort) throws Exception {
        this.httpSocket = new ServerSocket(serverPort);
    }

    public void listen() throws Exception {
        System.out.println("[HTTPServer] Listening on Port " + this.httpSocket.getLocalPort());
        while (true) {
            // Client Handler
            // TODO: Choose and instantiate Handler here to handle different types of requests
            // TODO: Create new thread for each request --> execute run() for every handler
            try (Socket client = httpSocket.accept()) {
                handleClient(client);
            };
        }
    }

    private static void handleClient(Socket client) throws Exception {
        System.out.println("[HTTPServer] New client "+client.toString());
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        StringBuilder requestBuilder = new StringBuilder();
        String line;
        while (!(line = bufReader.readLine()).isBlank()) {
            requestBuilder.append(line + "\r\n");
        }
        String request = requestBuilder.toString();
        System.out.println("[" + client.toString() + "]" + request);
        parseHTTPRequest(request);
        sendClientResponse(client);
    }

    private static void parseHTTPRequest(String request) {
        String[] singleLineArray = request.split("\r\n");
        String[] requestLine = singleLineArray[0].split(" ");
        String method = requestLine[0];
        String path = requestLine[1];;
        String version = requestLine[2];;
        String host = requestLine[1].split(" ")[1];

        List<String> httpHeaders = new ArrayList<>();
        for (int headerCount = 2; headerCount < singleLineArray.length; headerCount++) {
            String header = singleLineArray[headerCount];
            httpHeaders.add(header);
        }

        StringBuilder requestLogEntry = new StringBuilder();
        requestLogEntry.append(method + " | ");
        requestLogEntry.append(path + " | ");
        requestLogEntry.append(version+ " | ");
        requestLogEntry.append(host + " | ");
        System.out.println(requestLogEntry);
    }

    private static void sendClientResponse(Socket client) throws IOException {
        OutputStream clientOutput = client.getOutputStream();
        clientOutput.write("HTTP/1.1 200 OK\r\n".getBytes());
        clientOutput.write(("ContentType: text/html\r\n").getBytes());
        clientOutput.write("\r\n".getBytes());
        clientOutput.write("<b>It works!</b>".getBytes());
        clientOutput.write("\r\n\r\n".getBytes());
        clientOutput.flush();
        client.close();
    }


    @Override
    public void run() {

    }
}
