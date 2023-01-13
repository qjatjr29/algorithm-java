package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IsSubsequence {

  private static int temp;

  public boolean isSubsequence(String s, String t) {

    Map<Character, List<Integer>> alphaMap = new HashMap<>();

    for(int i = 0; i < t.length(); i++) {
      char cChar = t.charAt(i);
      List<Integer> valueArray = alphaMap.getOrDefault(cChar, new ArrayList<>());
      valueArray.add(i);
      alphaMap.put(cChar, valueArray);
    }

    boolean isSub = true;
    int cIndex = 0;
    for(int i = 0; i < s.length(); i++) {
      temp = -1;
      char targetChar = s.charAt(i);
      List<Integer> valueArray = alphaMap.getOrDefault(targetChar, new ArrayList<>());

      hasChar(cIndex, 0, valueArray.size(), valueArray);
      //hasChar2(cIndex, valueArray);

      if(temp == -1) {
        isSub = false;
        break;
      }
      cIndex = temp;
    }

    return isSub;
  }

  private void hasChar(int target, int start, int end, List<Integer> valueArray) {
    if(start >= end) return;

    int mid = (start + end) / 2;
    int index = valueArray.get(mid);

    if(index > target) {
      temp = index + 1;
      hasChar(target, start, mid, valueArray);
    }
    else hasChar(target, mid + 1, end, valueArray);
  }

  private void hasChar2(int target, List<Integer> valueArray) {
    for (Integer value : valueArray) {
      if(value >= target) {
        temp = value + 1;
        break;
      }
    }
  }

}
