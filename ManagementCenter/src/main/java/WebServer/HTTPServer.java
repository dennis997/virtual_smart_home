package WebServer;
import Entities.SensorData;
import SensorProcessor.UDPReceiver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class HTTPServer{
    private ServerSocket httpSocket;
    private UDPReceiver udpReceiver;

    public HTTPServer(UDPReceiver udpReceiver, int serverPort) throws Exception {
        this.httpSocket = new ServerSocket(serverPort);
        this.udpReceiver = udpReceiver;
    }

    public void listen() throws Exception {
        System.out.println("[HTTPServer] Listening on Port " + this.httpSocket.getLocalPort());
        while (true) {
            // Client Handler
            // TODO: Choose and instantiate Handler here to handle different types of requests
            // TODO: Create new thread for each request --> execute run() for every handler
            try (Socket client = httpSocket.accept()) {
                handleClient(client);
            }
        }
    }

    private void handleClient(Socket client) throws Exception {
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        StringBuilder requestBuilder = new StringBuilder();
        ArrayList<String> request = new ArrayList<String>();
        String line = bufReader.readLine();
        while (line != null && !line.isEmpty()) {
            request.add(line);
            line = bufReader.readLine();
        }
        if (request.isEmpty())
            return;
        parseHTTPRequest(request);
        sendClientResponse(client);
        for (SensorData data : this.udpReceiver.getSensorData()) {
            System.out.println(data);
        }
    }

    private void parseHTTPRequest(ArrayList<String> request) {
        String[] requestLine = request.get(0).split(" ");
        String method = requestLine[0];
        String path = requestLine[1];
        String version = requestLine[2];
        String host = request.get(1).split(" ")[1];

        StringBuilder requestLogEntry = new StringBuilder();
        requestLogEntry.append("[HTTPServer] ");
        requestLogEntry.append(method + " | ");
        requestLogEntry.append(path + " | ");
        requestLogEntry.append(version+ " | ");
        requestLogEntry.append(host + " | ");
        System.out.println(requestLogEntry);
    }

    private void handleClientResponse(Socket client, String path) throws IOException {

    }

    private static void sendClientResponse(Socket client) throws IOException {
        // TODO: Send different responses according to request. No POST Requests or invalid Requests
        // TODO: Switch case for different paths and function calls to retrieve the respective sensorData
        OutputStream clientOutput = client.getOutputStream();
        clientOutput.write("HTTP/1.1 200 OK\r\n".getBytes());
        clientOutput.write(("ContentType: text/html\r\n").getBytes());
        clientOutput.write("\r\n".getBytes());
        clientOutput.write("<b>Hell Yeah!</b>".getBytes());
        clientOutput.write("\r\n\r\n".getBytes());
        clientOutput.flush();
        client.close();
    }
}
