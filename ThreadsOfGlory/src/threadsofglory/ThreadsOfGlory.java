/*
 * Project: Threads of Glory, BOLDNESS
 *  
 * Summary:
 *   GUI for starting runnable classes and displaying the current threads.
 * 
 * Author:
 *   Summer Smith
 */

/**
 * Authors Notes:
 *   I did not get multiple selection on the threads list to work. I 
 *   added the following line:
 *      threadList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
 *   in my Start() method, but I think it needs some help from SelectionModel 
 *   in order for it to work as expected. I ran out of time before I could
 *   implement properly.
 * 
 *   I also failed to find a suitable method to ensure that the Running Threads
 *   listview updated whenever a thread stopped on it's own.  
 * 
 *   I wasted a huge amount of time on a stupid error, which has limited my
 *   ability to finish out the project to the best of my abilities.  I can
 *   say that my knowledge of javaFX and threads has increased dramatically 
 *   over this short period!
 */

package threadsofglory;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;


/**
 * Creates a GUI to manage runnables and threads.
 * 
 * @author PROXYpc
 */

//Collaborators: Joshua Jolley (using vbox/hbox instead of grid)
//               Elfre Valdez (Assistance with listview use and starting/stopping threads)

public class ThreadsOfGlory extends Application {
    
    //Containers
    private ListView <Runnable> runnables;
    private ArrayList<Thread> threads;
    private ListView <String> threadList;
    
    //Variables
    private Thread thread;
    private TextField userInput;
    private Scene scene;
    private Stage primaryStage;
    private static ThreadsOfGlory instance; 
    Label useIn;
    
    //Buttons
    private Button startBtn = new Button();
    private Button stopBtn = new Button();
    
    /**
     * Main calls launch to begin the program.
     * 
     * @param args 
     */
    public static void main(String[] args) {
        launch(args);
    }
       
    /**
     * Calls appropriate functions to set up the window.
     * 
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage) {
        instance = this;
        runnables =  new ListView<>();
        threads =  new ArrayList<>();
        threadList = new ListView<>();
        threadList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
      
        setScene();
        primaryStage.setTitle("Threads of Glory");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * Initializes the root pane and calls functions to 
     * fill in correct locations with content
     */
    public void setScene(){
        BorderPane root= new BorderPane();
        
        root.setTop(this.hbox1());
        root.setCenter(this.hbox2());
               
        scene = new Scene(root, 600, 500)  ;
    }
    
    /**
     * 
     * @return an instance of <i>this</i> for use in threads.
     */
    public static ThreadsOfGlory getInstance(){
        return instance;
    }
     
    public Label getUseIn(){
        return this.useIn;
    }
    
    /**
     * @return the GUI primary stage.
     */
    public Scene getScene(){
        return this.scene;
    }
    
    
    
    /**
     * @return the GUI Start button.
     */
    public Button getStartBtn(){
        return startBtn;
    }
    
    
    /** 
     * @return the GUI Stop button.
     */
    public Button getStopBtn(){
        return stopBtn;
    }
    
    /**
     * Returns true if the current thread is still in the
     * threadList (meaning it should still be running).
     * 
     * @return true if the current thread is still in the threadList
     */
    public boolean keepRunning(){
       String threadName = Thread.currentThread().getName();
       
       boolean keepRunning = threadList.getItems().contains(threadName);
       return keepRunning;
    }
    
    /**
     * @return an HBox containing the desired content for 
     * the top of the page.

     */
    private HBox hbox1(){
        //input area includes instruction label and text input area
        userInput = new TextField();  
        
        useIn = new Label("Enter Runnable:");
         
        //Action event for text entered and "Enter" key hit
        userInput.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String usrIn = userInput.getText();
                String name = ("threadsofglory." + usrIn);
                
                
                try{
                    Class<?> runnable = Class.forName(name);
                    Runnable runMe = (Runnable) runnable.newInstance();
                    
                    //Only add each class once
                    if(runnables.getItems().contains(runMe)){
                        userInput.clear();
                        userInput.requestFocus();
                        return;
                    } 
                    //If it's a valid class, load class
                    if(Runnable.class.isAssignableFrom(runnable)){
                        //Class newClass = load.loadClass(usrIn);
                        
                        runnables.getItems().add(runMe);  
                        
                        //Select the class that was just added
                        int i = runnables.getItems().indexOf(runMe);
                        runnables.getSelectionModel().select(i);
                        userInput.clear();
                    }
                }catch (Exception e){
                    System.out.println("ERROR: Class not found");
                    System.out.println(e);
                };
                userInput.clear();
                userInput.requestFocus();  
                        
            }
        });
      
        
        //GUI goodies (setup)
        HBox hbox1 = new HBox();
        hbox1.setPadding(new Insets(15));
        hbox1.setSpacing(10);
        hbox1.setAlignment(Pos.CENTER);
        hbox1.getChildren().addAll(useIn, userInput);
        
        return hbox1;
    }
    
    /**
     * @return an HBox containing the desired content for
     * the bottom of the page.
     */
    private HBox hbox2(){
        HBox center = new HBox();
        center.setPadding(new Insets(15));
        center.setSpacing(10);
        center.setAlignment(Pos.CENTER);
        center.getChildren().addAll(this.vbox1(), this.vbox2());
        
        return center;
    }
    
        
    /**
     * @return a VBox containing the desired content for 
     * the left side of the window.
     */
    private VBox vbox1(){
        //Start thread
        startBtn.setText("Start");
        startBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Runnable newRun = runnables.getSelectionModel().getSelectedItem();
                try {
                    thread = new Thread(newRun);
                    thread.start();
                    thread.setName(newRun + "-" + thread.getName());
                    threadList.getItems().add(thread.getName());
                    threads.add(thread);
                }catch (Exception e){
                    System.out.println("ERROR: " + e);
                }
            }
        });
           
                 Label runLabel = new Label("Runnables");
        
        //GUI goodies (setup)
         VBox vbox1 = new VBox();
         vbox1.setPadding(new Insets(15));
         vbox1.setPadding(new Insets(15));
         vbox1.setSpacing(10);
         vbox1.setAlignment(Pos.CENTER);
         vbox1.getChildren().addAll(runLabel, runnables, startBtn);
        
        return vbox1;
    }
    
    /**
     * @return a VBox containing the desired content for the 
     * right side of the window.
     */
    private VBox vbox2(){
        //Stop thread
        stopBtn.setText("Stop");
        stopBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!threadList.getSelectionModel().getSelectedItem().isEmpty()){
                    String stopThread = threadList.getSelectionModel().getSelectedItem();
                    int stopIndex = threadList.getSelectionModel().getSelectedIndex();
                    threadList.getItems().remove(stopThread);
                    Thread stopMe = threads.get(stopIndex);
                    threads.remove(stopMe);
                }
            }
        }); 
        
        
         Label threadLabel = new Label("Threads");
         
         //GUI goodies (setup)   
         VBox vbox2 = new VBox();
         vbox2.setPadding(new Insets(15));
         vbox2.setSpacing(10);
         vbox2.setAlignment(Pos.CENTER);
         vbox2.getChildren().addAll(threadLabel, threadList, stopBtn);
         
         return vbox2; 
    }
    
    
      
}
