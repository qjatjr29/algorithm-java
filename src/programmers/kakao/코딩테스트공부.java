package programmers.kakao;


public class 코딩테스트공부 {

  private static int[][] cost;
  private static int maxAlp, maxCop;

  public int solution(int alp, int cop, int[][] problems) {
    int answer = 0;

    maxAlp = -1;
    maxCop = -1;

    for(int[] problem : problems) {
      maxAlp = Math.max(maxAlp, problem[0]);
      maxCop = Math.max(maxCop, problem[1]);
    }

    cost = new int[maxAlp + 1][maxCop + 1];

    if(alp >= maxAlp) alp = maxAlp;
    if(cop >= maxCop) cop = maxCop;


    for(int i = alp; i <= maxAlp; i++) {
      for(int j = cop; j <= maxCop; j++) {
        cost[i][j] = Integer.MAX_VALUE;
      }
    }

    cost[alp][cop] = 0;

    for(int i = alp; i <= maxAlp; i++) {
      for(int j = cop; j <= maxCop; j++) {
        // 알고력, 코딩력 +1 훈련
        if(i != maxAlp) cost[i + 1][j] = Math.min(cost[i + 1][j], cost[i][j] + 1);
        if(j != maxCop) cost[i][j + 1] = Math.min(cost[i][j + 1], cost[i][j] + 1);

        for(int[] problem : problems) {
          solveProblem(i, j, problem);
        }
      }
    }
    answer = cost[maxAlp][maxCop];
    return answer;
  }

  private void solveProblem(int alp, int cop, int[] problem) {

    int requiredAlp = problem[0];
    int requiredCop = problem[1];

    // 문제를 풀 수 있는 경우
    if(alp >= requiredAlp && cop >= requiredCop) {

      int nextAlp = alp + problem[2];
      int nextCop = cop + problem[3];
      int nextCost = cost[alp][cop] + problem[4];

      // 모든 풀 수 있는 경우
      if(nextAlp >= maxAlp && nextCop >= maxCop) cost[maxAlp][maxCop] = Math.min(cost[maxAlp][maxCop], nextCost);

      else if(nextAlp >= maxAlp) cost[maxAlp][nextCop] = Math.min(cost[maxAlp][nextCop], nextCost);
      else if(nextCop >= maxCop) cost[nextAlp][maxCop] = Math.min(cost[nextAlp][maxCop], nextCost);

      else cost[nextAlp][nextCop] = Math.min(cost[nextAlp][nextCop], nextCost);
    }
  }

}
