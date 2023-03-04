package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        final int port = 8080;

        System.out.println("Server start");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ) {
                    System.out.println("New connection accepted");
                    countintToFive(out, in);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void countintToFive(PrintWriter out, BufferedReader in) throws IOException {
        out.println("Write your name:");

        String name = in.readLine();
        out.println("Hello, " + name + "! Do you want to count to 5? (yes/no)");

        while (true) {
            String answer = in.readLine();

            if (answer.equals("yes")) {
                int count = 1;
                out.println("Let's start counting: " + count);
                while (count <= 5) {
                    String number = in.readLine();
                    count = count + 2;
                    if (count == 5) {
                        out.println(count + ". Done!");
                        break;
                    } else {
                        out.println(count + ". Write next number:");
                    }
                }
            } else if (answer.equals("no")) {
                out.println("It's sad that no one is playing with me");
                break;
            } else {
                out.println("In my opinion, you made a mistake in choosing an answer. Try again");
            }
        }
    }
}
