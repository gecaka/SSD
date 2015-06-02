package ssd;

import Controller.Controller;
import Model.Comedian;
import Model.Customer;
import Model.Event;
import Model.Feedback;
import Model.Model;
import Model.Ticket;
import Model.Venue;
import View.Frame;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SSD {

    public static void main(String[] args) {
        
        Venue myVenue = new Venue("Soton",300);
    
        //myVenue.ComedianBookingForEvent("BGParty", "KevinHart");
        
        Frame frame = new Frame();
        Venue model = new Venue("Comedy Venue", 100);
        
        Controller control = new Controller(frame, model);
        
    }
    
}
