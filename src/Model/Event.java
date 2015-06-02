package Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Event {
    
    public static int counter = 1;
    
    int ID;
    String name;
    Date date;
    float ticket_price;
    
    //ArrayList<Ticket> BookedTickets;
    ArrayList<Comedian> BookedComedianArrayList;
    ArrayList<Feedback> FeedbackArrayList;
    
    
    public Event(String eventName, Date eventDate, float ticketPrice){
       
       ID = counter;
       name = eventName;
       date = eventDate;
       ticket_price = ticketPrice;
       
       //BookedTickets = new ArrayList<Ticket>();
       BookedComedianArrayList = new ArrayList<Comedian>();
       FeedbackArrayList = new ArrayList<Feedback>();
       counter++;
    }
    
    public void BookComedian(Comedian comedian){
        
        BookedComedianArrayList.add(comedian);
    }
    
    public void AddFeedback(Feedback myFB){

        FeedbackArrayList.add(myFB);
    }
    
    public String getName(){
        return name;
    }
    
    public Date getDate(){
        return date;
    }
    
    public int getID(){
        return ID;
    }
    
    public String toString(){
        return "Event [ " + new SimpleDateFormat("dd/MM/yyyy").format(date) + " | Ticket price: " +ticket_price + " \u00a3 ]";  
    }
    
    public float getEventPrice(){
        
        float price = 0;
        
        for(Comedian com : BookedComedianArrayList){
            price = price + com.getPrice();
        }
        
        ticket_price = price;
        
        return ticket_price;
    }

}
