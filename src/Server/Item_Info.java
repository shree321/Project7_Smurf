package Server;

import java.util.ArrayList;

public class Item_Info {
    public String item_name; // read in from input file
    public double item_min_bid_price; // read in from input file and gets replaced by the client bids
    String client_winner;
    public double client_win_price;
    public boolean available_or_sold; // t if sold, false if available
    public double buy_now_price;
}

