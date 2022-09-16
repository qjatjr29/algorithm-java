package programmers.kakao;


import static java.util.Collections.sort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 불량사용자 {

  private static List<Integer>[] bannedList;
  private static int answer;
  private static boolean[] checked;
  private static Set<List<Integer>> set;

  public static int solution(String[] user_id, String[] banned_id) {
    answer = 0;

    bannedList = new List[banned_id.length];
    set = new HashSet<>();

    for(int i = 0; i < bannedList.length; i++) {
      bannedList[i] = new ArrayList<>();
    }

    checked = new boolean[user_id.length];

    for(int i = 0; i < banned_id.length; i++) {
      String banId = banned_id[i];
      int size = banned_id[i].length();

      for(int j = 0; j < user_id.length; j++) {
        String userId = user_id[j];
        if(userId.length() != size) continue;

        boolean chk = true;
        for(int z = 0; z < banId.length(); z++) {
          if(banId.charAt(z) == '*') continue;
          if(banId.charAt(z) != userId.charAt(z)) {
            chk = false;
            break;
          }
        }
        if(chk) bannedList[i].add(j);
      }
    }
    dfs(0, new ArrayList<>());

    answer = set.size();
    return answer;
  }

  private static void dfs(int index, List<Integer> list) {
    if(index == bannedList.length) {
      sort(list);
      set.add(list);
      return;
    }

    int size = bannedList[index].size();

    for(int i = 0; i < size; i++) {
      Integer next = bannedList[index].get(i);
      if(checked[next]) continue;
      else {
        checked[next] = true;
        list.add(next);
        dfs(index + 1, list);
        list.remove(next);
        checked[next] = false;
      }
    }
  }

  public static void main(String[] args)  {

//    String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
//    String[] banned_id = {"fr*d*", "abc1**"};

//    String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
//    String[] banned_id = {"*rodo", "*rodo", "******"};

    String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
    String[] banned_id = {"fr*d*", "*rodo", "******", "******"};

    System.out.println(solution(user_id, banned_id));
  }

}
