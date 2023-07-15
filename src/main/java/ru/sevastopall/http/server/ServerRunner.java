package ru.sevastopall.http.server;

public class ServerRunner {

    public static void main(String[] args) {
        ru.sevastopall.http.server.HttpServer httpServer =new  ru.sevastopall.http.server.HttpServer(9000, 100);
        httpServer.run();
    }
}
