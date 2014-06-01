package threadsofglory;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Platform;

import javafx.scene.control.Button;
import javafx.scene.Group;
import javafx.scene.shape.Circle;

/**
 * Greetings will open a new GUI window that will
 * output a cheerful greeting!
 *
 * @author Summer Smith
 */
public class Greetings extends Thread{
   
    
    @Override
    public void run() {
		
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				Stage third = new Stage();
				third.setTitle("new GUI");
				Group root = new Group();
				Circle ball = new Circle(100,100,20);
				ball.relocate(200,200);
				root.getChildren().add(ball);
				Scene scene = new Scene(root);
				third.setScene(scene);
				third.show();
				third.toFront();
			}
		});
	}
    
}
