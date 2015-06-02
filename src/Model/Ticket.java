package Model;

public class Ticket {
    
    int eventID;
    String owner_name;
    int numOfTickets;
    
    public Ticket(int ID, int number){
        
        eventID = ID;
        numOfTickets = number;
    
    }
    
    public int getEventID(){
        return eventID;
    }
    
    public int getNumberOfTickets(){
        return numOfTickets;
    }
    
    public void setNumberOfTickets(int numberoftickets){
        numOfTickets = numberoftickets;
    }
    
    public String toString(){
    
        return owner_name + " [ EventID: " + eventID + " Num.Tickets: " + numOfTickets + " ]";
    }
    
    public void setOwner(String owner_name){
        this.owner_name = owner_name;
    }
    
    public String getOwenrName(){
        return owner_name;
    }
    
    public void setEventID(int eventID){
        this.eventID = eventID;
    }
    
}
