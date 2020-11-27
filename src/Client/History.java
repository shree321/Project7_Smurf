package Client;

import sun.net.www.http.HttpCaptureInputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
2 Maps
    Map1: contains the Item, the updated bid price, status for any item
    Map2: contains the Customer Name, Item Name, and new Bid price

    continuously update the Maps for any changes made
    and print out the last stored element anytime a VALID bid has been placed

    print out at the bottom of an automatic scrolling pane for the Bid History Tab

ArrayList of all of the customers and whether they are logged in in or out
*/
// TODO: OPTIONAL
// depending on the icon clicked, there can be a customer smurf description
// just have an identifier with 1-5 that says what icon they are to display correct description
public class History {
    String customer_name;
    boolean login_or_off;
    public History() {
    }
    Map<String, Boolean> user_map = new HashMap<String, Boolean>();
    Map<String, Integer, Boolean> item_history = new HashMap<String, Integer, Boolean>();
}