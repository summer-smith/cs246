package favoritescriptures;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Summer Smith
 */
public class FavoriteScriptures extends Application {
    
    ListView <String> scriptures;
    TextField userInput;
    
    @Override
    public void start(Stage primaryStage) {
        
       scriptures = new ListView<String>();
        
       userInput = new TextField();
        
        
        Button btn = new Button();
        btn.setText("Add to list");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
             String scripture = userInput.getText();
             scriptures.getItems().add(scripture);
             userInput.clear();
             userInput.requestFocus();          
                      
            }
        });
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        /**(add, col, row*/

        grid.add(new Label("Scripture"), 1, 1);
        grid.add(userInput, 1, 2);
        grid.add(btn, 2, 2);
        grid.add(new Label("Scriptures"), 3, 1);
        grid.add(scriptures, 3, 2);
        
        Scene scene = new Scene(grid, 500, 500);
        
        primaryStage.setTitle("Hello World!");
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
