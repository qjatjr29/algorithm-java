package baekJoon.Implementation;

import static java.lang.Math.abs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 참외밭 {

  private static int K;
  private static int[] numbers;

  public static void main(String[] args) throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    K = Integer.parseInt(st.nextToken());
    numbers = new int[6];
    int answer = 0;

    Side row = new Side(0, 987654321, 0);
    Side col = new Side(0, 987654321, 0);

    // 동 1 서 2 남 3 북 4
    for(int i = 0; i < 6; i++) {
      st = new StringTokenizer(br.readLine());
      int direction = Integer.parseInt(st.nextToken());
      int length = Integer.parseInt(st.nextToken());
      numbers[i] = length;
      if((direction == 1 || direction == 2) && row.max < length) {
        row.max = length;
        row.maxIndex = i;
      }
      if((direction == 3 || direction == 4) && col.max < length) {
        col.max = length;
        col.maxIndex = i;
      }
    }

    int right, left;
    if(row.maxIndex + 1 == 6) right = 0;
    else right = row.maxIndex + 1;

    if(row.maxIndex - 1 == -1) left = 5;
    else left = row.maxIndex - 1;

    col.min = abs(numbers[right] - numbers[left]);

    if(col.maxIndex + 1 == 6) right = 0;
    else right = col.maxIndex + 1;

    if(col.maxIndex - 1 == -1) left = 5;
    else left = col.maxIndex - 1;

    row.min = abs(numbers[right] - numbers[left]);

    answer = (row.max * col.max) - (row.min * col.min);

    answer *= K;

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }

  private static class Side {
    int max, min, maxIndex;

    public Side(int max, int min, int maxIndex) {
      this.max = max;
      this.min = min;
      this.maxIndex = maxIndex;
    }
  }

}
