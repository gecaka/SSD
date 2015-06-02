package Model;

import java.util.ArrayList;

public class Customer {
    
    String customerName;
    
    public ArrayList<Ticket> BookedTickets;
    
    public Customer(String name){

        customerName = name;
        BookedTickets = new ArrayList<Ticket>();
    }
    
    public void BookTicket(Ticket myticket){
        myticket.setOwner(customerName);
        BookedTickets.add(myticket);
    }
    
    public String getCustomerName(){
        return customerName;
    }
    
    public String toString(){
        return "Name: " + customerName;
    }
    
}
