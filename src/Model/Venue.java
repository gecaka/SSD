package Model;

import java.util.ArrayList;
import java.util.Date;

public class Venue {
    
    String name;
    int capacity;
    
    public ArrayList<Event> EventArrayList;
    public ArrayList<Customer> CustomerArrayList;
    public ArrayList<Comedian> ComedianArrayList;
    
    
    // constructor 
    public Venue(String nameIn, int capacityIn){
        name = nameIn;
        capacity = capacityIn;
        
        EventArrayList = new ArrayList<Event>();
        CustomerArrayList = new ArrayList<Customer>();
        ComedianArrayList = new ArrayList<Comedian>();
    }
    
    // add to arraylist methods ----------------------------------------------------------------
    public void CreateEvent(Event myEvent){
        EventArrayList.add(myEvent);
        
    }
      
    public void addComedian(Comedian com){

        ComedianArrayList.add(com);
    }
    
    public void addCustomer(Customer cust){

        CustomerArrayList.add(cust);
    }
    
    //search methods ---------------------------------------------------------------------------
    public Comedian searchForComedian(String name){
    
            for(Comedian comedian:ComedianArrayList){
                
                if( comedian.getName().equals(name) ){
                    
                    return comedian;
                }
            
            }
            return null;
    }
    
    public Event searchForEvent(int id){
    
            for(Event myEvent:EventArrayList){
                
                if(myEvent.getID() == id){
                    
                    return myEvent;
                }
            
            }
           return null; 
    }
    
    
    //book comedian ------------------------------------------------------------------------------
    public void ComedianBookingForEvent(String eventName,String comedianName){
        
        Comedian newComedian;
        
        newComedian = this.searchForComedian(comedianName);
        //this.searchForEvent(eventName).BookComedian(newComedian);
        
    }
    
    public void addFeedback(){
        //Even
    }
    
    public String toString(){
    
        return "Name: " + name + " and Capacity: " + capacity;
    }
    
    public void separateComedians(){
       
           for(Event evt: EventArrayList){
            evt.BookedComedianArrayList.clear();
             for(Comedian com: ComedianArrayList){
                if(evt.getID() == com.getEventID()){
                    evt.BookComedian(com);
                }
            }
        }
        
    }
    
    public void updateEventPrices(){
        
        for(Event e:EventArrayList){
            e.getEventPrice();
        }
        
    }

}
