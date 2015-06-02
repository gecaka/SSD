package View;

import Model.Comedian;
import Model.Customer;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.Date;
import java.util.Properties;
import org.jdatepicker.*;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import Utils.DateLabelFormatter;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import Model.Event;
import Model.Ticket;

public class Frame extends JFrame {
    
    public static final int HEIGHT = 800;
    public static final int WIDTH = 600;
    
    //JFrame Elements***************************************************************************************************
    
    JPanel fr_base_panel = new JPanel();
    CardLayout frame_manager = new CardLayout();
    
    JPanel fr_role_selection_screen = new JPanel();
    JPanel fr_booking_office_screen = new JPanel();
    JPanel fr_manager_screen = new JPanel();
    
    //ROLE SELECTION Elements*******************************************************************************************
    
    JPanel rs_top_panel = new JPanel();
    JPanel rs_bottom_panel = new JPanel();
    
    JLabel rs_lb_logo;
    JLabel rs_lb_welcome = new JLabel("Enter as:");
    JButton rs_btn_manager_select = new JButton("Manager");
    JButton rs_btn_booking_office_select = new JButton("Booking Office");
    
    //BOOKING OFFICE Elements*******************************************************************************************
    
    JPanel bo_left_panel = new JPanel();
    JPanel bo_right_panel = new JPanel();
    
    JLabel bo_lb_name = new JLabel("Name:");
    JLabel bo_lb_number_tickets = new JLabel("Number of tickets:");
    JLabel bo_lb_event_date = new JLabel("Event date:");
    
    JTextField bo_tf_name = new JTextField();
    JTextField bo_tf_number_tickets = new JTextField();
    JComboBox bo_cb_event_date = new JComboBox();
    
    JButton bo_bt_book_tickets = new JButton("Book Tickets");
    
    JComboBox bo_cb_select_customer = new JComboBox();
    
    JButton bo_bt_add_customer_booking = new JButton("Add customer booking");
    JButton bo_bt_update_customer_booking = new JButton("Update customer booking");
    
    //MANAGER Elements**************************************************************************************************
    
    JTabbedPane mn_tabbed_pane = new JTabbedPane();
    
                    //Comedian bookings tab
    JPanel mn_comedian_booking_panel = new JPanel();
    JPanel mn_comedian_booking_left = new JPanel();
    JPanel mn_comedian_booking_right = new JPanel();
    
    JComboBox mn_cb_cb_comedian_select = new JComboBox();
    
    JButton mn_cb_bt_add_comedian = new JButton("Add Comedian");
    JButton mn_cb_bt_update_comedian = new JButton("Update comedian info");
    
    JLabel mn_cb_lb_name = new JLabel("Name:");
    JLabel mn_cb_lb_price = new JLabel("Price:");
    JLabel mn_cb_lb_experience = new JLabel("Experience:");
    JLabel mn_cb_lb_event_date = new JLabel("Event date:");
    
    JTextField mn_cb_tf_name = new JTextField();
    JTextField mn_cb_tf_experience = new JTextField();
    JTextField mn_cb_tf_price = new JTextField();
    JDatePanelImpl mn_cb_tf_event_date_panel;
    JDatePickerImpl mn_cb_tf_event_date_picker;
    
                    //Statistics tab
    JPanel mn_statistics_panel = new JPanel();
    JPanel mn_statistics_left = new JPanel();
    JPanel mn_statistics_right = new JPanel();
    
    JComboBox mn_st_cb_event_select = new JComboBox();
    
    JLabel mn_st_lb_event_name = new JLabel("Event name:");
    JLabel mn_st_lb_sold_tickets = new JLabel("Sold tickets:");
    JLabel mn_st_lb_actual_attendance = new JLabel("Actual attendance:");
    JLabel mn_st_lb_sales_revenue = new JLabel("Sales revenue:");
    JLabel mn_st_lb_average_rating = new JLabel("Average rating:");
            
    JTextField mn_st_tf_event_name = new JTextField();
    JTextField mn_st_tf_sold_tickets = new JTextField();
    JTextField mn_st_tf_actual_attendance = new JTextField();
    JTextField mn_st_tf_sales_revenue = new JTextField();
    JTextField mn_st_tf_average_rating = new JTextField();
    
                    //Feedback tab
    JPanel mn_feedback_panel = new JPanel();
    JPanel mn_feedback_left = new JPanel();
    JPanel mn_feedback_right = new JPanel();
    
    JComboBox mn_fb_cb_event_select = new JComboBox();
    
