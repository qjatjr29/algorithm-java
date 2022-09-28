package programmers.kakao;

public class N진수게임 {

  private static StringBuilder sb;
  private static final String[] alpaList = {"A", "B", "C", "D", "E", "F"};

  public static String solution(int n, int t, int m, int p) {
    sb = new StringBuilder();
    int size = t * m;
    int number = 1;
    sb.append("0");

    while(true) {
      if(size < 0) break;
      String convert = convert(n, number);
      size -= convert.length();
      sb.append(convert);
      number++;
    }

    String str = sb.toString();
    StringBuilder answer = new StringBuilder();

    for(int i = 0; i < t; i++) {
      answer.append(str.charAt(p - 1));
      p += m;
    }
    return answer.toString();
  }

  private static String convert(int n, int number) {
    StringBuilder stringBuilder = new StringBuilder();
    int quot = number;
    int remain;

    while(true) {
      if(quot <= 0) break;

      remain = quot % n;
      quot = quot / n;

      if(remain >= 10) {
        stringBuilder.append(alpaList[remain % 10]);
      }
      else {
        stringBuilder.append(remain);
      }
    }
    StringBuilder reverse = stringBuilder.reverse();
    return reverse.toString();
  }

  public static void main(String[] args) {

    System.out.println(solution(2, 4, 2, 1));
    System.out.println(solution(16, 16, 2, 1));
    System.out.println(solution(16, 16, 2, 2));
  }

}
