package ru.geekbrains;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client1 {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        try {
            socket = new Socket("localhost",0103);
            final Scanner in = new Scanner(socket.getInputStream());
            final PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            final Scanner consol = new Scanner(System.in);



            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String str = in.nextLine();
                        if (str.equals("/end")) {
                            out.println("/end");
                            break;
                        }
                        System.out.println("Сервер: "+str);
                    }

                }
            });

            t1.start();

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
            socket.close();
        }

//


    }

}
