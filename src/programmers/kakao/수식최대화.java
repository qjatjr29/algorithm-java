package programmers.kakao;

import static java.lang.Math.abs;
import static java.lang.Math.max;

import java.util.ArrayList;
import java.util.List;

public class 수식최대화 {

  private static Character[] op = {'*', '+', '-'};
  private static List<Long> numbers;
  private static List<Character> operations;
  private static Long answer;

  public static long solution(String expression) {

    numbers = new ArrayList<>();
    operations = new ArrayList<>();
    answer = 0l;

    String pattern = "\\*|\\+|-";
    String[] numStr = expression.split(pattern);
    for(int i = 0; i < numStr.length; i++) {
      numbers.add(Long.parseLong(numStr[i]));
    }

    String s = expression.replaceAll("[0-9]", "");

    for(int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      operations.add(c);
    }

    char[] ch = new char[3];
    Boolean[] visited = new Boolean[4];
    for(int i = 1; i <= 3; i++) {
      visited[i] = false;
    }
    setup(0, ch, visited);

    return answer;
  }

  public static void main(String[] args) {
    long solution = solution("50*6-3*2");
    System.out.println(solution);
  }

  private static void calculate(char[] opers) {
    List<Long> temp = new ArrayList<>(numbers);
    List<Character> oper = new ArrayList<>(operations);

    for(int i = 0; i < opers.length; i++) {
      for(int j = 0; j < oper.size(); j++) {

        if(opers[i] == oper.get(j)) {
          Long result = calc(temp.get(j), temp.get(j + 1), opers[i]);
          temp.remove(j);
          temp.remove(j);

          temp.add(j, result);
          oper.remove(j);
          j--;
        }
      }
    }
    answer = max(answer, abs(temp.get(0)));
  }

  private static Long calc(Long num1, Long num2, char op) {
    if(op == '*') return num1 * num2;
    else if(op == '+') return num1 + num2;
    else return num1 - num2;
  }

  private static void setup(int cnt, char[] opers, Boolean[] visited) {
    if(cnt == 3) {
      calculate(opers);
      return;
    }

    for(int i = 1; i <= 3; i++) {
      if(!visited[i]) {
        opers[cnt] = op[i - 1];
        visited[i] = true;
        setup(cnt + 1, opers, visited);
        visited[i] = false;
      }
    }

  }

}
