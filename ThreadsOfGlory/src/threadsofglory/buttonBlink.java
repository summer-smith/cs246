package threadsofglory;

/**
 * buttonBlink applies a drop shadow
 * to all the buttons on the GUI to give them
 * an unobtrusive blinking effect.
 *
 * THIS DOES NOT WORK- HANGS GUI
 * 
 * @author Summer Smith
 */

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.effect.*;

public class buttonBlink implements Runnable{
        
    private final DropShadow shadow = new DropShadow();
    private Button button1;
    private Button button2;
    
    
    @Override
    public void run(){
        ThreadsOfGlory glory = ThreadsOfGlory.getInstance();
        button1 = glory.getStartBtn();
        button2 = glory.getStopBtn();
        
        try {
            for (int i =0; i < 50; i++){
                while (glory.keepRunning()){
                    
                    Platform.runLater(new Runnable(){
                        @Override
                        public void run(){
                            try {
                            button1.setEffect(shadow);
                            button2.setEffect(shadow);
                        
                            Thread.sleep(300);
            
                    
                            button1.setEffect(null);
                            button2.setEffect(null);
                            }catch (InterruptedException e){
                                System.out.println("ERROR: " + e);
                            }
                        }
                    });
                }
            
            }
        }catch (Exception E){
            System.out.println("ERROR: " + E);
        }
        
    }     
}  
