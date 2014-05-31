package threadsofglory;

public class Counter implements Runnable{
    @Override
    public void run(){
        ThreadsOfGlory glory = ThreadsOfGlory.getInstance();
        try {
            for (int i = 0; i <= 1000000; i++){
                if(!glory.keepRunning()){
                    break;
                }
                System.out.println(i);
                Thread.sleep(10000);
            }
        }catch (InterruptedException e){
                System.out.println("ERROR: " + e);
        };
        
    }
}
