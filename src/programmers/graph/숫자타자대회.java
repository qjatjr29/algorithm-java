package programmers.graph;

import java.util.*;

public class 숫자타자대회 {

  private int[][] numberPad = {
      {1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {-1, 0, -1}
  };
  private static int[][] cost;
  private static int answer;
  private static int[][][] dp;

  public int solution(String numbers) {
    answer = 987654321;
    cost = new int[10][10];
    int len = numbers.length();
    dp = new int[len + 1][10][10];
    int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    int[] dy = {0, 0, -1 ,1, -1, 1, -1, 1};

    for(int i = 0; i <= len; i++) {
      for(int j = 0; j < 10; j++) {
        Arrays.fill(dp[i][j], -1);
      }
    }

    // 자기 자신을 누르는 경우는 가중치가 1
    for(int i = 0; i < 10; i++) {
      for(int j = 0; j < 10; j++) {
        if(i == j) cost[i][j] = 1;
        else cost[i][j] = 987654321;
      }
    }

    // i -> j 가는 최소 가중치 구해놓기.
    for(int i = 0; i < numberPad.length; i++) {
      for(int j = 0; j < numberPad[i].length; j++) {
        int startNumber = numberPad[i][j];
        if(startNumber == -1) continue;

        // 인접한 경우 가중치 2
        for(int z = 0; z < 4; z++) {
          int nx = i + dx[z];
          int ny = j + dy[z];

          if(nx < 0 || nx >= numberPad.length || ny < 0 || ny >= numberPad[i].length) continue;

          int targetNumber = numberPad[nx][ny];

          if(targetNumber == -1) continue;
          cost[startNumber][targetNumber] = 2;
        }
        // 대각선의 경우 가중치 3
        for(int z = 4; z < 8; z++) {
          int nx = i + dx[z];
          int ny = j + dy[z];

          if(nx < 0 || nx >= numberPad.length || ny < 0 || ny >= numberPad[i].length) continue;
          int targetNumber = numberPad[nx][ny];
          if(targetNumber == -1) continue;
          cost[startNumber][targetNumber] = 3;
        }
      }
    }

    // 각 숫자간 최소 가중치를 구해놓음.(플로이드와샬)
    for(int i = 0; i < cost.length; i++) {
      for(int j = 0; j < cost.length; j++) {
        for(int z = 0; z < cost.length; z++) {
          cost[j][z] = Math.min(cost[j][z], cost[j][i] + cost[i][z]);
        }
      }
    }

    answer = dfs(0, numbers, 4, 6);
    return answer;
  }

  private int dfs(int index, String number, int left, int right) {

    // 마지막 번호까지 모두 확인한 경우
    if(index == number.length()) {
      return 0;
    }
    // 이미 해당 경우를 확인한 경우.
    if(dp[index][left][right] != -1) return dp[index][left][right];

    int nextNumber = number.charAt(index) - '0';

    // 해당 숫자로 가는데 최소한의 시간
    int rtn = 987654321;

    // 각 현재 손가락의 위치에 따른 걸리는 시간
    int leftCost = cost[left][nextNumber];
    int rightCost = cost[right][nextNumber];

    // 왼손가락 사용
    if(nextNumber != right) rtn = Math.min(dfs(index + 1, number, nextNumber, right) + leftCost, rtn);

    // 오른쪽 손가락 사용
    if(nextNumber != left) rtn = Math.min(dfs(index + 1, number, left, nextNumber) + rightCost, rtn);

    return dp[index][left][right] = rtn;
  }


}