    JButton mn_fb_bt_add_feedback = new JButton("Add feedback");
    
    JLabel mn_fb_lb_question_one = new JLabel("How enjoyable was the event?");
    JLabel mn_fb_lb_question_two = new JLabel("How likely are you to return to the venue?");
    JLabel mn_fb_lb_question_one_response = new JLabel("otgovor");
    JLabel mn_fb_lb_question_two_response = new JLabel("otgovor 2");
    
    ButtonGroup mn_fb_question_one_buttons = new ButtonGroup();
    ButtonGroup mn_fb_question_two_buttons = new ButtonGroup();
    
    JRadioButton q1_b1 = new JRadioButton("Very Enjoyable");
    JRadioButton q1_b2 = new JRadioButton("Quite Enjoyable");
    JRadioButton q1_b3 = new JRadioButton("Enjoyable");
    JRadioButton q1_b4 = new JRadioButton("Ok");
    JRadioButton q1_b5 = new JRadioButton("Not Enjoyable");
    
    JRadioButton q2_b1 = new JRadioButton("Very Likely");
    JRadioButton q2_b2 = new JRadioButton("Likely");
    JRadioButton q2_b3 = new JRadioButton("Maybe");
    JRadioButton q2_b4 = new JRadioButton("Unlikely");
    JRadioButton q2_b5 = new JRadioButton("Very Unlikely");
    

    
    public Frame(){
        
        this.setTitle("SSU Comedy Venue");
        this.setResizable(false);
        this.setSize(HEIGHT, WIDTH);
        this.setLayout(new BorderLayout());
        this.addWindowListener(new WindowAdapter() 
        {
        public void windowClosing(WindowEvent windowEvent)
            {
                System.exit(0);
            }        
        }); 
        
        addElementsToFrame();
        this.setVisible(true);
        frame_manager.show(fr_base_panel, "role selection"); //role_selection, manager, booking_office

    }
    
    //MANAGER Elements***************************************************************************************************
    
    private void addElementsToManager(){
        initManagerElements();
        
        fr_manager_screen.add(mn_tabbed_pane, BorderLayout.CENTER);
        mn_tabbed_pane.addTab("Comedian Bookings", mn_comedian_booking_panel);
        mn_tabbed_pane.addTab("Statistics", mn_statistics_panel);
        mn_tabbed_pane.addTab("Feedback", mn_feedback_panel);
        
        mn_comedian_booking_panel.add(mn_comedian_booking_left);
        mn_comedian_booking_panel.add(mn_comedian_booking_right);
        
        mn_comedian_booking_left.add(mn_cb_cb_comedian_select);
        mn_comedian_booking_left.add(new JLabel("")); //filler
        mn_comedian_booking_left.add(new JLabel("")); //filler
        mn_comedian_booking_left.add(new JLabel("")); //filler
        mn_comedian_booking_left.add(new JLabel("")); //filler
        mn_comedian_booking_left.add(mn_cb_bt_add_comedian);
        mn_comedian_booking_left.add(mn_cb_bt_update_comedian);
        
        mn_comedian_booking_right.add(mn_cb_lb_name);
        mn_comedian_booking_right.add(mn_cb_tf_name);
        mn_comedian_booking_right.add(mn_cb_lb_experience);
        mn_comedian_booking_right.add(mn_cb_tf_experience);
        mn_comedian_booking_right.add(mn_cb_lb_price);
        mn_comedian_booking_right.add(mn_cb_tf_price);
        mn_comedian_booking_right.add(mn_cb_lb_event_date);
        mn_comedian_booking_right.add(mn_cb_tf_event_date_picker);
        
        mn_statistics_panel.add(mn_statistics_left);
        mn_statistics_panel.add(mn_statistics_right);
        
        mn_statistics_left.add(mn_st_cb_event_select);
        
        mn_statistics_right.add(mn_st_lb_event_name);
        mn_statistics_right.add(mn_st_tf_event_name);
        mn_statistics_right.add(mn_st_lb_sold_tickets);
        mn_statistics_right.add(mn_st_tf_sold_tickets);
        mn_statistics_right.add(mn_st_lb_actual_attendance);
        mn_statistics_right.add(mn_st_tf_actual_attendance);
        mn_statistics_right.add(mn_st_lb_sales_revenue);
        mn_statistics_right.add(mn_st_tf_sales_revenue);
        mn_statistics_right.add(mn_st_lb_average_rating);
        mn_statistics_right.add(mn_st_tf_average_rating);
        
        mn_feedback_panel.add(mn_feedback_left);
        mn_feedback_panel.add(mn_feedback_right);
        
        mn_feedback_left.add(mn_fb_cb_event_select);
        
        mn_feedback_right.add(mn_fb_lb_question_one);
        
        mn_feedback_right.add(new JLabel("")); //filler
        
        mn_feedback_right.add(q1_b1);
        mn_feedback_right.add(q1_b2);
        mn_feedback_right.add(q1_b3);
        mn_feedback_right.add(q1_b4);
        mn_feedback_right.add(q1_b5);
        
        mn_feedback_right.add(new JLabel("")); //filler
        
        mn_feedback_right.add(mn_fb_lb_question_two);
        
        mn_feedback_right.add(new JLabel("")); //filler
        
        mn_feedback_right.add(q2_b1);
        mn_feedback_right.add(q2_b2);
        mn_feedback_right.add(q2_b3);
        mn_feedback_right.add(q2_b4);
        mn_feedback_right.add(q2_b5);
        
        mn_feedback_right.add(new JLabel("")); //filler
        mn_feedback_right.add(new JLabel("")); //filler
        
        mn_feedback_right.add(mn_fb_lb_question_one_response);
        
        mn_feedback_right.add(new JLabel("")); //filler
        
        mn_feedback_right.add(mn_fb_lb_question_two_response);
        
        
    }
    
