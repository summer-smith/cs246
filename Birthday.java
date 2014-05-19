/**
* Project:
*   Birthday Reminder
*
* Project outline:
*   Given a list of dates, the program will determine if there are any
*   birthdays today.
*
* Author:
*   Summer Smith
*
* TO DO:
* -create a string of names with birthdays
* -implement read/write file
* -interface
**/
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.FileReader;

public class Birthday {

   private HashSet<Calendar> birthdays = new HashSet<Calendar>();
   private Calendar today;
   
   public static void main(String[] args) {
      Birthday bday = new Birthday();
      bday.run();
   }

   void run() {
      System.out.println("Welcome!");
      String fileName = "bdays.txt";
      readFile(fileName);
      getToday();
      isBirthday();
      //create a hash set of calendar objects!  Easy to add
      //to in read file, and easy to search in isBirthday.
   
   }

   void readFile(String fileName){
    BufferedReader in = null;
    
      try {
         in = new BufferedReader(new FileReader(fileName));
         String x = "";
         String cal;
         String[] data;
      
         while ((x=in.readLine()) != null) {
            data = x.split("-");
            cal = new GergorianCalendar(data[0], data[1], data[2]);
            birthdays.add(cal);
         }
       }catch(Exception E){
         System.out.println("\nERROR in readFile(): " + E);
      }
   }

   void getToday(){
      DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
      today = Calendar.getInstance();

      System.out.print("Today is ");
      System.out.println(format.format(today.getTime()));

   }

   void isBirthday() {
      //Need to parse our birthday string
      //getDisplayName(int field, int style, Locale locale)
      //Calendar method returns the string representation
      //of the calendar field value in the given styel and locale
     
      //if today's date matches one of the dates in our string
      //if(){

      //}
      //call display
      //else
         System.out.println("No birthdays today.");
      
   }

   void upcomingBirthdays(){
      //To be implemented later:
      //Will output all birthdays coming up in the current month
   }

   void displayBirthday(String data)
   {
      //parse out name & birthday
      String name;
      String birthday;
      //System.out.println(name + " has a birthday today, " + birthday + "!"); 
      System.out.println("There is a birthday today!");

   }

   
    
}
