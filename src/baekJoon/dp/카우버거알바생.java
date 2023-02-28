package baekJoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 카우버거알바생 {

  private static int orders;
  private static int[][] order;
  private static int[][][] dp;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    orders = Integer.parseInt(st.nextToken());
    int remainBurger = Integer.parseInt(st.nextToken());
    int remainFries = Integer.parseInt(st.nextToken());

    // 주문 정보
    order = new int[orders + 1][2];
    dp = new int[orders + 1][301][301];

    for(int i = 0; i < orders; i++) {
      st = new StringTokenizer(br.readLine());
      int burger = Integer.parseInt(st.nextToken());
      int fries = Integer.parseInt(st.nextToken());

      order[i + 1][0] = burger;
      order[i + 1][1] = fries;
    }
    int answer = 0;
    for(int i = 1; i <= orders; i++) {
      for(int j = remainBurger; j >= 0; j--) {
        for(int z = remainFries; z >= 0; z--) {

          // i번째 주문에 (필요한 버거, 감자튀김) 개수가 (남은 버거, 감자튀김)보다 적을 경우 => 오더 가능!
          // 오더를 해서 나온 count와 현재 count(주문을 하지 않고 넘어갔을 때 count)를 비교해 최대값을 찾는다.
          // 이번 주문을 받았으니 +1 을 해준다.
          // 주문을 하지않고 넘어간 경우와 비교해서 최대값을 구한다.
          if(j - order[i][0] >= 0 && z - order[i][1] >= 0) {
            dp[i][j][z] = Math.max(dp[i - 1][j][z], dp[i - 1][j - order[i][0]][z - order[i][1]] + 1);
          }
          else dp[i][j][z] = dp[i - 1][j][z];
          answer = Math.max(answer, dp[i][j][z]);
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
