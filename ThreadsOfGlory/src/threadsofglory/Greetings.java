package threadsofglory;

import javafx.application.Platform;
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
 * Greetings will open a new GUI window that will
 * output a cheerful greeting!
 *
 * @author Summer Smith
 */

public class Greetings implements Runnable{
    private ThreadsOfGlory glory = ThreadsOfGlory.getInstance();
    private Stage newStage;
    private BorderPane root;
    private Scene scene;
    
    /**
     * Makes a GUI pop-up window
     */
    @Override
    public void run() {
        int i = 0;
        
        //continue looping to keep checking if this should still be running
	while(true)
            //only build once
            if (glory.keepRunning() && i == 0){
            Platform.runLater(new Runnable(){
                @Override
                    public void run() {
			newStage = new Stage();                       
                        newStage.setTitle("Greetings!");
                        root = new BorderPane();
                        root.setCenter(makeBody());
                        scene = new Scene(root, 200, 200);
                        newStage.setScene(scene);
			newStage.show();
			newStage.toFront();
                         
                    }                  
                });
                i++;
            }else {
                newStage.close();
            }
           
    }
    
    /**
     * 
     * @return a VBox holding the main body elements.
     */
    private VBox makeBody(){
        VBox body = new VBox();
        body.getChildren().addAll(this.topBox(), this.bottomBox());
        body.setPadding(new Insets(10));
        body.setSpacing(10);
        body.setAlignment(Pos.CENTER);
        
        return body;
    }
    
    /**
     * 
     * @return the HBox to fill the top of the center area
     */
    private HBox topBox(){
        HBox topBox = new HBox();
        Label greeting = new Label();
        greeting.setText("Hello!  How are you today?");
        
        topBox.getChildren().add(greeting);
        
        return topBox;
    }
    
    /**
     * 
     * @return the HBox to fill the bottom of the center area
     */
    private HBox bottomBox(){
        HBox bottomBox = new HBox();
        
        Button closeBtn = new Button();
        closeBtn.setText("Close");
        closeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newStage.close();
            }
        }); 
        
        bottomBox.getChildren().add(closeBtn);
        
        return bottomBox;
    }
    
}
