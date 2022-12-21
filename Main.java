import java.util.*;

class Main{
  public static void main(String[] args){

    int ruleLength = 8;
    int arrayLength = 50;
    int maxIterations = 50;

    Timer timer = new Timer();
    int begin = 1000;
    int timeinterval = 2 * 1000;
    timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
       // int[] rule = {1,0,1,0,1,1,1,1};
        // int[] rule = {0,1,0,0,1,1,1,0};
//         int[] rule = new int[0];
	int[] rule = {1,0,0,0,1,0,0,1};

        CA1D ca = new CA1D(arrayLength, maxIterations, rule);
        for (int i = 0; i < ca.getMax(); i++){
          ca.update();
        }
      }
    }, begin, timeinterval);
  }
}
