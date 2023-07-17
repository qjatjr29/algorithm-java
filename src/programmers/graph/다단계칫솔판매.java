package programmers.graph;

import java.util.*;

public class 다단계칫솔판매 {

  private static Map<String, Salesman> salesmanMap;

  public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {

    salesmanMap = new HashMap<>();
    int size = enroll.length;
    List<Integer> answer = new ArrayList<>();

    salesmanMap.put("-", new Salesman("-"));
    for(int i = 0; i < size; i++) {
      String name = enroll[i];
      salesmanMap.put(name, new Salesman(name));
    }

    for(int i = 0; i < size; i++) {
      String recommenderName = referral[i];
      salesmanMap.get(enroll[i]).recommender = salesmanMap.get(recommenderName);
    }

    for(int i = 0; i < seller.length; i++) {
      String sellerName = seller[i];
      int cost = amount[i] * 100;
      Salesman salesman = salesmanMap.get(sellerName);
      salesman.calculateProfit(cost);
    }


    for(String name : enroll) {
      answer.add(salesmanMap.get(name).profit);
    }

    return answer.stream().mapToInt(Integer::intValue).toArray();
  }

  private class Salesman {
    String name;
    Salesman recommender;
    int profit;

    Salesman(String name) {
      this.name = name;
      this.recommender = null;
      this.profit = 0;
    }

    public void calculateProfit(int cost) {
      int profitToRecommender = cost / 10;

      if(profitToRecommender != 0 && this.recommender != null) {
        this.profit += cost - profitToRecommender;
        this.recommender.calculateProfit(profitToRecommender);
      }
      else this.profit += cost;
    }
  }


}
