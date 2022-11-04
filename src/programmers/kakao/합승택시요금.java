package programmers.kakao;

import static java.lang.Math.min;

public class 합승택시요금 {

  private static int[][] dp;

  public static int solution(int n, int s, int a, int b, int[][] fares) {
    int answer = 0;

    dp = new int[n + 1][n + 1];

    for(int i = 1; i <= n; i++) {
      for(int j = 1; j <=n; j++) {
        if(i == j) continue;
        dp[i][j] = 20000001;
      }
    }

    for(int i = 0; i < fares.length; i++) {
      for(int j = 0; j < fares[i].length; j++) {
        int node1 = fares[i][0];
        int node2 = fares[i][1];
        int cost = fares[i][2];
        dp[node1][node2] = cost;
        dp[node2][node1] = cost;
      }
    }
    floyd(n);

    // 따로 가는 경우
    answer = dp[s][a] + dp[s][b];

    // 함께 같이 가다가 따로 가는 경우
    for(int i = 1; i <= n; i++) {
      if(i == s) continue;
      answer = min(answer, dp[s][i] + dp[i][a] + dp[i][b]);
    }

    return answer;
  }

  private static void floyd(int size) {
    for(int i = 1; i <= size; i++) {
      for(int j = 1; j <= size; j++) {
        for(int z = 1; z <= size; z++) {
          if(j == z) continue;
          dp[j][z] = min(dp[j][z], dp[j][i] + dp[i][z]);
        }
      }
    }
  }

  public static void main(String[] args) {

//    int[][] fares = {
//        {4, 1, 10},
//        {3, 5, 24},
//        {5, 6, 2},
//        {3, 1, 41},
//        {5, 1, 24},
//        {4, 6, 50},
//        {2, 4, 66},
//        {2, 3, 22},
//        {1, 6, 25}
//    };

    int[][] fares = {
        {5, 7, 9},
        {4, 6, 4},
        {3, 6, 1},
        {3, 2, 3},
        {2, 1, 6}
    };

    System.out.println(solution(7, 3, 4, 1, fares));


  }

}
