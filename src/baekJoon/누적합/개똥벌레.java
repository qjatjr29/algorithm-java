package baekJoon.누적합;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 개똥벌레 {

  private static int[] top;
  private static int[] bottom;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int length = Integer.parseInt(st.nextToken());
    int height = Integer.parseInt(st.nextToken());
    top = new int[height + 1];
    bottom = new int[height + 1];

    for(int i = 0; i < length; i++) {
      st = new StringTokenizer(br.readLine());
      int h = Integer.parseInt(st.nextToken());
      if(i % 2 == 0) bottom[h]++;
      else top[h]++;
    }

    for(int i = 1; i <= height; i++) {
      bottom[i] += bottom[i - 1];
      top[i] += top[i - 1];
    }

    int section = 0;
    int obstacle = length;
    // i 번째 구간에서 장애물의 개수를 구한 후 최솟값 갱신
    for(int i = 1; i <= height; i++) {

      // 해당 구간에서 장애물의 개수
      int ob = (bottom[height] - bottom[i - 1]) + (top[height] - top[height - i]);
      if(obstacle > ob) {
        obstacle = ob;
        section = 1;
      }
      else if(obstacle == ob) section++;
    }

    bw.write(String.valueOf(obstacle) + " " + String.valueOf(section));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }
}
