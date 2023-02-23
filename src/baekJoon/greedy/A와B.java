package baekJoon.greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12904
public class A와B {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    String source = st.nextToken();

    st = new StringTokenizer(br.readLine());
    String target = st.nextToken();

    // 만들어야하는 문자열의 마지막 문자부터 확인 (rightPointer 사용)
    // 문자가 'B'라면 그 앞의 문자들을 뒤집어서 생각해야 한다. -> isReverse를 반대로
    // 뒤집어서 생각하는 경우에는 문자열의 앞부터 확인한다. (leftPointer 사용)
    // 또 다시 뒤집히면 다시 뒤에서부터 확인한다.
    int rightPointer = target.length() - 1;
    int leftPointer = 0;
    boolean isReverse = false;
    int answer = 0;

    while(true) {

      // 주어진 만들기전 문자열의 길이만큼 확인했다면 해당 문자열이 지금 남은 문자열과 같은지 확인한다.
      if(rightPointer - leftPointer + 1 == source.length()) {
        String substring = target.substring(leftPointer, rightPointer + 1);

        if(isReverse) {
          StringBuffer sb = new StringBuffer(substring).reverse();
          substring = sb.toString();
        }
        if(source.equals(substring)) answer = 1;
        break;
      }

      // 뒤집혀 있는 경우
      if(isReverse) {
        char compare = target.charAt(leftPointer);
        if(compare == 'A') leftPointer++;
        // 뒤집어야 한다.
        else {
          leftPointer++;
          isReverse = false;
        }
      }

      else {
        char compare = target.charAt(rightPointer);
        if(compare == 'A') rightPointer--;
          // 뒤집어야 한다.
        else {
          rightPointer--;
          isReverse = true;
        }
      }
    }

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }

}
