package programmers.kakao;

import static java.util.Arrays.sort;

public class 실패율 {

  public static int[] solution(int N, int[] stages) {

    int total = stages.length;
    // 현재 스테이지 전 까지의 사람 수
    int preCount = 0;
    int[] answer = new int[N];
    // 각 스테이지 마다 사용자의 실패율과 스테이지 번호 저장.
    Stage[] list = new Stage[N];

    // 각 스테이지에 도전하고 있는 사용자 수
    int[] users = new int[N + 2];
    for(int i = 0; i < total; i++) {
      int a = stages[i];
      users[a]++;
    }

    for(int i = 1; i <= N; i++) {
      list[i - 1] = new Stage(i, users[i], total - preCount);
      preCount += users[i];
    }
    sort(list);

    for(int i = 0; i < N; i++) {
      System.out.println(list[i].rate);
      answer[i] = list[i].idx;
    }
    return answer;
  }

  public static void main(String[] args) {
    int[] stages = {3, 3, 3, 3};
    int[] solution = solution(5, stages);
    for (int i : solution) {
      System.out.print(i + " ");
    }
    System.out.println();
  }

  private static class Stage implements Comparable <Stage>{
    int idx;
    double rate;

    public Stage(int idx, int num, int tt) {
      this.idx = idx;
      if(tt == 0) this.rate = 0.0;
      else this.rate = (double) num / tt;
    }

    @Override
    public int compareTo(Stage o) {
      if(this.rate < o.rate) return 1;
      else if(this.rate == o.rate) {
        if(this.idx > o.idx) return 1;
        else return -1;
      }
      else return -1;
    }
  }

}
