package Controller;

import Model.Comedian;
import Model.Customer;
import Model.Event;
import Model.Feedback;
import Model.Model;
import Model.Ticket;
import Model.Venue;
import View.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import ssd.SSD;


public class Controller {
    

    public Venue theModel;
    public Frame theView;
    
    public Controller(Frame View,Venue model){
        
        
        theModel = model;
        theView = View;
        
        populateSystem();
        
        //role selection
        theView.addSelectManagerListener(new selectManagerListener());
        theView.addBookingOfficeListener(new selectBookingOfficeListener());
        
        //Booking office
        theView.addCustomerListener(new addCustomerListener());
        theView.updateCustomerListener(new updateCustomerListener());
        theView.bookTicketsListener(new bookTicketsListener());
        theView.selectCustomerListener(new selectCustomerListener());
        theView.selectComedianListener(new selectComedianListener());
        theView.addComedianListener(new addComedianListener());
        theView.updateComedianListener(new updateComedianListener());
        theView.selectFeedbackListener(new selectFeedbackListener());
        theView.selectStatisticListener(new selectStatisticListener());
    }
    
    public void populateSystem(){
        
        String date1 = "24/04/2015";
        String date2 = "21/03/2015";
        String date3 = "18/02/2015";
        
        Date dateobj1 = new Date();
        Date dateobj2 = new Date();
        Date dateobj3 = new Date();
        
        try {
            dateobj1 = new SimpleDateFormat("dd/MM/yyyy").parse(date1);
            dateobj2 = new SimpleDateFormat("dd/MM/yyyy").parse(date2);
            dateobj3 = new SimpleDateFormat("dd/MM/yyyy").parse(date3);
        } catch (ParseException ex) {
            Logger.getLogger(SSD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Event event1 = new Event("Latino comedy",dateobj1,2.82f);
        
        Event event2 = new Event("Cool night",dateobj2,3.21f);
                
        Event event3 = new Event("Oriental comedy",dateobj3,4.31f);
        
        Comedian com1 = new Comedian(1,"KevinHart", "SNL, Soul Plane", 2.32f);
        
        Comedian com2 = new Comedian(1,"Chris Rock", "Cool runnings, Backlash", 3.23f);
        
        Comedian com3 = new Comedian(2,"Jared Kent", "20 year old comedian from Los Angeles", 3.22f);
        
        Comedian com4 = new Comedian(3,"Calum Rivers", "Slapstick comedy", 2.21f);
        
        Customer cust1 = new Customer("Preslav");
        
        Customer cust2 = new Customer("Georgi");
        
        Customer cust3 = new Customer("Brian");
        
        Customer cust4 = new Customer("Alex");
        
        Customer cust5 = new Customer("Jack");
        
        Feedback fb1 = new Feedback(1,"Very funny show");
        
        Feedback fb2 = new Feedback(1,"Great show");
        
        Feedback fb3 = new Feedback(2,"Could be better");
        
        Feedback fb4 = new Feedback(2,"Weak show");
        
        Feedback fb5 = new Feedback(3,"Mode comedy needed");
        
        Feedback fb6 = new Feedback(3,"Less comedy needed");
        
        Feedback fb7 = new Feedback(3,"Keep it up!");
        
        Ticket cust1_tickets = new Ticket(1,4);
        
        Ticket cust1_tickets2 = new Ticket(2,3);
        
        Ticket cust2_tickets = new Ticket(1,2);
        
        Ticket cust3_tickets = new Ticket(2,3);
        
        Ticket cust3_tickets2 = new Ticket(3,1);
        
        Ticket cust4_tickets = new Ticket(2,4);
        
        Ticket cust5_tickets = new Ticket(3,4);
        
        //Tests---------------------------------------------------------------------------
        
        theModel.CreateEvent(event1);
        theModel.CreateEvent(event2);
        theModel.CreateEvent(event3);
        
        theModel.addComedian(com1);
        theModel.addComedian(com2);
        theModel.addComedian(com3);
        theModel.addComedian(com4);
        
        theModel.addCustomer(cust1);
        theModel.addCustomer(cust2);
        theModel.addCustomer(cust3);
        theModel.addCustomer(cust4);
        theModel.addCustomer(cust5);
        
        event1.AddFeedback(fb1);
        event1.AddFeedback(fb2);
        event2.AddFeedback(fb3);
        event2.AddFeedback(fb4);
        event3.AddFeedback(fb5);
        event3.AddFeedback(fb6);
        event3.AddFeedback(fb7);
        
        cust1.BookTicket(cust1_tickets);
        cust1.BookTicket(cust1_tickets2);
        cust2.BookTicket(cust2_tickets);
        cust3.BookTicket(cust3_tickets);
        cust3.BookTicket(cust3_tickets2);
        cust4.BookTicket(cust4_tickets);
        cust5.BookTicket(cust5_tickets);
        
        Customer cust = new Customer("Ivan"); 
        theModel.addCustomer(cust);
        
        cust = new Customer("Alex"); 
        theModel.addCustomer(cust);
        
        cust = new Customer("Fernando"); 
        theModel.addCustomer(cust);
        
        cust = new Customer("Francisco"); 
        theModel.addCustomer(cust);
        
        cust = new Customer("Christoval"); 
        theModel.addCustomer(cust);
        
        theModel.separateComedians();
        updateGUInoclear();
    }
    
    public void updateGUI(){
        theView.clearAllComboBoxes();
        theView.addEventList(theModel.EventArrayList);
        theView.addCustomerList(theModel.CustomerArrayList);
        theView.addComedianList(theModel.ComedianArrayList);
        theModel.updateEventPrices();
    }
    
    public void updateGUInoclear(){
        theView.addEventList(theModel.EventArrayList);
        theView.addCustomerList(theModel.CustomerArrayList);
        theView.addComedianList(theModel.ComedianArrayList);
        theModel.updateEventPrices();
    }
    
    //Role Select Listeners !!!!!!!
    
    class selectManagerListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            theView.swtichToManager();
        }
        
    }
    