    private void initManagerElements(){
        
        mn_comedian_booking_panel.setLayout(new BoxLayout(mn_comedian_booking_panel,BoxLayout.X_AXIS));
        mn_statistics_panel.setLayout(new BoxLayout(mn_statistics_panel,BoxLayout.X_AXIS));
        mn_feedback_panel.setLayout(new BoxLayout(mn_feedback_panel,BoxLayout.X_AXIS));
        
        mn_comedian_booking_left.setLayout(new GridLayout(7,1));
        mn_comedian_booking_right.setLayout(new GridLayout(9,1));
        mn_comedian_booking_left.setBorder(new EmptyBorder(10,10,10,10));
        mn_comedian_booking_right.setBorder(new EmptyBorder(10,10,10,10));
        mn_comedian_booking_left.setBackground(Color.BLUE);
        
        mn_cb_lb_name.setHorizontalAlignment(SwingConstants.CENTER);
        mn_cb_lb_price.setHorizontalAlignment(SwingConstants.CENTER);
        mn_cb_lb_experience.setHorizontalAlignment(SwingConstants.CENTER);
        mn_cb_lb_event_date.setHorizontalAlignment(SwingConstants.CENTER);
        
        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");
        
        UtilDateModel mn_cb_event_date_model = new UtilDateModel();
        
        mn_cb_tf_event_date_panel = new JDatePanelImpl(mn_cb_event_date_model,properties);
        mn_cb_tf_event_date_picker = new JDatePickerImpl(mn_cb_tf_event_date_panel,new DateLabelFormatter());
        //---end comedianbooking panel
        mn_statistics_left.setLayout(new GridLayout(7,1));
        mn_statistics_right.setLayout(new GridLayout(5,2));
        mn_statistics_left.setBorder(new EmptyBorder(10,10,10,10));
        mn_statistics_right.setBorder(new EmptyBorder(10,10,10,10));
        mn_statistics_left.setBackground(Color.BLUE);
        //--end statistics panel
        mn_feedback_left.setLayout(new GridLayout(7,1));
        mn_feedback_right.setLayout(new GridLayout(20,1));
        mn_feedback_left.setBorder(new EmptyBorder(10,10,10,10));
        mn_feedback_right.setBorder(new EmptyBorder(10,10,10,10));
        mn_feedback_left.setBackground(Color.BLUE);
        
        mn_fb_question_one_buttons.add(q1_b1);
        mn_fb_question_one_buttons.add(q1_b2);
        mn_fb_question_one_buttons.add(q1_b3);
        mn_fb_question_one_buttons.add(q1_b4);
        mn_fb_question_one_buttons.add(q1_b5);
        
        mn_fb_question_two_buttons.add(q2_b1);
        mn_fb_question_two_buttons.add(q2_b2);
        mn_fb_question_two_buttons.add(q2_b3);
        mn_fb_question_two_buttons.add(q2_b4);
        mn_fb_question_two_buttons.add(q2_b5);
        
        //--end feedback panel
    }
    
