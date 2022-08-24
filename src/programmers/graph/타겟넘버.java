package programmers.graph;

public class 타겟넘버 {

  private static int answer;

  public static int solution(int[] numbers, int target) {
    answer = 0;
    dfs(0, numbers, 0, target);
    return answer;
  }

  public static void dfs(int idx, int[] numbers, int sum, int target) {
    if(idx == numbers.length && sum == target) {
      answer++;
      return;
    }
    if(idx >= numbers.length) return;

    dfs(idx + 1, numbers, sum + numbers[idx], target);
    dfs(idx + 1, numbers, sum - numbers[idx], target);
  }


  public static void main(String[] args) {

//    int[] numbers = {1, 1, 1, 1, 1};
//    int target = 3;
    int[] numbers = {4, 1, 2, 1};
    int target = 4;

    System.out.println(solution(numbers, target));

  }

}
