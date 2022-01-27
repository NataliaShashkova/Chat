package ru.geekbrains;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private static final int PORT = 1356;
    private static final String SERVER_ADDR = "localhost";


    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDR, PORT)) {
            System.out.println("Client started");

            new Thread(() -> {
                try (Scanner scSocket = new Scanner(socket.getInputStream())) {
                    while (true) {
                        String str;
                        if (scSocket.hasNext()) {
                            str = scSocket.nextLine();
                        } else {
                            str = "/end";
                        }

                        if (str.equals("/end")) {
                            System.out.println("Server disconnected");
                            break;
                        }
                        System.out.println("Server:" + str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            try (Scanner sc = new Scanner(System.in);
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                while (true) {
                    String str = sc.nextLine();
                    if (str.equals("/end")) {
                        System.out.println("Client stopped");
                        out.println("/end");
                        break;
                    }
                    out.println(str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