    //FRAME Methods****************************************************************************************************
    private void addElementsToFrame(){
        initFrameElements();
        
        this.add(fr_base_panel, BorderLayout.CENTER);
   
        fr_base_panel.add(fr_role_selection_screen, "role_selection");
        fr_base_panel.add(fr_booking_office_screen, "booking_office");
        fr_base_panel.add(fr_manager_screen, "manager");
        
        addElementsToRole();
        addElementsToManager();
        addElementsToBooking();
        
    }
    
    private void initFrameElements(){
        fr_base_panel.setLayout(frame_manager);
        
        
        fr_role_selection_screen.setLayout(new GridLayout(2,1));
        fr_role_selection_screen.setBorder(new EmptyBorder(20,20,20,20));
        
        fr_booking_office_screen.setLayout(new BoxLayout(fr_booking_office_screen,BoxLayout.X_AXIS));
        
        fr_manager_screen.setLayout(new BorderLayout());
       
    }
    
    //ROLE SELECTION Methods********************************************************************************************
    
    private void addElementsToRole(){
        initRoleElements();
        fr_role_selection_screen.add(rs_top_panel);
        fr_role_selection_screen.add(rs_bottom_panel);
        
        rs_top_panel.add(rs_lb_logo);
        rs_bottom_panel.add(rs_lb_welcome, BorderLayout.PAGE_START);
        rs_bottom_panel.add(rs_btn_manager_select, BorderLayout.LINE_START);
        rs_bottom_panel.add(rs_btn_booking_office_select, BorderLayout.LINE_END);
    }
    
    private void initRoleElements(){
        rs_top_panel.setLayout(new BoxLayout(rs_top_panel,BoxLayout.Y_AXIS));
        rs_bottom_panel.setLayout(new BorderLayout());
        rs_top_panel.setBorder(new EmptyBorder(20,20,20,20));
        
        String path = "res/logo.png";
        File file = new File(path);
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        rs_lb_logo = new JLabel(new ImageIcon(image.getScaledInstance(600, 200,Image.SCALE_SMOOTH)));
        rs_lb_logo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        rs_btn_manager_select.setPreferredSize(new Dimension(300, 100));
        rs_btn_booking_office_select.setPreferredSize(new Dimension(300, 100));
        rs_lb_welcome.setHorizontalAlignment(JLabel.CENTER);
        
    }
    
    //BOOKING OFFICE Elements********************************************************************************************
    
    private void addElementsToBooking(){
        initBookingElements();
        
        fr_booking_office_screen.add(bo_left_panel);
        fr_booking_office_screen.add(bo_right_panel);
        
        bo_right_panel.add(bo_lb_name);
        bo_right_panel.add(bo_tf_name);
        bo_right_panel.add(bo_lb_number_tickets);
        bo_right_panel.add(bo_tf_number_tickets);
        bo_right_panel.add(bo_lb_event_date);
        bo_right_panel.add(bo_cb_event_date);
        bo_right_panel.add(new JLabel("")); // Filler
        bo_right_panel.add(bo_bt_book_tickets);
        
        bo_left_panel.add(bo_cb_select_customer);
        bo_left_panel.add(new JLabel("")); // Filler
        bo_left_panel.add(new JLabel("")); // Filler
        bo_left_panel.add(new JLabel("")); // Filler
        bo_left_panel.add(new JLabel("")); // Filler
        bo_left_panel.add(bo_bt_add_customer_booking);
        bo_left_panel.add(bo_bt_update_customer_booking);
        
    }
    
    public void initBookingElements(){
        bo_left_panel.setLayout(new GridLayout(7,1));
        bo_left_panel.setBackground(Color.BLUE);
        bo_left_panel.setBorder(new EmptyBorder(10,10,10,5));
        
        bo_right_panel.setLayout(new GridLayout(8,1));
        bo_right_panel.setBorder(new EmptyBorder(10,5,10,10));
        
        bo_lb_name.setHorizontalAlignment(SwingConstants.CENTER);
        bo_lb_number_tickets.setHorizontalAlignment(SwingConstants.CENTER);
        bo_lb_event_date.setHorizontalAlignment(SwingConstants.CENTER);
        
        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");
        
        UtilDateModel bo_event_date_model = new UtilDateModel();
        
        bo_cb_event_date = new JComboBox();
        
    }
    
    //Role Selection listeners
    
    public void addSelectManagerListener(ActionListener listenForManagerButton){
        rs_btn_manager_select.addActionListener(listenForManagerButton);
    }
    
