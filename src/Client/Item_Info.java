package Client;

public class Item_Info {
    String item_name; // read in from input file
    int item_min_bid_price; // read in from input file and gets replaced by the client bids
    String client_winner;
    int client_win_price;
    boolean available_or_sold; // t if sold, false if available
    int buy_now_price;
}
