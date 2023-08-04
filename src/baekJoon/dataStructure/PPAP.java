package baekJoon.dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class PPAP {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    String str = st.nextToken();

    Stack<Character> stack = new Stack<>();

    String answer = "";

    for(int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if(c == 'P') stack.push(c);
      else if(c == 'A'){
        if(stack.size() >= 2 && i != str.length() - 1 && str.charAt(i + 1) == 'P') {
          stack.pop();
          stack.pop();
        }
        else {
          answer = "NP";
          break;
        }
      }
    }

    if(!answer.equals("NP") && stack.size() == 1) answer = "PPAP";
    else answer = "NP";

    bw.write(answer);
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }

}
