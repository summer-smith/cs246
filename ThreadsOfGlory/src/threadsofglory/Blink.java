package threadsofglory;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;

/**
 * buttonBlink applies a drop shadow
 * to all the buttons on the GUI to give them
 * an unobtrusive blinking effect.
 * 
 * @author Summer Smith
 */
public class Blink implements Runnable{
    private ThreadsOfGlory glory = ThreadsOfGlory.getInstance();
    private final DropShadow shadow = new DropShadow();
    private Button button1;
    private Button button2;
    
    /**
     * Run sets a shadow on the start and stop buttons
     * from the ThreadsOfGlory GUI
     */
    @Override
    public void run(){
        
        button1 = glory.getStartBtn();
        button2 = glory.getStopBtn();
        
        while (glory.keepRunning()){
            try {
                Platform.runLater(new Runnable(){
                    @Override
                    public void run(){
                        button1.setEffect(shadow);
                        button2.setEffect(shadow);
                    }
                });
                
                Thread.sleep(500);
            
                Platform.runLater(new Runnable(){
                    @Override
                    public void run(){    
                        button1.setEffect(null);
                        button2.setEffect(null);
                        }
                    });
                Thread.sleep(500);
        
            }catch (Exception E){
            System.out.println("ERROR: " + E);
            }
        }
    }
}
 