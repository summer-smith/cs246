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
    
    public void removeClass(){
        
    }
    
    public void setScene(){
       /**Class member variables*/
       runnables =  FXCollections.observableArrayList();
       load = ThreadsOfGlory.class.getClassLoader();
       threadList = new ListView<>();
       runList = new ListView<>();
       userInput = new TextField();
        
        //Adding a class (runnable)
        
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
        
        //Labels
        Label useIn = new Label("Enter Runnable:");
        Label runLabel = new Label("Runnables");
        Label threadLabel = new Label("Threads");
        
        //GUI goodies (setup)
        HBox hbox1 = new HBox();
        hbox1.setPadding(new Insets(15));
        hbox1.setSpacing(10);
        hbox1.setAlignment(Pos.CENTER);
        hbox1.getChildren().addAll(useIn, userInput);
        
        HBox hbox2 = new HBox();
        hbox2.setPadding(new Insets(15));
        hbox2.setSpacing(10);
        hbox2.setAlignment(Pos.CENTER);
        hbox2.getChildren().addAll(startBtn, stopBtn);
        
        VBox vbox1 = new VBox();
        vbox1.setPadding(new Insets(15));
        vbox1.setSpacing(10);
        runList.setItems(runnables);
        vbox1.getChildren().addAll(runLabel, runList);
        
        VBox vbox2 = new VBox();
        vbox2.setPadding(new Insets(15));
        vbox2.setSpacing(10);
        vbox2.getChildren().addAll(threadLabel, threadList);
        
        BorderPane root= new BorderPane();
        root.setTop(hbox1);
        root.setLeft(vbox1);
        root.setRight(vbox2);
        root.setBottom(hbox2);
        
        
        scene = new Scene(root, 500, 500);
        
    }

  
}
