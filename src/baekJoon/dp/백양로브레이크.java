package baekJoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 백양로브레이크 {

  private static final int MAX_BUILDING_COUNT = 250;
  private static final int ONE_WAY = 0;
  private static final int TWO_WAY = 1;

  private static Road[] roadInfos;
  private static int[][] dp;

  public static void main(String[] args) throws IOException {

    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int buildings = Integer.parseInt(st.nextToken());
    int roads = Integer.parseInt(st.nextToken());

    roadInfos = new Road[roads];

    for(int i = 0; i < roads; i++) {
      st = new StringTokenizer(br.readLine());

      int building1 = Integer.parseInt(st.nextToken());
      int building2 = Integer.parseInt(st.nextToken());
      int type = Integer.parseInt(st.nextToken());
      roadInfos[i] = new Road(building1, building2, type);
    }

    st = new StringTokenizer(br.readLine());
    int questions = Integer.parseInt(st.nextToken());

    setDp(buildings);
    floyd();

    for(int i = 0; i < questions; i++) {
      st = new StringTokenizer(br.readLine());
      int building1 = Integer.parseInt(st.nextToken());
      int building2 = Integer.parseInt(st.nextToken());

      bw.write(String.valueOf(dp[building1][building2]));
      bw.newLine();
    }
    bw.flush();
    bw.close();
    br.close();

  }

  private static void setDp(int buildingCount) {
    dp = new int[buildingCount + 1][buildingCount + 1];

    for(int i = 1; i <= buildingCount; i++) {
      for(int j = 1; j <= buildingCount; j++) {
        if(i == j) continue;
        dp[i][j] = MAX_BUILDING_COUNT;
      }
    }

    for(Road road : roadInfos) {
      int b1 = road.building1;
      int b2 = road.building2;
      int type = road.type;
      dp[b1][b2] = 0;
      if(type == ONE_WAY) {
        dp[b2][b1] = 1;
      }
      else {
        dp[b2][b1] = 0;
      }
    }
  }

  private static void floyd() {
    int size = dp.length;

    for(int i = 1; i < size; i++) {
      for(int j = 1; j < size; j++) {
        for(int z = 1; z < size; z++) {
          if(j == z) continue;
          dp[j][z] = Math.min(dp[j][z], dp[j][i] + dp[i][z]);
        }
      }
    }
  }

  private static class Road {
    int building1, building2, type;

    public Road(int building1, int building2, int type) {
      this.building1 = building1;
      this.building2 = building2;
      this.type = type;
    }
  }

}
