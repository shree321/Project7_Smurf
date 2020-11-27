package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {

        TabPane tabPane = new TabPane();
        // Customer logs in with username& password or as a guest, then info gets added to Customer Info & Auction Drop-down menu
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

//      Second Stage creates a new window for the input text // TODO: ADD TO TAB1 INSTEAD NEXT TO USERNAME
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
        tab2.setContent((new Rectangle(800,400, Color.LIGHTCYAN)));
        // Have each new customer added to drop- down menu with a new tab created for each new customer
        // the customer tab isn't deleted but shows a Logged out option for their status so you can still see their history
        Tab tab3 = new Tab("Customer Info" , new Label("Displays all information and history for the selected customer"));
        //tab3.setContent((new Rectangle(800,400, Color.LIGHTSTEELBLUE)));
        // display all of the items in a drop-down menu that is read in from input file
        // item name, minimum bid price, and image selection based on the item name
        Tab tab4 = new Tab("Item Info" , new Label("Displays all information and history for the selected item"));
        //tab4.setContent((new Rectangle(800,400, Color.PALETURQUOISE)));
        // Allows each customer to select an item and enter a price
        // then it shows if it is valid or not
        Tab tab5 = new Tab("Auction Home Page" , new Label("Place your bid here"));
        // tab5.setContent((new Rectangle(800,400, Color.SKYBLUE)));


        tabPane.getTabs().add(tab1);
        tabPane.getTabs().add(tab2);
        tabPane.getTabs().add(tab3);
        tabPane.getTabs().add(tab4);
        tabPane.getTabs().add(tab5);


        VBox vBox = new VBox(tabPane);


        vBox.setPrefWidth(800);
        vBox.setPrefHeight(400);
        Scene scene = new Scene(vBox);

        primaryStage.setScene(scene);

        primaryStage.setTitle("Smurf Village Market Auction");
        primaryStage.show();


    }
}