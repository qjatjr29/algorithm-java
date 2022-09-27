package programmers.kakao;

import java.util.LinkedList;

public class 캐시 {

  public static int solution(int cacheSize, String[] cities) {
    int answer = 0;

    if(cacheSize == 0) return cities.length * 5;

    LinkedList<String> cache = new LinkedList<>();

    for(int i = 0; i < cities.length; i++) {
      String city = cities[i].toUpperCase();

      if(cache.remove(city)) {
        answer += 1;
        cache.add(city);
      }
      else {
        answer += 5;
        if(cache.size() >= cacheSize) cache.remove(0);
        cache.add(city);
      }
    }

    return answer;
  }



  public static void main(String[] args) {

//    String[] cities = {
//        "Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"
//    };

    String[] cities = {
        "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"
    };

//    String[] cities = {"Jeju", "Pangyo", "NewYork", "newyork"};
//    String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
//    String[] cities = {"a", "a", "a", "b", "b", "b", "c", "c", "c"};
//    String[] cities = {"A", "b", "a"};


    System.out.println(solution(3, cities));

  }

}
