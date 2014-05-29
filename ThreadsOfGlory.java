/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package threadsofglory;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
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
        
       runnables = new ListView<>();
       threads = new ListView<>();
       userInput = new TextField();
        
        
        Button add = new Button();
        add.setText("Add");
        add.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
             String usrIn = userInput.getText();
             runnables.getItems().add(usrIn);  //TO DO: Add check for valid runnable
             //(SHould implement Runnable)
             userInput.clear();
             userInput.requestFocus();          
                      
            }
        });
        
        Button start = new Button();
        start.setText("Start");
        Button stop = new Button();
        stop.setText("Stop");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        /**(add, col, row, rowspan, colspan*/

        grid.add(new Label("Enter Runnable:"), 1, 0);
        grid.add(userInput, 2, 0);
        grid.add(add, 3, 0);
        grid.add(new Label("Runnables"), 1, 1);
        grid.add(runnables, 1, 2);
        grid.add(new Label("Threads"), 3, 1);
        grid.add(threads, 3, 2);
        grid.add(start, 1, 4);
        grid.add(stop, 3, 4);
        
        Scene scene = new Scene(grid, 500, 500);
        
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
