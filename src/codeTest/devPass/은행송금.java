package codeTest.devPass;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class 은행송금 {

  private static List<Link>[] banks;
  private static int answer;
  private static boolean[][] visited;

  public static int solution(int N, int[][] fees, int dest) {

    banks = new List[N + 1];
    visited = new boolean[N + 1][N + 1];

    for(int i = 0; i < N + 1; i++) {
      banks[i] = new ArrayList<>();
    }

    for(int i = 0; i < fees.length; i++) {
      int aBank = fees[i][0];
      int bBank = fees[i][1];
      int fee = fees[i][2];

      banks[aBank].add(new Link(bBank, fee));
      banks[bBank].add(new Link(aBank, fee));
    }

    sendMoney(dest);

    return answer;
  }

  private static void sendMoney(int end) {

    PriorityQueue<Link> pq = new PriorityQueue<>();
    pq.add(new Link(1, 0));

    while(true) {
      if(pq.isEmpty()) break;

      Link here = pq.poll();

      int bankNumber = here.bank;
      int currentFee = here.fee;

      if(bankNumber == end) {
        answer = currentFee;
        break;
      }

      for(int i = 0; i < banks[bankNumber].size(); i++) {
        Link next = banks[bankNumber].get(i);

        int nextBank = next.bank;
        int nextFee = next.fee;
        if(visited[bankNumber][nextBank]) continue;
        visited[bankNumber][nextBank] = true;
        visited[nextBank][bankNumber] = true;

        pq.add(new Link(nextBank, currentFee + nextFee));
      }
    }
  }

  private static class Link implements Comparable<Link>{
    int bank, fee;

    public Link(int bank, int fee) {
      this.bank = bank;
      this.fee = fee;
    }

    @Override
    public int compareTo(Link o) {
      if(this.fee > o.fee) return 1;
      else if(this.fee == o.fee) return 0;
      else return -1;
    }
  }

  public static void main(String[] args) {

  }

}