    class selectBookingOfficeListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            theView.switchToBookingOffice();
        }
        
    }
    
    /*Booking Office Listeners ====================================================================================================*/
    
    class addCustomerListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            boolean exists = false;
            
            
            String customer_name = theView.getCustomerName();
            int number_of_tickets = theView.getNumberOfTickets();
            Event evnt = theView.getSelectedEventBO();
            
            Ticket reform_ticket = new Ticket(evnt.getID(),number_of_tickets);
            
            for(Customer cust:theModel.CustomerArrayList){
                for(Ticket ticket:cust.BookedTickets){
                    if(cust.getCustomerName().equals(customer_name) && evnt.getID() == ticket.getEventID()){
                        exists = true;
                        reform_ticket = ticket;
                    }
                }
            }
            
            if(!exists){
                Customer customer = new Customer(customer_name);
                customer.BookTicket(reform_ticket);
                theModel.addCustomer(customer);
                
            }else{
                reform_ticket.setNumberOfTickets((reform_ticket.getNumberOfTickets()+number_of_tickets));
            }
            
            updateGUI();
        }
        
    }
    
    class updateCustomerListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            String customer_name = theView.getCustomerName();
            int number_of_tickets = theView.getNumberOfTickets();
            Event evnt = theView.getSelectedEventBO();
        
            theView.getSelectedTicketBO().setEventID(evnt.getID());
            theView.getSelectedTicketBO().setNumberOfTickets(number_of_tickets);
            theView.getSelectedTicketBO().setOwner(customer_name);
            
            updateGUI();
        }
        
    }
    
    class bookTicketsListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            String customer_name = theView.getCustomerName();
            int number_of_tickets = theView.getNumberOfTickets();
            Event evnt = theView.getSelectedEventBO();
            
            JOptionPane.showMessageDialog(theView , customer_name+" booked " + number_of_tickets + " tickets for the event on "+ new SimpleDateFormat("dd/MM/yyyy").format(evnt.getDate()));
        }
        
    }
    
    class selectCustomerListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            Ticket ticket = theView.getSelectedTicketBO();
            if(ticket != null){
            theView.setNumberOfTickets(ticket.getNumberOfTickets());
            theView.setCustomerName(ticket.getOwenrName());
            theView.setEventIndex(ticket.getEventID());
            }
        }
        
    }
    
     /*Manager panel Listeners ======================================================================================== */
    
        class selectComedianListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Comedian com = theView.getSelectedComedian();
            
            if(com != null){
            theView.setNameMNCOM(com.getName());
            theView.setExperienceMNCOM(com.getPastExp());
            theView.setPriceMNCOM(com.getPrice());
            
            
            Event ev = theModel.searchForEvent(com.getEventID());
            
            System.out.println(ev.getDate().getDate());
            
            theView.setDateMNCOM(ev.getDate().getDate(), ev.getDate().getMonth(), ev.getDate().getYear()+1900);
            }
        }
        
    }
        
        class addComedianListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean exists = false;
            
            String comedian_name = theView.getNameMNCOM();
            String experience = theView.getExperienceMNCOM();
            float price = theView.getPriceMNCOM();
            String date = theView.getDateMNCOM();
            String[] data = date.split("/");
            
            Comedian comedian = new Comedian(0,comedian_name,experience,price);
            
            for(Comedian com:theModel.ComedianArrayList){
                if(com.getName().equals(comedian_name)){
                    
                }else{        
                    for(Event events:theModel.EventArrayList){
                        if(data[0].equals(String.valueOf(events.getDate().getDate()))){
                            if(data[1].equals(String.valueOf(events.getDate().getMonth()))){
                                if(data[2].equals(String.valueOf(events.getDate().getYear()))){
                                    exists = true;
                                    
                                }
                            }
                        }
                    }
                   
                    
                }
            }
            
            if(!exists){
                
                Date datee = new Date();
                try {
                    datee = new SimpleDateFormat("dd/MM/yyyy").parse(date);
                } catch (ParseException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                Event evnt = new Event("Event",datee,2.3f);
                comedian.setEventID(evnt.getID());
                theModel.addComedian(comedian);
                updateGUI();
                
            }else{
                //reform_ticket.setNumberOfTickets((reform_ticket.getNumberOfTickets()+number_of_tickets));
            }
            
            
        }
        
    }
            
        class updateComedianListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            String comedian_name = theView.getNameMNCOM();
            String experience = theView.getExperienceMNCOM();
            float price = theView.getPriceMNCOM();
            String date = theView.getDateMNCOM();
            String[] data = date.split("/");
        
            theView.getSelectedComedian().setName(comedian_name);
            theView.getSelectedComedian().setPastExp(experience);
            theView.getSelectedComedian().setPrice(price);
            
            updateGUI();
            
        }
        
    }
        
        class selectFeedbackListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            Event evt = theView.getSTSelectedEvent();
            
            String first_percentage = "70%";
            String second_percentage = "80%";
            
            if(evt != null){
            theView.setFBawnser1(first_percentage +" of people enjoyed the event.");
            theView.setFBawnser2(second_percentage+" of people would come back.");
            }
        }
        
    }
                
        class selectStatisticListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Event evt = theView.getSTSelectedEvent();
            
            if(evt != null){
            theView.setSTname(evt.getName());
            theView.setSTsoldtickets(32); // What
            theView.setSTActualAtendence(60);
            theView.setSTSalesRevenue(700);
            theView.setSTAverageRating(9.2f);
            }
        }
        
    }
    
}
