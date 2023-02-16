package programmers.kakao;

// https://school.programmers.co.kr/learn/courses/30/lessons/150368

public class 이모티콘할인행사 {

  private static int emoticonPlus;
  private static int emoticonCost;

  public int[] solution(int[][] users, int[] emoticons) {
    int[] discountRate = new int[emoticons.length];

    numberOfCase(discountRate, 0, emoticons, users);

    int[] answer = {emoticonPlus, emoticonCost};

    return answer;
  }

  private void numberOfCase(int[] discountRate, int count, int[] emoticons, int[][] users) {

    if(count == discountRate.length) {
      buyEmoticon(discountRate, emoticons, users);
      return;
    }

    discountRate[count] = 10;
    numberOfCase(discountRate, count + 1, emoticons, users);
    discountRate[count] = 20;
    numberOfCase(discountRate, count + 1, emoticons, users);
    discountRate[count] = 30;
    numberOfCase(discountRate, count + 1, emoticons, users);
    discountRate[count] = 40;
    numberOfCase(discountRate, count + 1, emoticons, users);
  }

  private void buyEmoticon(int[] discountRate, int[] emoticons, int[][] users) {

    int buyEmoticonPlus = 0;
    int buyEmoticonCost = 0;

    for(int[] user : users) {

      int requiredRate = user[0];
      int targetCost = user[1];

      int cost = 0;
      for (int i = 0; i < discountRate.length; i++) {
        // 기준 비율을 넘지 못하면 사지 않는다.
        if (discountRate[i] < requiredRate) continue;
        // 기준 비율을 넘었다면 할인을 적용해 구매한다.
        cost += (emoticons[i] * (100 - discountRate[i]) / 100);
      }
      // 기준 가격을 넘었다면 서비스에 가입한다.
      if (cost >= targetCost) buyEmoticonPlus++;
      else buyEmoticonCost += cost;
    }

    if(emoticonPlus < buyEmoticonPlus) {
      emoticonPlus = buyEmoticonPlus;
      emoticonCost = buyEmoticonCost;
    }
    else if(emoticonPlus == buyEmoticonPlus) {
      emoticonCost = Math.max(emoticonCost, buyEmoticonCost);
    }
  }

}
