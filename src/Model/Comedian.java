package Model;

public class Comedian {
    
    String name;
    String pastExperience;
    int eventID;
    float price;
    
    public Comedian(int idIn, String nameIn, String pastExpIn, float Price){
    
        name = nameIn;
        pastExperience = pastExpIn;
        eventID = idIn;
        price = Price;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public int getEventID(){
        return eventID;
    }
    
    public void setEventID(int eventID){
        this.eventID = eventID;
    }
    
    public float getPrice(){
        return price;
    }
    
    public void setPrice(float price){
        this.price = price;
    }
    
    public String getPastExp(){
        return pastExperience;
    }
    
    public void setPastExp(String pastExp){
        pastExperience = pastExp;
    }
    
    public String toString(){
        return name + " Price: " + price;
    }
}
