package threadsofglory;

import javafx.application.Platform;
import javafx.scene.Scene;
import java.util.ArrayList;

/**
 * Rainbow will rotate the GUI background
 * through Purple, Blue, Green, Yellow, Orange, Red .
 *
 * @author Summer Smith
 */

public class Rainbow implements Runnable{
    private String Red;
    private String Green;
    private String Blue;
    private String thisColor;
    private String style;
    private Scene colorMe;
    private String next;
    private static ArrayList<String> colors;
    private ThreadsOfGlory glory = ThreadsOfGlory.getInstance();
    
    /**
     * Changes the CSS on the background to the colors defined
     * in the colors array.
     */
    @Override
    public void run(){
        colorMe = glory.getScene();
        colors = new ArrayList<>();
        fillArray();
        
        while (glory.keepRunning()){
            for(int i = 0; i < 7; i++){
                try{
                    next = colors.get(i);
        
                    style = "-fx-background-color: " + next + ";";
           
                    Platform.runLater(new Runnable(){
                        
                        @Override
                        public void run() {
                            colorMe.getRoot().setStyle(style);
                        }
                    });
                                        
                    Thread.sleep(800);
                    
                }catch (Exception e){
                    System.out.println("Rainbow Terimated - ERROR: " + e);
                }
            }
        }
    };
    
    /**
     * Return a filled array
     */
    private void fillArray(){
        colors.add("purple");
        colors.add("blue");
        colors.add("green");
        colors.add("yellow");
        colors.add("orange");
        colors.add("red");
        colors.add("white");
    }
   
}
