/*
 * Project: Threads of Glory, BOLDNESS
 *  
 * Summary:
 *   GUI for starting runnable classes and displaying the current threads.
 * 
 * Author:
 *   Summer Smith
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
import java.lang.ClassLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author PROXYpc
 */
public class ThreadsOfGlory extends Application {
    //Member variables
    ClassLoader load;
    Thread thread;
    ObservableList<String> runnables;
    ListView <String> runList;
    ListView <String> threadList;
    TextField userInput;
    Scene scene;
    private static ThreadsOfGlory instance; //using singleton method
    
    public static void main(String[] args) {
        launch(args);
    }
       
    @Override
    public void start(Stage primaryStage) {
        
        setScene();
        primaryStage.setTitle("Threads of Glory");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static ThreadsOfGlory getInstance(){
        return instance;
    }
        
    public boolean keepRunning(){
        return false;
    }
    
    public boolean validClass(String name){
        //boolean isRunnable = true;
        try{
            Class runnable = Class.forName(name);
            boolean isRunnable = Runnable.class.isAssignableFrom(runnable);          
            return isRunnable;
        }catch(Exception e){
            System.out.println("ERROR: Class not found");
            System.out.println(e);
        };
        return false;
    }
    
    private void removeClass(){
        
    }
    
    private HBox hbox1(){
        //input area includes instruction label and text input area
        load = ThreadsOfGlory.class.getClassLoader();
        userInput = new TextField();  
        Label useIn = new Label("Enter Runnable:");
         
        //Action event for text entered and "Enter" key hit
        userInput.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String usrIn = userInput.getText();
                
                //Only add each class once
                if(runnables.contains(usrIn)){
                    userInput.clear();
                    userInput.requestFocus();
                } 
                else if(validClass(usrIn)){
                    //Load class
                    try{
                        Class newClass = load.loadClass(usrIn);
                        runnables.add(usrIn);  
                    
                        userInput.clear();
                        userInput.requestFocus();
                    }catch (ClassNotFoundException e){
                        System.out.println("ERROR: Class not found");
                        System.out.println(e);
                    };
                        
                } else {
                    userInput.clear();
                    userInput.requestFocus();     
                }
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
    
    private HBox hbox2(){
        //Start thread
        Button startBtn = new Button();
        startBtn.setText("Start");
        startBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String runName = runList.getSelectionModel().getSelectedItem();
                try {
                    Runnable newRun = (Runnable)Class.forName(runName).newInstance();
                    thread = new Thread(newRun);
                    thread.start();
                    thread.setName(runName + " - " + thread.getName());
                    threadList.getItems().add(thread.getName());
                }catch (Exception e){
                    System.out.println("ERROR: " + e);
                }
            }
        });
            
        //Stop thread
        Button stopBtn = new Button();
        stopBtn.setText("Stop");
        stopBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               //Kill threads here     
            }
        });
        
        //GUI goodies (setup)
         HBox hbox2 = new HBox();
         hbox2.setPadding(new Insets(15));
         hbox2.setSpacing(10);
         hbox2.setAlignment(Pos.CENTER);
         hbox2.getChildren().addAll(startBtn, stopBtn);
         
         return hbox2;
    }
    
    private VBox vbox1(){
         runnables =  FXCollections.observableArrayList();
         runList = new ListView<>();
         Label runLabel = new Label("Runnables");
        
        //GUI goodies (setup)
         VBox vbox1 = new VBox();
         vbox1.setPadding(new Insets(15));
         vbox1.setSpacing(10);
         runList.setItems(runnables);
         vbox1.getChildren().addAll(runLabel, runList);
        
        return vbox1;
    }
    
    private VBox vbox2(){
         threadList = new ListView<>();
         Label threadLabel = new Label("Threads");
         
         //GUI goodies (setup)   
         VBox vbox2 = new VBox();
         vbox2.setPadding(new Insets(15));
         vbox2.setSpacing(10);
         vbox2.getChildren().addAll(threadLabel, threadList);
         
         return vbox2; 
    }
    
    public void setScene(){
        BorderPane root= new BorderPane();
        
        root.setTop(this.hbox1());
        root.setLeft(this.vbox1());
        root.setRight(this.vbox2());
        root.setBottom(this.hbox2());
               
        scene = new Scene(root, 500, 500);      
    }

  
}
