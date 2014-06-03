package threadsofglory;

/**
 * Counter outputs a count to the command line.
 *
 * @author Summer Smith
 */

public class Counter implements Runnable{
    /**
     * Outputs a count to the command line
     */
    @Override
    public void run(){
        ThreadsOfGlory glory = ThreadsOfGlory.getInstance();
        while (glory.keepRunning()){
            try {
                for (int i = 0; glory.keepRunning() && i<10000; i++){
                    System.out.println(i);
                    Thread.sleep(100);
                }
            }catch (Exception e){
                System.out.println("Counter terminated");
            }
        }        
    }
}
