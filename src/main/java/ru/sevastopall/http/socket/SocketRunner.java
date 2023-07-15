package main.java.ru.sevastopall.http.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class SocketRunner {

    public static void main(String[] args) throws IOException {
        InetAddress inetAd = Inet4Address.getByName("localhost");
        try (Socket socket = new Socket(inetAd, 7777);
             var outputStream = new DataOutputStream(socket.getOutputStream());
             var inputStream = new DataInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                String request = scanner.nextLine();
                outputStream.writeUTF(request);
                System.out.println("Response from server " + inputStream.readUTF());
            }
        }
    }
}
