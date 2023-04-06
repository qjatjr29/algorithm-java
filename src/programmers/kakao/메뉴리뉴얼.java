package programmers.kakao;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/72411
public class 메뉴리뉴얼 {

  private static String[] orderArray;
  private static List<String> courseList;
  private static Set<String>[][] candidateSet;

  public String[] solution(String[] orders, int[] course) {

    orderArray = new String[orders.length];
    courseList = new ArrayList<>();
    candidateSet = new Set[orders.length][11];

    for(int i = 0; i < orders.length; i++) {

      for(int j = 0; j < 11; j++) {
        candidateSet[i][j] = new HashSet<>();
      }
      String order = orders[i];
      char[] orderCharArr = order.toCharArray();
      Arrays.sort(orderCharArr);
      orderArray[i] =  new String(orderCharArr);
    }

    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < course.length; i++) {
      int courseSize = course[i];
      // 각 order 배열마다 사이즈에 맞는 부분 배열 저장하기
      for(int orderIndex = 0; orderIndex < orderArray.length; orderIndex++) {
        String order = orderArray[orderIndex];
        sb.setLength(0);
        dfs(0, courseSize, sb, order, orderIndex);
      }
    }

    for(int i = 0; i < course.length; i++) {

      // 구하고자 하는 course 크기
      int courseSize = course[i];
      int menuCount = 0;
      Set<String> courseSet = new HashSet<>();
      int cnt = 0;
      List<String> temp = new ArrayList<>();
      // 각 주문마다 size크기의 부분배열들을 비교해야함.
      for(int orderIndex = 0; orderIndex < orderArray.length; orderIndex++) {

        Set<String> subOrder = candidateSet[orderIndex][courseSize];

        // 해당 부분배열( ex: AB ) 가 다른 주문들에게도 있는지 확인.
        for(String str : subOrder) {
          cnt = 0;
          for(int oIndex = 0; oIndex < orderArray.length; oIndex++) {
            if(candidateSet[oIndex][courseSize].contains(str)) cnt++;
          }
          // System.out.println(str + " " + cnt + " " + menuCount);
          if(cnt >= 2 && cnt > menuCount) {
            menuCount = cnt;
            temp.clear();
            temp.add(str);
          }
          else if(cnt >= 2 && cnt == menuCount) {
            temp.add(str);
          }
        }
      }
      for(String t : temp) {
        courseSet.add(t);
      }
      for(String result : courseSet) {
        courseList.add(result);
      }
    }

    Collections.sort(courseList);

    return courseList.stream().toArray(String[]::new);
  }

  private void dfs(int index, int size, StringBuilder sb, String order, int orderIndex) {

    // 만족한 경우
    if(sb.length() == size) {

      candidateSet[orderIndex][size].add(sb.toString());
      return;
    }

    for(int i = index; i < order.length(); i++) {
      sb.append(order.charAt(i));
      dfs(i + 1, size, sb, order, orderIndex);
      sb.deleteCharAt(sb.length() - 1);
    }
  }

}
