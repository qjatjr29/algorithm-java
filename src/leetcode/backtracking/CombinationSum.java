package leetcode.backtracking;

import java.util.*;

public class CombinationSum {

  private static Set<String> listSet;

  public List<List<Integer>> combinationSum(int[] candidates, int target) {

    listSet = new HashSet<>();
    List<List<Integer>> answer = new ArrayList<>();
    dfs(0, target, new ArrayList<>(), candidates);

    for(String elements : listSet) {

      String[] e = elements.split(",");
      List<Integer> list = new ArrayList<>();
      for(int i = 0; i < e.length; i++) {
        list.add(Integer.parseInt(e[i]));
      }

      answer.add(list);
    }
    return answer;
  }

  private void dfs(int number, int target, List<Integer> list, int[] candidates) {
    if(number > target) return;
    if(number == target) {
      List<Integer> copyList = new ArrayList<>();
      copyList.addAll(list);
      Collections.sort(copyList);

      StringBuilder sb = new StringBuilder();
      for(int e : copyList) {
        sb.append(e);
        sb.append(",");
      }
      listSet.add(sb.toString());
      return;
    }

    for(int i = 0; i < candidates.length; i++) {
      int candidate = candidates[i];
      if(number + candidate > target) continue;
      list.add(candidate);
      dfs(number + candidate, target, list, candidates);
      list.remove(list.size() - 1);
    }
  }

}
