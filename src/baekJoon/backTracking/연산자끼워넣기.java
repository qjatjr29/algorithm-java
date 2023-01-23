package baekJoon.backTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 연산자끼워넣기 {

  private static int[] numbers;
  private static long max_answer = -10000000001L;
  private static long min_answer = 10000000001L;
  private static String[] oper_String = {"plus", "minus", "multiply", "divide"};

  public static void main(String[] args) throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int cnt = Integer.parseInt(st.nextToken());
    numbers = new int[cnt];

    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < cnt; i++) {
      numbers[i] = Integer.parseInt(st.nextToken());
    }

    st = new StringTokenizer(br.readLine());
    int[] opers = new int[4];
    for(int i = 0; i < 4; i++) {
      opers[i] = Integer.parseInt(st.nextToken());
    }

    calculate(1, numbers[0], opers);

    bw.write(String.valueOf(max_answer));
    bw.newLine();
    bw.write(String.valueOf(min_answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }

  private static void calculate(int idx, long num, int[] opers) {

    if(idx >= numbers.length) {
      max_answer = Math.max(max_answer, num);
      min_answer = Math.min(min_answer, num);
      return;
    }

    for(int i = 0; i < opers.length; i++) {
      if(opers[i] == 0) continue;
      if(numbers[idx] == 0 && i == 3) continue;

      long next_num = Operator
          .of(oper_String[i])
          .calculate(num , numbers[idx]);
      opers[i]--;
      calculate(idx + 1, next_num, opers);
      opers[i]++;
    }

  }

  private enum Operator {
    PLUS("plus") {
      @Override
      public long calculate(long num1, int num2) {
        return num1 + num2;
      }
    },
    MINUS("minus")  {
      @Override
      public long calculate(long num1, int num2) {
        return num1 - num2;
      }
    },
    MULTIPLY("multiply")  {
      @Override
      public long calculate(long num1, int num2) {
        return num1 * num2;
      }
    },
    DIVIDE("divide")  {
      @Override
      public long calculate(long num1, int num2) {
        if(num1 < 0) {
          return (-1) * (Math.abs(num1) / num2);
        }
        return num1 / num2;
      }
    };

    String value;

    Operator(String value) {
      this.value = value;
    }

    abstract long calculate(long num1, int num2);

    public static Operator of(String value) {
      return Operator.valueOf(value.toUpperCase());
    }
  }

}
