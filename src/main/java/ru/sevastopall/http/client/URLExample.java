package ru.sevastopall.http.client;

import java.io.IOException;
import java.net.URL;

public class URLExample {

    public static void main(String[] args) throws IOException {
        var url = new URL("file:C:\\Users\\School_Laptop  3\\IdeaProjects\\java_http_training\\src\\main\\java\\ru\\sevastopall\\http\\socket\\DatagramRunner.java");
        var urlConnection = url.openConnection();

        System.out.println(new String(urlConnection.getInputStream().readAllBytes()));


    }

    private static void checkGoogle() throws IOException {
        var url = new URL("https://www.google.com");
        var urlConnection = url.openConnection();
        System.out.println( );
    }

}
