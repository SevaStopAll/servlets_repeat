package ru.sevastopall.http.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    private final ExecutorService pool;
    private final int port;
    private boolean stopped;


    public HttpServer(int port, int poolSize) {

        this.port = port;
        pool = Executors.newFixedThreadPool(poolSize);
    }

    public void run() {
        try {
            ServerSocket server = new ServerSocket(port);
            while(!stopped) {
                Socket socket = server.accept();
                System.out.println("Socket accepted");
                pool.submit(() -> processSocket(socket));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processSocket(Socket socket) {
        try (socket;
             InputStream inputStream = new DataInputStream(socket.getInputStream());
             OutputStream outputStream = new DataOutputStream(socket.getOutputStream())) {

            //step 1 handle request
            System.out.println("Request" + new String(inputStream.readNBytes(400)));

            Thread.sleep(10000);
            //step2 handle response
            byte[] body = Files.readAllBytes(Path.of("src/main/resources", "example.html"));
            byte[] headers = """
                    HTTP/1.1 200 OK
                    content-type: text/html
                    content-length: %s
                    """.formatted(body.length).getBytes();
            outputStream.write(headers);
            outputStream.write(System.lineSeparator().getBytes());
            outputStream.write(body);
        } catch (IOException | InterruptedException e) {
            //TODO: 7/12/23 log error message
            e.printStackTrace();
        }
    }

        public void setStopped(boolean stopped) {
            this.stopped = stopped;
        }
}

