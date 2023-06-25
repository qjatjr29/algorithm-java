package leetcode.greedy;

public class GasStation {
  public int canCompleteCircuit(int[] gas, int[] cost) {

    int sum = 0;
    int answer = 0;
    int usedGas = 0;

    for (int i = 0; i < gas.length; i++) {
      // 누적 가스 (현재 가스량 - 소모된 가스량)
      sum += gas[i] - cost[i];
      // 누적된 가스가 0보다 작다면 현재까지 소모된 가스량을 total에 저장
      // 그리고 다시 i + 1 부터 시작
      if (sum < 0) {
        usedGas += sum;
        sum = 0;
        answer = i + 1;
      }
    }
    // 마지막으로 누적된 가스량을 총합
    usedGas += sum;
    return usedGas < 0 ? -1 : answer;
  }
}
