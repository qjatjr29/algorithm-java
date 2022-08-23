package programmers.stack;

import java.util.Stack;

public class 올바른괄호 {

  public static boolean solution(String s) {
    boolean answer = true;

    Stack<Character> stack = new Stack<>();

    for(int i = 0; i < s.length(); i++) {
      char curCharacter = s.charAt(i);

      if(curCharacter == '(') {
        stack.push(curCharacter);
      }
      else {
        if(stack.isEmpty()) return false;
        else stack.pop();
      }
    }

    if(!stack.isEmpty()) answer = false;
    return answer;
  }

  public static void main(String[] args) {
//    String s = "()()";
//    String s = "(())()";
    String s = ")()(";
//    String s = "(()(";



    System.out.println(solution(s));
  }

}
