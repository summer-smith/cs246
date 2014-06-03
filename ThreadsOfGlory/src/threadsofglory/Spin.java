package threadsofglory;


import javafx.scene.control.Label;

/**
 *
 * @author PROXYpc
 */
public class Spin extends Thread{
    
    private ThreadsOfGlory glory = ThreadsOfGlory.getInstance();
    
    
    @Override
    public void run(){
        Label myLabel = glory.getUseIn();
        
        while (glory.keepRunning())
            try{
                myLabel.setRotate(45);
                Thread.sleep(50);
                myLabel.setRotate(90);
                Thread.sleep(50);
                myLabel.setRotate(135);
                Thread.sleep(50);
                myLabel.setRotate(180);
                Thread.sleep(50);
                myLabel.setRotate(225);
                Thread.sleep(50);
                myLabel.setRotate(270);
                Thread.sleep(50);
                myLabel.setRotate(315);
                Thread.sleep(50);
                myLabel.setRotate(360);
                Thread.sleep(50);
            }catch (Exception e){
                System.out.println("ERROR: " + e);
            }
       
        
    }
}

   
