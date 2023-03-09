package baekJoon.string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/9935
public class 문자열폭발 {

  private static final String NOT_REMAIN_STRING = "FRULA";

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    String inputString = st.nextToken();
    int inputSize = inputString.length();

    st = new StringTokenizer(br.readLine());
    String bombString = st.nextToken();
    int bombSize = bombString.length();

    char[] result = new char[1000001];
    int resultIndex = 0;

    for(int i = 0; i < inputSize; i++) {

      char c = inputString.charAt(i);
      result[resultIndex] = c;
      resultIndex++;

      if(resultIndex - bombSize < 0) continue;

      int bSize = bombSize;

      if(c == bombString.charAt(bSize - 1)) {
        boolean isRemove = true;
        bSize--;
        for (int j = resultIndex - 1; j >= resultIndex - bombSize; j--) {
          if(result[j] != bombString.charAt(bSize)) {
            isRemove = false;
            break;
          }
          bSize--;
        }
        if(isRemove) resultIndex -= bombSize;
      }
    }

    if(resultIndex == 0) bw.write(NOT_REMAIN_STRING);
    else {

      StringBuilder sb = new StringBuilder();
      for(int i = 0; i < resultIndex; i++) {
        sb.append(result[i]);
      }
      bw.write(sb.toString());
    }

    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }
}
