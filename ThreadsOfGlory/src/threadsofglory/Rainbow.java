package threadsofglory;

/**
 * Rainbow will rotate the GUI background
 * through Purple, Blue, Green, Yellow, Orange, Red .
 *
 * @author Summer Smith
 */

import javafx.beans.property.*;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Rainbow implements Runnable{
    private String Red;
    private String Green;
    private String Blue;
    private String thisColor;
    private Stage color = ThreadsOfGlory.getInstance().getPrimaryStage();
    private ObjectProperty<Color> next;
    private static ArrayList<ObjectProperty<Color>> colors;
    ThreadsOfGlory glory = ThreadsOfGlory.getInstance();
    
    @Override
    public void run(){
        //Scene scene = new Scene(new Group(), 500, 400);
        //scene.getStylesheets().add("path/stylsheet.css");
        
        //.custom-button {
        //-fx-background-color:white;
        //}
        
        while (glory.keepRunning()){
            for(int i = 0; i < 100; i++){
                try{
                    //scene.getRoot().setStyle("-fx-background-color: " + colorValue +";");
                    next = colors.get(i);
        
                    
                    thisColor = "-fx-background-color: rgba("
                        + ((int) (next.get().getRed() * 255)) + ","
                        + ((int) (next.get().getGreen() * 255)) + ","
                        + ((int) (next.get().getBlue() * 255)) + ")";    

           
                    Platform.runLater(new Runnable(){
                        
                        @Override
                        public void run() {
                            color.getScene().getRoot().setStyle("-fx-background-color: " + thisColor + ";");
                        }
                    });
            
                }catch (Exception e){
                System.out.println("Rainbow Terimated - Exception thrown");
                }
            }
        }
    };
    
    public void buildArray(){
        ObjectProperty<Color> purple = new SimpleObjectProperty<>(Color.PURPLE);
        ObjectProperty<Color> blue = new SimpleObjectProperty<>(Color.BLUE);
        ObjectProperty<Color> green = new SimpleObjectProperty<>(Color.GREEN);
        ObjectProperty<Color> yellow = new SimpleObjectProperty<>(Color.YELLOW);
        ObjectProperty<Color> orange = new SimpleObjectProperty<>(Color.ORANGE);
        ObjectProperty<Color> red = new SimpleObjectProperty<>(Color.RED);
        
        colors.add(purple);
        colors.add(blue);
        colors.add(green);
        colors.add(yellow);
        colors.add(red);
        colors.add(orange);     
    }
    
    public static void main(String[] args) {
        Rainbow makeItRain = new Rainbow();
        makeItRain.run();
    }
}
