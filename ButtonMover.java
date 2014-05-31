/*package threadsofglory;

public class ButtonMover extends Thread{
    @Override
    public void run(){
        ThreadsOfGlory glory = ThreadsOfGlroy.getInstance();
        Button button = glory.getStopButton();//make button Glory member variable, make a getter
        System.out.println("In the thread class.");
        
        try {
        for (int i =0; i < 50; i++){
            Platform.runLater(new Runnable({
                @Override
                public void run(){
                button.setText("Move!");
                }
            });
            
            Thread.sleep(100);
            
            Platform.runLater(new Runnable({
                @Override
                public void run(){
                button.setText("Stop");
            }
            });
            
            Thread.sleep(100);
        }
        }catch Exception E{
            System.out.println("ERROR: " + E);
        }
    }     
}  
   
  */