package main.java.ru.sevastopall.http.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class SocketServerRunner {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(7777);
             var acceptSocket = serverSocket.accept();
             DataOutputStream dataOutputStream = new DataOutputStream(acceptSocket.getOutputStream());
             DataInputStream dataInputStream = new DataInputStream(acceptSocket.getInputStream());
             var scanner = new Scanner(System.in)) {
            var request = dataInputStream.readUTF();
            while (!"stop".equals(request)) {
                System.out.println("Client request " + request);
                var response = scanner.nextLine();
                dataOutputStream.writeUTF(response);
                request = dataInputStream.readUTF();
            }


        }
    }

}
