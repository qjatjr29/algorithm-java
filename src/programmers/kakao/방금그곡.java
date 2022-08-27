package programmers.kakao;

import java.time.LocalTime;

public class 방금그곡 {

  public static String solution(String m, String[] musicinfos) {
    String answer = "";
    int playTime = 0;

    String[] sharp = {"C#", "D#", "F#", "G#", "A#"};
    String[] changeChar = {"H", "I", "J", "K", "L"};

    for(int i = 0; i < sharp.length; i++) {
      m = m.replaceAll(sharp[i], changeChar[i]);
    }

    for(int i = 0; i < musicinfos.length; i++) {
      String musicinfo = musicinfos[i];

      String[] info = musicinfo.split(",");

      String start = info[0];
      String end = info[1];
      String title = info[2];
      String code = info[3];

      for(int j = 0; j < sharp.length; j++) {
        code = code.replaceAll(sharp[j], changeChar[j]);
      }

      LocalTime startTime = LocalTime.parse(start);
      LocalTime endTime = LocalTime.parse(end);

      int sHour = startTime.getHour();
      int sMinute = startTime.getMinute();

      int eHour = endTime.getHour();
      int eMinute = endTime.getMinute();

      int timeGap = (eHour - sHour) * 60 + (eMinute - sMinute);
      int gap = timeGap;

      int size = code.length();

      StringBuilder sb = new StringBuilder();
      while(true) {
        if(gap == 0) break;

        if(gap < size) {
          String cut = code.substring(0, gap);
          sb.append(cut);
          break;
        }

        else {
          sb.append(code);
          gap -= size;
        }
      }
      String value = sb.toString();

      if(value.contains(m)) {
        if(playTime == 0) {
          playTime = timeGap;
          answer = title;
        }
        else {
          if(playTime < timeGap) {
            playTime = timeGap;
            answer = title;
          }
        }
      }
    }

    if(playTime == 0) answer = "(None)";

    return answer;
  }

  public static void main(String[] args) {

//    String m = "ABCDEFG";
//    String[] musicinfos = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};

//    String m = "CC#BCC#BCC#BCC#B";
//    String[] musicinfos = {"03:00,03:30,FOO,CC#B",
//        "04:00,04:08,BAR,CC#BCC#BCC#B"};

    String m = "ABC";
    String[] musicinfos = {"12:00,12:14,HELLO,C#DEFGAB",
        "13:00,13:05,WORLD,ABCDEF"};

    System.out.println(solution(m, musicinfos));

  }

}
