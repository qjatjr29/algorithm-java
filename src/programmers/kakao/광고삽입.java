package programmers.kakao;

import java.text.ParseException;

public class 광고삽입 {

  private static int[] sum;

  public static String solution(String play_time, String adv_time, String[] logs) throws ParseException {
    String answer = "";

    int playTime = timeToInt(play_time);
    int advTime = timeToInt(adv_time);

    sum = new int[playTime + 1];

    for(int i = 0; i < logs.length; i++) {
      String[] split = logs[i].split("-");

      String start = split[0];
      String end = split[1];

      int startTime = timeToInt(start);
      int endTime = timeToInt(end);

      for(int j = startTime; j < endTime; j++) {
        sum[j]++;
      }
    }

    long max, advSum;
    advSum = 0;
    for(int i = 0; i < advTime; i++) {
      advSum += sum[i];
    }
    max = advSum;

    int startIndex = 1;
    int lastIndex = advTime;
    int result = 0;


    while(true) {

      if(lastIndex >= playTime) break;

      advSum -= sum[startIndex - 1];
      advSum += sum[lastIndex];

      if(max < advSum) {
        max = advSum;
        result = startIndex;
      }
      startIndex++;
      lastIndex++;
    }

    answer = intToTime(result);
    return answer;
  }

  private static int timeToInt (String str) {

    int time = 0;

    String[] timeList = str.split(":");

    int hour = Integer.parseInt(timeList[0]);
    int minute = Integer.parseInt(timeList[1]);
    int second = Integer.parseInt(timeList[2]);

    time += (hour * 3600) + (minute * 60) + second;

    return time;
  }

  private static String intToTime (int number) {

    StringBuilder sb = new StringBuilder();

    if(number / 3600 <= 9) {
      sb.append("0");
    }
    int hour = number / 3600;
    number = number % 3600;
    sb.append(hour);
    sb.append(":");

    if(number / 60 < 10) sb.append("0");
    int minute = number / 60;
    number = number % 60;
    sb.append(minute);
    sb.append(":");

    if(number % 60 < 10) sb.append("0");
    int second = number % 60;
    sb.append(second);

    return sb.toString();
  }


  public static void main(String[] args) throws ParseException {

//    String playTime = "02:03:55";
//    String advTime = "00:14:15";
//
//    String[] logs = {
//        "01:20:15-01:45:14",
//        "00:40:31-01:00:00",
//        "00:25:50-00:48:29",
//        "01:30:59-01:53:29",
//        "01:37:44-02:02:30"
//    };


//    String playTime = "99:59:59";
//    String advTime = 	"25:00:00";
//
//    String[] logs = {
//        "69:59:59-89:59:59",
//        "01:00:00-21:00:00",
//        "79:59:59-99:59:59",
//        "11:00:00-31:00:00"
//    };

    String playTime = "50:00:00";
    String advTime = 	"50:00:00";

    String[] logs = {
        "15:36:51-38:21:49",
        "10:14:18-15:36:51",
        "38:21:49-42:51:45"
    };

    System.out.println(solution(playTime, advTime, logs));
  }

}
