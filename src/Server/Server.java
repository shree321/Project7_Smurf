package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.io.File;
import java.util.Scanner;


import com.google.gson.Gson;


class Server extends Observable {
    private static ArrayList<Item_Info> item_info_arr= new ArrayList<Item_Info>();
    public static void main(String[] args) throws Exception{
        parseInputFile();
        new Server().runServer();
    }

    /*
    reads the input item file
    stores the item name, min price, and buy it now price in an ArrayList
     */
    public static void parseInputFile()  throws Exception{
        File file = new File("C:\\EE 422C\\Projects\\Project7_Smurf\\src\\Server\\InputItems.txt");
        Scanner sc = new Scanner(file);

        while(sc.hasNext()) {
            Item_Info i = new Item_Info();
            // copy file to server and then access fields
            i.item_name = (sc.next());
            i.item_min_bid_price = Double.parseDouble(sc.next());
            i.buy_now_price = Double.parseDouble(sc.next());
            item_info_arr.add(i);
            // store i in an array list
            //System.out.println(sc.nextLine());
        }
        for (Item_Info e: item_info_arr) {
            System.out.println(e.item_name + e.item_min_bid_price + e.buy_now_price);
        }
    }

    private void runServer() {
        try {
            setUpNetworking();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    private void setUpNetworking() throws Exception {
        @SuppressWarnings("resource")
        ServerSocket serverSock = new ServerSocket(4242);
        while (true) {
            Socket clientSocket = serverSock.accept();
            System.out.println("Connecting to... " + clientSocket);

            ClientHandler handler = new ClientHandler(this, clientSocket);
            Gson gson = new Gson();
            handler.sendToClient(gson.toJson(item_info_arr)); // send to specific client


            this.addObserver(handler);

            Thread t = new Thread(handler);
            t.start();
        }
    }

    protected void processRequest(String input){
        // reader thread in clienthandler and other thread
        // they are waiting for a json toString()
        // json to an object so deserialization




        try {
            // process the request
            // send message object
            // should also be converted to gson string


            this.setChanged();
            this.notifyObservers(input); // part of the observable class
        }
         catch(Exception e){
                e.printStackTrace();
            }
        }

    }

