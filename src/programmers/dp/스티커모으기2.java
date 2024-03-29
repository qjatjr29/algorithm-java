package programmers.dp;

public class 스티커모으기2 {
  public int solution(int sticker[]) {
    int answer = 0;

    int size = sticker.length;

    if(size == 1) return sticker[0];

    // 첫 번째 스티커를 선택하는지 안하는지에 따라 달라진다.
    int[] dp1 = new int[size];
    int[] dp2 = new int[size];

    // 첫번째 스티커를 선택
    dp1[0] = sticker[0];
    dp1[1] = sticker[0];
    // 첫번째 스티커를 선택하지 않고 두번쨰 스티커를 선택
    dp2[1] = sticker[1];

    // 첫 번째 스티커를 선택한 경우 => 마지막 스티커 선택 못함.
    for(int i = 2; i < size - 1; i++) {
      dp1[i] = Math.max(dp1[i - 2] + sticker[i], dp1[i - 1]);
    }

    // 첫 번째 스티커를 선택하지 않은 경우 => 마지막 스티커 선택 가능.
    for(int i = 2; i < size; i++) {
      dp2[i] = Math.max(dp2[i - 2] + sticker[i], dp2[i - 1]);
    }

    answer = Math.max(dp1[size - 2], dp2[size - 1]);

    return answer;
  }
}
