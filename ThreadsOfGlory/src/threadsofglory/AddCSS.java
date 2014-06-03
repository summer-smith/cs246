package threadsofglory;

import javafx.scene.Scene;
import javafx.application.Platform;

/**
 * Adds CSS to the calling GUI
 * 
 * @author Summer Smith
 */
public class AddCSS implements Runnable{
    private ThreadsOfGlory glory = ThreadsOfGlory.getInstance();
    private Scene scene;

    @Override
    public void run(){
        
        scene = glory.getScene();
        
        while (glory.keepRunning()){
            try{
                Platform.runLater(new Runnable(){
                    @Override 
                    public void run(){
                        scene.getStylesheets().add("threadsofglory/buttonStyle.css");
                    }
                });
                Thread.sleep(1500);
                
                Platform.runLater(new Runnable(){
                    @Override 
                    public void run(){
                        scene.getStylesheets().remove("threadsofglory/buttonStyle.css");
                    }
                });
                Thread.sleep(500);
            }catch (Exception e){
                        System.out.println("AddCSS Terminated - ERROR: " + e);
            }
        
        }
    }
}
