package baekJoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 동전바꿔주기 {

  public static void main(String[] args) throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int target = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    int coins = Integer.parseInt(st.nextToken());

    int[][] cases = new int[coins + 1][target + 1];
    Coin[] coinList = new Coin[coins + 1];
    coinList[0] = new Coin(0, 0);

    for(int i = 1; i <= coins; i++) {
      st = new StringTokenizer(br.readLine());
      int cost = Integer.parseInt(st.nextToken());
      int count = Integer.parseInt(st.nextToken());
      coinList[i] = new Coin(cost, count);
    }

    Arrays.sort(coinList);

    for(int i = 0; i <= coins; i++) {
      cases[i][0] = 1;
    }

    for(int i = 1; i < coinList.length; i++) {
      Coin coin = coinList[i];
      int cost = coin.cost;
      int count = coin.count;
      for(int money = 1; money <= target; money++) {
        for(int z = 0; z <= count; z++) {
          // 구하고자 하는 값보다 더 큰 경우는 넘어간다.
          if(money < cost * z) break;
          // i번째 동전을 사용해서 money 만큼의 경우의 수
          // i - 1 번째까지의 경우의 수를 더 해준다.
          cases[i][money] += cases[i - 1][money - cost * z];
        }
      }
    }

    bw.write(String.valueOf(cases[coins][target]));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }

  private static class Coin implements Comparable<Coin> {
    int cost;
    int count;

    public Coin(int cost, int count) {
      this.cost = cost;
      this.count = count;
    }

    @Override
    public int compareTo(Coin o) {
      return this.cost - o.cost;
    }
  }

}