    public void addBookingOfficeListener(ActionListener listenForBookingOffice){
        rs_btn_booking_office_select.addActionListener(listenForBookingOffice);
    }
    
    public void switchToRoleSelect(){
        frame_manager.show(fr_base_panel, "role_selection");
    }
    
    public void switchToBookingOffice(){
        frame_manager.show(fr_base_panel, "booking_office");
    }
    
    public void swtichToManager(){
        frame_manager.show(fr_base_panel, "manager");
    }
    
    //Booking Office Listeners
    
    public void addCustomerListener(ActionListener ListenForCustomerAdd){
        bo_bt_add_customer_booking.addActionListener(ListenForCustomerAdd);
    }
    
    public void updateCustomerListener(ActionListener ListenForCustomerUpdate){
       bo_bt_update_customer_booking.addActionListener(ListenForCustomerUpdate);
    }
    
    public void bookTicketsListener(ActionListener listenforCustomerBooking){
        bo_bt_book_tickets.addActionListener(listenforCustomerBooking);
    }
    
    public void selectCustomerListener(ActionListener listenForCustomerSelect){
        bo_cb_select_customer.addActionListener(listenForCustomerSelect);
    }
    
    public String getCustomerName(){
        return bo_tf_name.getText();
    }
    
    public int getNumberOfTickets(){
        return Integer.parseInt(bo_tf_number_tickets.getText());
    }
    
    public void setCustomerName(String name){
        bo_tf_name.setText(name);
    }
    
    public void setNumberOfTickets(int num_tickets){
        bo_tf_number_tickets.setText(String.valueOf(num_tickets));
    }
    
    public void addEvent(Event evnt){
        bo_cb_event_date.addItem(evnt);
        mn_st_cb_event_select.addItem(evnt);
        mn_fb_cb_event_select.addItem(evnt);
    }
    
    public void addEventList(ArrayList<Event> evnt){
        for(Event e:evnt){
            bo_cb_event_date.addItem(e);
            mn_st_cb_event_select.addItem(e);
            mn_fb_cb_event_select.addItem(e);
        }
    }
    
    public void setEventIndex(int id){
        
        for(int i = 0; i < bo_cb_event_date.getItemCount();i++){
            
            Event evnt = (Event)bo_cb_event_date.getItemAt(i);
            
            if(id == evnt.getID()){
                bo_cb_event_date.setSelectedIndex(i);
            }
        }
    }
    
    public Event getSelectedEventBO(){
        return (Event)bo_cb_event_date.getSelectedItem();
    }
    
    public Ticket getSelectedTicketBO(){
        return (Ticket)bo_cb_select_customer.getSelectedItem();
    }
    
    /*public String getEventDate(){
        return bo_tf_event_date_picker.getModel().getDay() + "/" + bo_tf_event_date_picker.getModel().getMonth() + "/" + bo_tf_event_date_picker.getModel().getYear();
    }
    
    public int getEventDay(){
        return bo_tf_event_date_picker.getModel().getDay();
    }
    
    public int getEventMonth(){
        return bo_tf_event_date_picker.getModel().getMonth();
    }
    
    public int getEventYear(){
        return bo_tf_event_date_picker.getModel().getYear();
    }
    
    public void setEventDate(int day,int month,int year){
        bo_tf_event_date_picker.getModel().setDate(day, month, year); // DAY MONTH YEAR !!!!
    }
    
    public void setEventDate(String date){
        
        String[] dates = date.split("/");
        int day = Integer.parseInt(dates[0]);
        int month = Integer.parseInt(dates[1]);
        int year = Integer.parseInt(dates[2]);
        bo_tf_event_date_picker.getModel().setDate(day, month, year); // DAY MONTH YEAR !!!!
    }
   
    public void setEventDay(int day){
        bo_tf_event_date_picker.getModel().setDay(day);
    }
    
    public void setEventMonth(int month){
        bo_tf_event_date_picker.getModel().setMonth(month);
    }
        
    public void setEventYear(int year){
        bo_tf_event_date_picker.getModel().setYear(year);
    }
    */
    public void addCustomerList(ArrayList<Customer> customers){
        for(Customer cust:customers){
          
          for(Ticket ticket : cust.BookedTickets)
             bo_cb_select_customer.addItem(ticket);
          }
        
    }
    
    public void addCustomer(Customer customer){
        
        for(Ticket ticket : customer.BookedTickets){
             bo_cb_select_customer.addItem(ticket);
        }
    
    }
    
