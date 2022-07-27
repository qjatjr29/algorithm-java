package baekJoon.Implementation;

import static java.util.Collections.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class 후보추천하기 {

  private static int N, recommends;
  private static int[] students;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());

    List<Picture> list = new ArrayList<>();
    List<Integer> answer = new ArrayList<>();

    N = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());

    recommends = Integer.parseInt(st.nextToken());

    students = new int[recommends];

    st = new StringTokenizer(br.readLine());

    for(int i = 0; i < recommends; i++) {
      int student = Integer.parseInt(st.nextToken());
      if(list.size() < N) {
        boolean chk = false;
        for(int j = 0; j < list.size(); j++) {
          if(list.get(j).index == student) {
            list.get(j).count++;
            chk = true;
            break;
          }
        }
        if(!chk) list.add(new Picture(student, 1, i));
      }
      else {
        sort(list);
        boolean chk = false;
        for (int j = 0; j < list.size(); j++) {
          if (list.get(j).index == student) {
            list.get(j).count++;
            chk = true;
            break;
          }
        }
        if (!chk) {
          list.remove(0);
          list.add(new Picture(student, 1, i));
        }
      }
    }

    for(int i = 0; i < list.size(); i++) {
      answer.add(list.get(i).index);
    }

    sort(answer);

    for (Integer ans : answer) {
      bw.write(String.valueOf(ans) + " ");
    }
    bw.newLine();
    bw.flush();
    bw.close();
  }

  private static class Picture implements Comparable<Picture> {
    int index, count, createdAt;

    public Picture(int index, int count, int createdAt) {
      this.index = index;
      this.count = count;
      this.createdAt = createdAt;
    }

    @Override
    public int compareTo(Picture o) {
      if(this.count == o.count) return this.createdAt - o.createdAt;
      return this.count - o.count;
    }



  }

}
