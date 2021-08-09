package WebServer;
import Entities.SensorData;
import SensorProcessor.UDPReceiver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.*;

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
        handleClientResponse(client, parseHTTPRequest(request));
    }

    private Map<String, String> parseHTTPRequest(ArrayList<String> request) {

        Map<String, String> requestMap = new HashMap<String, String>();
        String[] requestLine = request.get(0).split(" ");

        requestMap.put("method", requestLine[0]);
        requestMap.put("path", requestLine[1]);
        requestMap.put("version", requestLine[2]);
        requestMap.put("host", request.get(1).split(" ")[1]);

        StringBuilder requestLogEntry = new StringBuilder();
        requestLogEntry.append("[HTTPServer] ");
        requestLogEntry.append(requestMap.get("method") + " | ");
        requestLogEntry.append(requestMap.get("path") + " | ");
        requestLogEntry.append(requestMap.get("version") + " | ");
        requestLogEntry.append(requestMap.get("host") + " | ");
        System.out.println(requestLogEntry);

        return requestMap;
    }

    private String getAll(){
        return new GsonBuilder().setPrettyPrinting().create().toJson(this.udpReceiver.getSensorData());
    }

    private String getHistory(String location) throws Exception{
        List<SensorData> filteredSensorData = this.udpReceiver.getSensorData().stream().filter(
                sensorData -> sensorData.getLocation().equals(location)).collect(Collectors.toList());
        if(filteredSensorData.isEmpty()){
            throw new Exception();
        }
        return new GsonBuilder().setPrettyPrinting().create().toJson(filteredSensorData);
    }
    private String getCurrent(String location) throws Exception{
        List<SensorData> filteredSensorData = this.udpReceiver.getSensorData().stream().filter(
                sensorData -> sensorData.getLocation().equals(location)).collect(Collectors.toList());
            SensorData currentData = filteredSensorData.get(filteredSensorData.size() - 1);
        return new GsonBuilder().setPrettyPrinting().create().toJson(currentData);
    }


    private void handleClientResponse(Socket client, Map<String, String> requestMap) throws Exception {
        String path = requestMap.get("path");
        System.out.println(path);
        String[] attributes = path.split("/");
        try {
            if (attributes.length == 0) {
                sendClientResponse(client, "200 OK", "application/json", getAll());
            } else {
                switch (attributes[1]) {
                    case "history": {
                        sendClientResponse(client, "200 OK", "application/json", getHistory(attributes[2]));
                        break;
                    }
                    case "current": {
                        sendClientResponse(client, "200 OK", "application/json", getCurrent(attributes[2]));
                        break;
                    }
                    default:
                        sendClientResponse(client, "400 Bad Request", "text/html", "<h1>Nope</h1>");
                }
            }
        }
        catch (Exception e){
            sendClientResponse(client, "400 Bad Request", "text/html", "<h1>Sensor not found</h1>");
        }
    }

    private static void sendClientResponse(Socket client, String status, String contentType, String output) throws IOException {
        // TODO: Send different responses according to request. No POST Requests or invalid Requests
        // TODO: Switch case for different paths and function calls to retrieve the respective sensorData
        OutputStream clientOutput = client.getOutputStream();
        clientOutput.write(("HTTP/1.1" + status + "\r\n").getBytes());
        clientOutput.write(("ContentType:" + contentType + "\r\n").getBytes());
        clientOutput.write("\r\n".getBytes());
        clientOutput.write(output.getBytes());
        clientOutput.write("\r\n\r\n".getBytes());
        clientOutput.flush();
        client.close();
    }
}
