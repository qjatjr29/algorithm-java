package baekJoon.divideConquer;

import static java.util.Collections.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class 좌표압축 {

  private static int N;
  private static int[] array;
  private static Set<Integer> numbers;
  private static List<Integer> points;
  private static Map<Integer, Integer> answer;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    array = new int[N];
    answer = new HashMap<>();
    numbers = new HashSet<>();

    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < N; i++) {
      int number = Integer.parseInt(st.nextToken());
      numbers.add(number);
      array[i] = number;
    }

    points = new ArrayList<>(numbers);

    sort(points);


    for(int i = 0; i < points.size(); i++) {
      Integer target = points.get(i);
      int result = dc(0, points.size() - 1, target);
      answer.put(target, result);
    }

    for(int i = 0; i < N; i++) {
      bw.write(answer.get(array[i]) + " ");
    }

    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }

  private static int dc(int left, int right, int target) {
    int rtn = 0;
    while(true) {
      if(left > right) break;

      int mid = (left + right) / 2;

      int cmp = points.get(mid);

      if(cmp < target) {
        rtn = mid + 1;
        left = mid + 1;
      }

      else if(cmp == target) {
        right = mid - 1;
      }
      else {
        right = mid - 1;
      }
    }
    return rtn;
  }

}
