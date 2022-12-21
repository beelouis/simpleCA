import java.util.*;

class CA1D{

  private int[] array;
  private HashMap<String, Integer> rule;
  private int[] ruleOutput;
  private Random ran;
  private int numIterations;
  private int maxIterations;

  public CA1D(int numStates, int max, int[] ruleOutput){
    ran = new Random();
    this.ruleOutput = ruleOutput;
    numIterations = 0;
    maxIterations = max;

    array = new int[numStates];
    for (int i = 0; i < array.length; i++){
      array[i] = ran.nextInt(2);
     // array[i] = ran.nextInt(5);
     // array[i] = ran.nextDouble() > 0.1 ? 1 : 0;
    }

    rule = new HashMap<>();
    for (int i = 0; i < 8; i++){
      String code = getBinary(i);
      if (ruleOutput.length > 0){
        rule.put(code, ruleOutput[i]);
      } else {
        rule.put(code, ran.nextInt(2));
      }
    }

    rule.entrySet().forEach(e -> System.out.print(e.getKey() + " => " + e.getValue() + ", "));
    System.out.println("\n" + Arrays.toString(array));
  }

  private String getBinary(int counter){
    String code = "";
      for (int i = 3; i > 0; i--) {
          int k = counter >> i-1;
          if ((k & 1) > 0)   code += "1";
          else               code += "0";
      }
      return code;
  }

  public void update(){
    int[] nextState = new int[array.length];

    for (int i = 0; i < array.length; i++){
      String check = "";
      for (int j = -1; j <= 1; j++){
        int index = (array.length + i + j) % array.length;
        check += Integer.toString(array[index]);
      }
      nextState[i] = rule.get(check);
    }

    // for (int i = 0; i < array.length; i++){
    //   int sum = 0;
    //   for (int j = -1; j <= 1; j++){
    //     int index = (array.length + i + j) % array.length;
    //     sum += array[index];
    //     nextState[i] = array[index];
    //     if (sum >= 3){
    //       break;
    //     }
    //   }
    // }

    array = nextState;

    Arrays.stream(array).forEach(v -> System.out.print(v == 1 ? " * " : " "));
    System.out.println("");
    numIterations++;
    // if (numIterations == maxIterations) System.exit(0);
  }

  public int getMax(){
    return maxIterations;
  }


}
