package Client;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Client {
    private static String host = "127.0.0.1";
    private BufferedReader fromServer;
    private PrintWriter toServer;


    public static void main(String[] args) {
        try {
            new Client().setUpNetworking();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpNetworking() throws Exception {
        @SuppressWarnings("resource")
        Socket socket = new Socket(host, 4242);
        System.out.println("Connecting to... " + socket);
        fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        toServer = new PrintWriter(socket.getOutputStream());
    }

    protected void startUpdateProcessing() {
        Thread readerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                String input;
                try {
                    while ((input = fromServer.readLine()) != null) {
                        System.out.println("From server: " + input);
                        processRequest(input);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        readerThread.start();
    }

    protected void processRequest(String input) {
        // change gson to History object

        // process request
    }

    protected void sendToServer(String string) {
        System.out.println("Sending to server: " + string);
        toServer.println(string);
        toServer.flush();
    }

}




