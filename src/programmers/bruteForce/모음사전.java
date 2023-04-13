package programmers.bruteForce;
import java.util.*;

public class 모음사전 {

  private static List<String> words;
  private static char[] alpha = {'A', 'E', 'I', 'O', 'U'};

  public int solution(String word) {
    int answer = 0;

    words = new ArrayList<>();

    StringBuilder sb = new StringBuilder();
    for(int i = 1; i <= 5; i++) {
      dfs(sb, i);
    }

    Collections.sort(words);
    answer = words.indexOf(word) + 1;

    return answer;
  }

  private void dfs(StringBuilder str, int size) {
    if(str.length() == size) {
      words.add(str.toString());
      return;
    }

    for(int i = 0; i < alpha.length; i++) {
      str.append(alpha[i]);
      dfs(str, size);
      str.deleteCharAt(str.length() - 1);
    }
  }

}
