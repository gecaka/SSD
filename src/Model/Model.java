/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

public class Model {
   
    public ArrayList<Customer> customers;
    public ArrayList<Comedian> comedians;
    
    public Model(){
        customers = new ArrayList<Customer>();
        comedians = new ArrayList<Comedian>();
    }
    
    public void addCustomer(Customer customer){
        customers.add(customer);
    }
    
    public void addComedian(Comedian comedian){
        comedians.add(comedian);
    }
    
    public boolean lookForCustomerBooking(String name, int ev_day,int ev_month,int ev_year){
            
        return true;
    }
    
}
