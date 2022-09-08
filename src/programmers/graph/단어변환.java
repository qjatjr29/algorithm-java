package programmers.graph;

import static java.lang.Math.min;

public class 단어변환 {

  private static boolean[] visited;
  private static int answer;

  public static int solution(String begin, String target, String[] words) {
    answer = 987654321;

    visited = new boolean[words.length];

    dfs(begin, target, words, 0);

    if(answer == 987654321) answer = 0;

    return answer;
  }

  private static void dfs(String cur, String target, String[] words, int cnt) {
    if(cur.equals(target)) {
      answer = min(answer, cnt);
      return;
    }

    for(int i = 0; i < words.length; i++) {
      if(visited[i]) continue;
      int count = 0;
      for(int j = 0; j < cur.length(); j++) {
        if(cur.charAt(j) == words[i].charAt(j)) count++;
      }
      if(count == cur.length() - 1) {
        visited[i] = true;
        dfs(words[i], target, words, cnt + 1);
        visited[i] = false;
      }
    }
    return;
  }

  public static void main(String[] args) {

    String begin = "hit";
    String target = "cog";
//    String[] words = {
//        "hot", "dot", "dog", "lot", "log", "cog"
//    };
    String[] words = {
        "hot", "dot", "dog", "lot", "log"
    };

    System.out.println(solution(begin, target, words));

  }

}
