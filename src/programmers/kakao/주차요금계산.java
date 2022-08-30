package programmers.kakao;

import static java.util.Collections.sort;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 주차요금계산 {

  private static Map<Integer, State> cars;
  private static int[] feeInfo;
  private static List<Integer> carNumbers;

  public static int[] solution(int[] fees, String[] records) {
    int[] answer = {};

    cars = new HashMap<>();
    feeInfo = fees;
    carNumbers = new ArrayList<>();

    for(int i = 0; i < records.length; i++) {
      sol(records[i]);
    }

    sort(carNumbers);
    answer = new int[cars.size()];

    for(int i = 0; i < carNumbers.size(); i++) {
      Integer carNum = carNumbers.get(i);
      State curCar = cars.get(carNum);
      int addTime = 0;
      if(curCar.state.equals("IN")) {
        addTime = calculateTime(LocalTime.of(23, 59), curCar.time);
      }
      int totalTime = curCar.sumTime + addTime;

      answer[i] = calculateFee(totalTime);
    }

    return answer;
  }

  private static void sol(String record) {

    String[] info = record.split(" ");
    String timeStr = info[0];
    Integer number = Integer.valueOf(info[1]);
    String state = info[2];

    LocalTime time = LocalTime.parse(timeStr);

    if(cars.containsKey(number)) {
      State car = cars.get(number);
      // 나가는 경우 - 요금 추가
      if(state.equals("OUT")) {
        int timeGap = calculateTime(time, car.time);
        State newState = new State(time, car.sumTime + timeGap, "OUT");
        cars.replace(number, newState);
      }
      // 들어가는 경우
      else {
        State newState = new State(time, car.sumTime,  "IN");
        cars.replace(number, newState);
      }
    }
    // 처음 들어가는 경우
    else {
      State newState = new State(time, 0, "IN");
      cars.put(number, newState);
      carNumbers.add(number);
    }
  }

  private static int calculateFee(int time) {

    int standardTime = feeInfo[0];
    int standardFee = feeInfo[1];
    int periodTime = feeInfo[2];
    int periodFee = feeInfo[3];

    if(time > standardTime) {
      int rtn = standardFee;

      int mod = (time - standardTime) / periodTime;
      if((time - standardTime) % periodTime != 0) mod += 1;

      rtn += (mod * periodFee);
      return rtn;

    } else {
      return standardFee;
    }
  }

  private static int calculateTime(LocalTime time, LocalTime cmp) {
    int cHour = time.getHour();
    int cMinute = time.getMinute();

    int cmpHour = cmp.getHour();
    int cmpMinute = cmp.getMinute();

    return (cHour - cmpHour) * 60 + (cMinute - cmpMinute);
  }

  private static class State {
    LocalTime time;
    int sumTime;
    String state;

    public State(LocalTime time, int sumTime, String state) {
      this.time = time;
      this.sumTime = sumTime;
      this.state = state;
    }
  }


  public static void main(String[] args) {

//    int[] fees = {180, 5000, 10, 600};
//    String[] records = {"05:34 5961 IN",
//        "06:00 0000 IN",
//        "06:34 0000 OUT",
//        "07:59 5961 IN",
//        "07:59 0148 IN",
//        "18:59 0000 IN",
//        "19:09 0148 OUT",
//        "22:59 5961 IN",
//        "23:00 5961 OUT"
//    };

    int[] fees = {120, 0, 60, 591};
    String[] records = {"16:00 3961 IN",
        "16:00 0202 IN",
        "18:00 3961 OUT",
        "18:00 0202 OUT",
        "23:58 3961 IN"
    };

    int[] answer = solution(fees, records);

    for (int result : answer) {
      System.out.println(result);
    }

  }

}
