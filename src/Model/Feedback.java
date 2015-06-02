package Model;

public class Feedback {
   
    int eventID;
    String feedback;
    
    public Feedback(int ID, String fb){
    
        eventID = ID;
        feedback = fb;
    }
    
    public int getFBEventID(){
        return eventID;
    }
    
    public String getFeedback(){
        return feedback;
    }
    
    public String toString(){
    
        return "Event ID: " + eventID + " and Feedback: " + feedback;
    }
}
