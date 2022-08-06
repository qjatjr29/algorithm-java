package codeTest.카카오엔터프라이즈;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 펭귄은추워요 {

  private static long minCount, maxCount;

  public static void main(String[] args) throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    minCount = Long.MAX_VALUE;
    maxCount = -1;

    StringTokenizer st = new StringTokenizer(br.readLine());

    int left = Integer.parseInt(st.nextToken());
    int mid = Integer.parseInt(st.nextToken());
    int right = Integer.parseInt(st.nextToken());

    Penguin penguin = new Penguin(left, mid, right);

    minMove(penguin);
    maxMove(penguin);

    bw.write(minCount + "\n");
    bw.write(maxCount+ "\n");
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }
  private static void minMove(Penguin penguin) {

    PriorityQueue<Penguin> penguinQueue = new PriorityQueue<>();
    penguinQueue.add(penguin);

    while(true) {
      if(penguinQueue.isEmpty()) break;
      Penguin here = penguinQueue.poll();

      int cLeft = here.left;
      int cMid = here.mid;;
      int cRight = here.right;
      int cCount = here.count;

      int leftGap = cMid - cLeft - 1;
      int rightGap = cRight - cMid - 1;
      if(leftGap == 0 && rightGap == 0) {
        minCount = cCount;
        break;
      }

      if(leftGap < rightGap) {
        // 왼쪽 펭귄이 이동해야 함.
        if(leftGap == 0) {

          int mid = (cMid + cRight) / 2;
          penguinQueue.add(new Penguin(cMid, mid, cRight, cCount + 1));
        }
        // 오른쪽 펭귄이 이동
        else {
          int mid = (cLeft + cMid) / 2;
          penguinQueue.add(new Penguin(cLeft, mid, cMid, cCount + 1));
        }
      }

      else {
        // 오른쪽 펭귄이 이동
        int mid = (cMid + cRight) / 2;
        penguinQueue.add(new Penguin(cMid, mid, cRight, cCount + 1));
      }
    }
  }

  private static void maxMove(Penguin penguin) {
    PriorityQueue<Penguin> penguinQueue = new PriorityQueue<>(Collections.reverseOrder());
    penguinQueue.add(penguin);

    while(true) {
      if(penguinQueue.isEmpty()) break;
      Penguin here = penguinQueue.poll();

      int cLeft = here.left;
      int cMid = here.mid;;
      int cRight = here.right;
      int cCount = here.count;

      int leftGap = cMid - cLeft - 1;
      int rightGap = cRight - cMid - 1;
      if(leftGap == 0 && rightGap == 0) {
        maxCount = cCount;
        break;
      }
      // 왼쪽 펭귄이 이동
      if(leftGap < rightGap) {
        penguinQueue.add(new Penguin(cMid, cMid + 1, cRight, cCount + 1));
        penguinQueue.add(new Penguin(cMid, cRight - 1, cRight, cCount + 1));
      }

      // 오른쪽 펭귄이 이동
      else {
        penguinQueue.add(new Penguin(cLeft, cLeft + 1, cMid, cCount + 1));
        penguinQueue.add(new Penguin(cLeft, cMid - 1, cMid, cCount + 1));
      }
    }
  }

  private static class Penguin implements Comparable<Penguin>{
    int left, mid, right;
    int count;

    public Penguin(int left, int mid, int right) {
      this.left = left;
      this.mid = mid;
      this.right = right;
      this.count = 0;
    }

    public Penguin(int left, int mid, int right, int count) {
      this.left = left;
      this.mid = mid;
      this.right = right;
      this.count = count;
    }

    @Override
    public int compareTo(Penguin o) {
      if(this.count > o.count) return 1;
      else if(this.count == o.count) return 0;
      else return -1;
    }
  }
}
