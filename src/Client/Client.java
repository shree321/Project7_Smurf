package Client;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;
import java.net.Socket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Client extends Application {
    private static String host = "127.0.0.1";
    private BufferedReader fromServer;
    private PrintWriter toServer;

    private static ArrayList<Item_Info> items_auction_arr= new ArrayList<Item_Info>();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        try { new Client().setUpNetworking(); }
        catch(Exception e) { e.printStackTrace(); }
        TabPane tabPane = new TabPane();
        // Customer logs in with username& password or as a guest, then info gets added to Customer Info & Auction Drop-down menu
        //TODO: ORGANIZE TAB1
        Tab tab1 = new Tab("Login Page");
        GridPane gp1 = new GridPane();
        gp1.setStyle("-fx-background-color: lightblue");
        gp1.setPadding(new Insets(25,25,25,25));

        Text title = new Text();
        title.setText("Smurf Village Market Auction");
        title.setTranslateX(25);
        title.setTranslateY(0);
        gp1.getChildren().add(title);

        Text welcome = new Text();
        welcome.setText("~All proceeds will go to helping Grouchy Smurf smile more!~");
        welcome.setTranslateX(25);
        welcome.setTranslateY(25);
        gp1.getChildren().add(welcome);

        Text username = new Text();
        username.setText("Username:");
        username.setTranslateX(25);
        username.setTranslateY(50);
        gp1.getChildren().add(username);

        //      Second Stage creates a new window for the input text
        TextField user_input = new TextField();
        HBox user_input_box = new HBox(user_input);
        user_input_box.setTranslateX(100);
        user_input_box.setTranslateY(50);
        gp1.getChildren().add(user_input_box);


        Text password = new Text();
        password.setText("Password:");
        password.setTranslateX(25);
        password.setTranslateY(75);
        gp1.getChildren().add(password);

        PasswordField password_input = new PasswordField();
        password_input.setPromptText("Your password");
        HBox password_input_box = new HBox(password_input);
        password_input_box.setTranslateX(100);
        password_input_box.setTranslateY(75);
        gp1.getChildren().add(password_input_box);

        Text or = new Text();
        or.setText("OR Enter as a Guest Customer-->");
        or.setTranslateX(25);
        or.setTranslateY(125);
        gp1.getChildren().add(or);

        // Guest Button
        Button guest = new Button("Guest");
        gp1.getChildren().add(guest);
        guest.setTranslateX(215);
        guest.setTranslateY(125);
        guest.setOnAction(new EventHandler<ActionEvent>() {	//event

            @Override
            public void handle(ActionEvent event){

            }

        });

        Text market = new Text();
        market.setText("Have fun bidding on the Market fruits & produce:");
        market.setTranslateX(25);
        market.setTranslateY(225);
        gp1.getChildren().add(market);

        // add a split pane to have a background and icons
//        SplitPane splitPane = new SplitPane();
//        VBox leftControl  = new VBox();
//        VBox rightControl = new VBox(new Label("Choose a Smurf Customer Icon:"));
//        splitPane.getItems().addAll(leftControl, rightControl);
//        gp1.getChildren().add(splitPane);


        //        choose_icon.setTranslateX(400);
//        choose_icon.setTranslateY(50);
        // Choose a Smurf icon for the customer
        Text choose_icon = new Text();
        choose_icon.setText("Choose a Smurf Customer Icon:");
        choose_icon.setTranslateX(400);
        choose_icon.setTranslateY(50);
        gp1.getChildren().add(choose_icon);

        // Login Button
        Button login = new Button("Login");
        gp1.getChildren().add(login);
        login.setTranslateX(100);
        login.setTranslateY(300);

        Text actiontarget = new Text();
        gp1.getChildren().add(actiontarget);
        actiontarget.setTranslateX(160);
        actiontarget.setTranslateY(300);

        //Image Background
//        FileInputStream input = new FileInputStream("\"Users\\shree\\Downloads\\smurfvillage.JPEG\"");
//        Image image = new Image(input);
//        ImageView imageView = new ImageView(image);
//
//        HBox hbox = new HBox(imageView);
//        gp1.getChildren().add(hbox);

        final Label password_msg = new Label("");
        final Label username_msg = new Label("");

        login.setOnAction(new EventHandler<ActionEvent>() {	//event
            @Override
            public void handle(ActionEvent event){
                // get and store the username of the client into the History ArrayList
                if(user_input.getText()!="") {
                    // store the username with loggedin field as true

                }
                else {
                    username_msg.setText("Please enter a username to proceed to the auction");
                    username_msg.setTextFill(Color.rgb(210, 39, 30));
                }

                //determine if the password is valid
                if (!password_input.getText().equals("422C")) {
                    password_msg.setText("Your password is incorrect!");
                    password_msg.setTextFill(Color.rgb(210, 39, 30));
                } else {
                    password_msg.setText("Your password has been confirmed");
                    password_msg.setTextFill(Color.rgb(21, 117, 84));
                }
                password_input.clear();

                password_msg.setTranslateX(100);
                password_msg.setTranslateY(100);
                gp1.getChildren().addAll(password_msg);

                // process login
                actiontarget.setFill(Color.DARKBLUE);
                actiontarget.setText("Login button pressed");

            }
        });

        tab1.setContent(gp1);


        // Anytime a valid bid is placed, add the information to this tab at th top or bottom
        Tab tab2 = new Tab("Bid History"  , new Label("Displays the auction history for all bids places"));
        //tab2.setStyle("-fx-background-color: lightcyan");
        //GridPane gp2 = new GridPane();
        //gp2.setStyle("-fx-background-color: lightcyan");
        //gp2.setPadding(new Insets(25,25,25,25));
        tab2.setContent((new Rectangle(800,400, Color.LIGHTCYAN)));
        //tab2.setContent(gp2);



        // Have each new customer added to drop- down menu with a new tab created for each new customer
        // the customer tab isn't deleted but shows a Logged out option for their status so you can still see their history
        Tab tab3 = new Tab("Customer Info" , new Label("Displays all information and history for the selected customer"));
        //tab3.setContent((new Rectangle(800,400, Color.LIGHTSTEELBLUE)));
        // display all of the items in a drop-down menu that is read in from input file
        // item name, minimum bid price, and image selection based on the item name
        Tab tab4 = new Tab("Item Info");
        GridPane gp4 = new GridPane();
        gp4.setStyle("-fx-background-color: lightsteelblue");
        gp4.setPadding(new Insets(25,25,25,25));

        Text title4 = new Text();
        title4.setText("Displays updated information on the items auctioned off today");
        title4.setTranslateX(25);
        title4.setTranslateY(0);
        gp4.getChildren().add(title4);

        // drop down menu based on the Array sent by the Server
        ComboBox item_menu = new ComboBox();

        // TODO: add the item_names to the drop down menu
            for(Item_Info i: items_auction_arr) {
                item_menu.getItems().add(i.item_name);
                System.out.println(i.item_name);
            }

        HBox item_menu_box = new HBox(item_menu);
        item_menu_box.setTranslateX(25);
        item_menu_box.setTranslateY(25);
        gp4.getChildren().add(item_menu_box);

        tab4.setContent(gp4);

        //tab4.setContent((new Rectangle(800,400, Color.PALETURQUOISE)));
        // Allows each customer to select an item and enter a price
        // then it shows if it is valid or not
        Tab tab5 = new Tab("Auction Home Page" , new Label("Place your bid here"));
        // tab5.setContent((new Rectangle(800,400, Color.SKYBLUE)));

        tabPane.getTabs().add(tab1); tabPane.getTabs().add(tab2); tabPane.getTabs().add(tab3); tabPane.getTabs().add(tab4); tabPane.getTabs().add(tab5);

        VBox vBox = new VBox(tabPane);
        vBox.setPrefWidth(800);
        vBox.setPrefHeight(400);
        Scene scene = new Scene(vBox);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Smurf Village Market Auction");
        primaryStage.show();


    }

    private void setUpNetworking() throws Exception {
        @SuppressWarnings("resource")
        Socket socket = new Socket(host, 4242);
        System.out.println("Connecting to... " + socket);
        fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        toServer = new PrintWriter(socket.getOutputStream());

        // initialize Client GUI
        String item_list = fromServer.readLine();
        System.out.println("Item INIT: From Server:" + item_list);

        Type l = new TypeToken<ArrayList<Item_Info>>(){}.getType();
        Gson gson = new Gson();
        items_auction_arr = gson.fromJson(item_list, l);

        System.out.println("Item INIT: In Client:" + items_auction_arr);


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




