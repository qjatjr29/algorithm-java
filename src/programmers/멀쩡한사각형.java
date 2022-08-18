package programmers;

public class 멀쩡한사각형 {

  public static long solution(int w, int h) {
    long answer = (long) w * h;
    int gcd = gcd(w, h);

    long a = (long) ((w / gcd) + (h / gcd) - 1) * gcd;

    answer -= a;

    return answer;
  }

  public static void main(String[] args) {
    System.out.println(solution(8, 12));
  }

  private static int gcd (int a, int b) {
    if(a % b == 0) return b;
    else return gcd(b, a % b);
  }

}
