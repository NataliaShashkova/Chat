package ru.geekbrains;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server153 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        Socket socket = null;

        try {
            serverSocket = new ServerSocket(1356);
            System.out.println("The server is ready to work");
            socket = serverSocket.accept();
            System.out.println("The client is connected to the server");

            final Scanner in = new Scanner(socket.getInputStream());
            final PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            final Scanner consol = new Scanner(System.in);



            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String str = in.nextLine();
                        if (str.equals("/end")){
                            out.println("/end");
                            break;
                        }
                        System.out.println("Клиент: "+ str);
                    }
                }
            });
            t1.start();

//Поток обработки сообщений из консоли
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        System.out.println("Please, type the message: ");
                        String str = consol.nextLine();
                        out.println(str);//
                    }
                }
            });
            t2.setDaemon(true);
            t2.start();


            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            serverSocket.close();
            socket.close();
        }


    }
}
