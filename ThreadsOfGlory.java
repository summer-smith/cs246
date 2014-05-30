/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
/**
 *
 * @author PROXYpc
 */
public class ThreadsOfGlory extends Application {
    ListView <String> runnables;
    ListView <String> threads;
    TextField userInput;
    
    @Override
    public void start(Stage primaryStage) {
       /**Class member variables*/
       runnables = new ListView<>();
       threads = new ListView<>();
       userInput = new TextField();
        
        /**Adding a new runnable*/
        Button addButton = new Button();
        addButton.setText("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
             String usrIn = userInput.getText();
             runnables.getItems().add(usrIn);  //TO DO: Add check for valid runnable
             //(SHould implement Runnable)
             userInput.clear();
             userInput.requestFocus();          
                      
            }
        });
        
        /**Start thread*/
        Button startBtn = new Button();
        startBtn.setText("Start");
        
        /**Stop thread*/
        Button stopBtn = new Button();
        stopBtn.setText("Stop");
        
        
        /**Labels*/
        Label useIn = new Label("Enter Runnable:");
        Label runLabel = new Label("Runnables");
        Label threadLabel = new Label("Threads");
        
        /**GUI goodies */
        HBox hbox1 = new HBox();
        hbox1.setPadding(new Insets(15));
        hbox1.setSpacing(10);
        hbox1.setAlignment(Pos.CENTER);
        hbox1.getChildren().addAll(useIn, userInput, addButton);
        
        HBox hbox2 = new HBox();
        hbox2.setPadding(new Insets(15));
        hbox2.setSpacing(10);
        hbox2.setAlignment(Pos.CENTER);
        hbox2.getChildren().addAll(startBtn, stopBtn);
        
        VBox vbox1 = new VBox();
        vbox1.setPadding(new Insets(15));
        vbox1.setSpacing(10);
        vbox1.getChildren().addAll(runLabel, runnables);
        
        VBox vbox2 = new VBox();
        vbox2.setPadding(new Insets(15));
        vbox2.setSpacing(10);
        vbox2.getChildren().addAll(threadLabel, threads);
        
        BorderPane root= new BorderPane();
        root.setTop(hbox1);
        root.setLeft(vbox1);
        root.setRight(vbox2);
        root.setBottom(hbox2);
        
        
        Scene scene = new Scene(root, 500, 500);
        
        primaryStage.setTitle("Threads of Glory");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
