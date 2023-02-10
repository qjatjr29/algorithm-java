package baekJoon.twoPointer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2461

public class 대표선수 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int answer = Integer.MAX_VALUE;

    int classCnt = Integer.parseInt(st.nextToken());
    int studentCnt = Integer.parseInt(st.nextToken());

    int[][] students = new int[classCnt][studentCnt];

    for(int i = 0; i < classCnt; i++) {
      st = new StringTokenizer(br.readLine());

      for(int j = 0; j < studentCnt; j++) {
        students[i][j] = Integer.parseInt(st.nextToken());
      }
      Arrays.sort(students[i]);
    }


    // 학생 능력치가 가장 낮은 학급 번호
    int minValueClassNumber = 0;

    // 각 학급의 학생 Pointer 배열
    int[] classPointerArray = new int[classCnt];

    while(true) {
      int maxStat = -1;
      int minStat = Integer.MAX_VALUE;

      for(int i = 0; i < classCnt; i++) {

        int stat = students[i][classPointerArray[i]];

        maxStat = Math.max(maxStat, stat);

        if(minStat > stat) {
          minStat = stat;
          minValueClassNumber = i;
        }
      }

      answer = Math.min(answer, maxStat - minStat);

      classPointerArray[minValueClassNumber]++;
      // 어느 학급의 Pointer가 학생의 수를 넘어가면 break;
      if(classPointerArray[minValueClassNumber] == studentCnt) break;

    }

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }

}
