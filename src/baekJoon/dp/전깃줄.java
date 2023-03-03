package baekJoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2565
public class 전깃줄 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int size = Integer.parseInt(st.nextToken());

    // 전깃줄 정보를 저장하는 배열
    Line[] lines = new Line[size];

    // i번째 전깃줄부터 마지막 전깃줄까지 교차하지 않는 전깃줄의 최대 개수
    int[] maxCount = new int[501];
    int answer = 0;

    for(int i = 0; i < size; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());

      lines[i] = new Line(start, end);
    }

    Arrays.sort(lines, new Comparator<Line>() {
      @Override
      public int compare(Line o1, Line o2) {
        if(o1.start < o2.start) return -1;
        else if(o1.start == o2.start) {
          if(o1.end < o2.end) return -1;
        }
        return 1;
      }
    });

    // dp 배열 초기화
    for(int i = 0; i < 501; i++) maxCount[i] = 1;

    // 전봇대 위치 최대 값
    int maxNumber = lines[size - 1].end;

    // O(n^2)
    for(int i = size - 2; i >= 0; i--) {

      int end = lines[i].end;

      // 현재 나온 전봇대 최대 값까지 반복문을 돌면서
      // 교차하지 않는 전깃줄의 최대 개수를 구한다.
      for(int j = end + 1; j <= maxNumber; j++) {
        maxCount[end] = Math.max(maxCount[end], maxCount[j] + 1);
      }

      answer = Math.max(answer, maxCount[end]);
      maxNumber = Math.max(end, maxNumber);
    }

    // 전체 전깃줄에서 - 교차하지 않는 최대 전깃줄의 개수를 뺀 값이 정답.
    bw.write(String.valueOf(size - answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }

  private static class Line {
    int start, end;

    public Line(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }

}
