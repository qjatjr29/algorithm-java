package programmers.kakao;

public class 양궁대회 {
    private static int result = -1000;
    private static int[] lion;
    private static int[] answer = { -1 };

    public static int[] solution(int n, int[] info) {
      lion = new int[11];
      sol(n, 1, info);
      return answer;
    }

    public static void sol(int n, int cnt, int[] info) {
      if(cnt == n + 1) {
        int enemy = 0;
        int mine = 0;

        for(int i = 0; i <= 10; i++) {
          if(info[i] != 0 || lion[i] != 0) {
            if(info[i] < lion[i]) {
              mine += (10 - i);
            } else {
              enemy += (10 - i);
            }
          }
        }

        if(mine > enemy) {
          if(mine - enemy >= result) {
            answer = lion.clone();
            result = mine - enemy;
          }
        }
        return;
      }

      for(int i = 0; i <= 10 && lion[i] <= info[i]; i++) {
        lion[i]++;
        sol(n, cnt + 1, info);
        lion[i]--;
      }

    }





  public static void main(String[] args) {
      int [] info = {0,0,1,2,0,1,1,1,1,1,1};
    int[] ans = solution(9, info);
    for (int an : ans) {
      System.out.print(an + " ");
    }
    System.out.println();
  }

}