    public void addComedian(Comedian comedian){
        mn_cb_cb_comedian_select.addItem(comedian);
    }
    
    public void addComedianList(ArrayList<Comedian> comedians){
        for(Comedian com:comedians){
            mn_cb_cb_comedian_select.addItem(com);
        }
    }
    
    public void clearAllComboBoxes(){
            bo_cb_event_date.removeAllItems();
            mn_st_cb_event_select.removeAllItems();
            mn_fb_cb_event_select.removeAllItems();
            bo_cb_select_customer.removeAllItems(); 
            mn_cb_cb_comedian_select.removeAllItems();
    }
    
    private void clearEventCombos(){
            bo_cb_event_date.removeAllItems();
            mn_st_cb_event_select.removeAllItems();
            mn_fb_cb_event_select.removeAllItems();
    }
    
    private void clearComedianCombos(){
        mn_cb_cb_comedian_select.removeAllItems();
    }
    
    private void clearCustomerCombos(){
        bo_cb_select_customer.removeAllItems(); 
    }
    
    //Manager Screen
    
    public void selectComedianListener(ActionListener listenForCustomerSelect){
        mn_cb_cb_comedian_select.addActionListener(listenForCustomerSelect);
    }
    
    public void addComedianListener(ActionListener listenForCustomerSelect){
        mn_cb_bt_add_comedian.addActionListener(listenForCustomerSelect);
    }
    
    public void updateComedianListener(ActionListener listenForCustomerSelect){
        mn_cb_bt_update_comedian.addActionListener(listenForCustomerSelect);
    }
    
    public Comedian getSelectedComedian(){
        return (Comedian)mn_cb_cb_comedian_select.getSelectedItem();
    }
    
    public void setNameMNCOM(String name){
        mn_cb_tf_name.setText(name);
    }
    
    public String getNameMNCOM(){
        return mn_cb_tf_name.getText();
    }
    
    public void setExperienceMNCOM(String exp){
        mn_cb_tf_experience.setText(exp);
    }
    
    public String getExperienceMNCOM(){
        return mn_cb_tf_experience.getText();
    }
    
    public void setPriceMNCOM(float price){
        mn_cb_tf_price.setText(String.valueOf(price));
    }
    
    public float getPriceMNCOM(){
        return Float.parseFloat(mn_cb_tf_price.getText());
    }
    
    public void setDateMNCOM(int day,int month,int year){
        
       mn_cb_tf_event_date_picker.getModel().setDay(day);
       mn_cb_tf_event_date_picker.getModel().setMonth(month);
       mn_cb_tf_event_date_picker.getModel().setYear(year);
       mn_cb_tf_event_date_picker.getModel().setSelected(true);
       
    }
    
    public String getDateMNCOM(){
      
      String date = "";
      date = date + mn_cb_tf_event_date_picker.getModel().getDay() + "/";
      date = date + mn_cb_tf_event_date_picker.getModel().getMonth() + "/";
      date = date + mn_cb_tf_event_date_picker.getModel().getYear();
      
      return date;
    }
        
    public void selectStatisticListener(ActionListener listenForCustomerSelect){
        mn_st_cb_event_select.addActionListener(listenForCustomerSelect);
    }
    
    public void setSTname(String name){
        mn_st_tf_event_name.setText(name);
    }
    
    public void setSTsoldtickets(int sold){
        mn_st_tf_sold_tickets.setText(String.valueOf(sold));
    }
    
    public void setSTActualAtendence(int actual){
        mn_st_tf_actual_attendance.setText(String.valueOf(actual));
    }
    
    public void setSTSalesRevenue(float revenue){
        mn_st_tf_sales_revenue.setText(String.valueOf(revenue));
    }
    
    public void setSTAverageRating(float rating){
        mn_st_tf_average_rating.setText(String.valueOf(rating));
    }
    
    public Event getSTSelectedEvent(){
        return (Event)mn_st_cb_event_select.getSelectedItem();
    }
    
    public void selectFeedbackListener(ActionListener listenForCustomerSelect){
        mn_fb_cb_event_select.addActionListener(listenForCustomerSelect);
    }
    
    public void setFBawnser1(String instr){
        mn_fb_lb_question_one_response.setText(instr);
    }
    
    public void setFBawnser2(String instr){
        mn_fb_lb_question_two_response.setText(instr);
    }
    
    public Event getFBSelectedEvent(){
        return (Event)mn_fb_cb_event_select.getSelectedItem();
    }
    
}
