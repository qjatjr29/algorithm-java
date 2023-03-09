package baekJoon.string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/5430
public class AC {

  private static final Character REVERSE = 'R';
  private static final Character DELETE = 'D';

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int T = Integer.parseInt(st.nextToken());

    for(int testcase = 0; testcase < T; testcase++) {

      st = new StringTokenizer(br.readLine());

      String commands = st.nextToken();

      st = new StringTokenizer(br.readLine());
      int size = Integer.parseInt(st.nextToken());

      st = new StringTokenizer(br.readLine());
      String inputStr = st.nextToken();
      String[] arrayStr = inputStr.substring(1, inputStr.length() - 1).split(",");

      boolean isError = false;
      boolean isReverse = false;
      int lPoint = 0;
      int rPoint = size - 1;
      int deleteCount = 0;

      for(int i = 0; i < commands.length(); i++) {

        char command = commands.charAt(i);

        if(command == REVERSE) isReverse = !isReverse;

        if(command == DELETE) {

          if(deleteCount == size) {
            isError = true;
            break;
          }
          if(!isReverse) lPoint++;
          else rPoint--;
          deleteCount++;
        }
      }

      if(isError) bw.write("error");

      else {
        StringBuilder sb = new StringBuilder();

        sb.append("[");

        if(lPoint <= rPoint) {
          if(!isReverse) {
            for(int i = lPoint; i < rPoint; i++) {
              sb.append(arrayStr[i]);
              sb.append(",");
            }
            sb.append(arrayStr[rPoint]);
          }

          else {
            for(int i = rPoint; i > lPoint; i--) {
              sb.append(arrayStr[i]);
              sb.append(",");
            }
            sb.append(arrayStr[lPoint]);
          }
        }
        sb.append("]");


        bw.write(sb.toString());
      }
      bw.newLine();
    }
    bw.flush();
    bw.close();
    br.close();

  }

}
