package baekJoon.string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16890
public class 창업 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    String str1 = st.nextToken();
    int length = str1.length();

    st = new StringTokenizer(br.readLine());
    String str2 = st.nextToken();

    Character[] str1Array = new Character[length];
    Character[] str2Array = new Character[length];

    for(int i = 0; i < length; i++) {
      str1Array[i] = str1.charAt(i);
      str2Array[i] = str2.charAt(i);
    }

    Arrays.sort(str1Array); // 오름차순
    Arrays.sort(str2Array, Collections.reverseOrder()); // 내림차순

    List<Character> koosaga = new ArrayList<>(
        Arrays.asList(str1Array).subList(0, (int) (length + 1) / 2));
    List<Character> cubelover = new ArrayList<>(Arrays.asList(str2Array).subList(0, length / 2));


    char[] companyName = new char[length];
    int lPoint = 0;
    int rPoint = length - 1;
    int time = 0;

    int kooStartPoint = 0;
    int kooEndPoint = koosaga.size() - 1;
    int cubeStartPoint = 0;
    int cubeEndPoint = cubelover.size() - 1;
    StringBuilder sb = new StringBuilder();

    while(true) {

      if(time == length) break;
      if(lPoint > rPoint) break;

      // 구사과 차례
      // 구사과의 문자열 중 가장 사전순으로 빠른 문자가 큐브러버의 문자열의 사전순으로 가장 느린 문자보다 느리거나 같을 때
      // 같을 경우도 포함하는 이유 : 현재 구사과의 가장 빠른 문자가 큐브러버 문자의 가장 느린 문자와 같다면
      // 큐브 러버의 남은 문자들이 구사과의 문자들보다 빠르기 때문에 구사과의 가장 느린문자를 가장 뒤에 놓아야 함.
      if(time % 2 == 0) {

        // 큐브러버가 위를 넘어갔을 수 있다.
        if(cubeStartPoint == cubelover.size() || koosaga.get(kooStartPoint) < cubelover.get(cubeStartPoint)) {
          companyName[lPoint] = koosaga.get(kooStartPoint);
          lPoint++;
          kooStartPoint++;
        }

        else {
          companyName[rPoint] = koosaga.get(kooEndPoint);
          rPoint--;
          kooEndPoint--;
        }
      }

      // 큐브러브는 사전순으로 가장 뒤에 오게 해야 함.
      // 가장 느린 문자가 구사과의 가장 빠른 문자보다 작거나 같다면 큐브러브의 가장 빠른 문자를 놓는다.
      // 큐브러브의 가장 느린 문자가 구사과의 가장 빠른 문자보다 느리다면 느린 문자를 놓는다.
      else {
        // 구사과 문자열이 범위를 넘어갔을 경우가 있을 수 있음
        if(kooStartPoint == koosaga.size() || koosaga.get(kooStartPoint) < cubelover.get(cubeStartPoint)) {
          companyName[lPoint] = cubelover.get(cubeStartPoint);
          lPoint++;
          cubeStartPoint++;
        }
        else {
          companyName[rPoint] = cubelover.get(cubeEndPoint);
          rPoint--;
          cubeEndPoint--;
        }
      }
      time++;

    }

    for (char c : companyName) {
      sb.append(c);
    }

    bw.write(sb.toString());
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }

}
