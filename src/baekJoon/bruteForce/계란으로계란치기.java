package baekJoon.bruteForce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16987
public class 계란으로계란치기 {

  private static int answer;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    Egg[] eggs = new Egg[N];

    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int d = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      eggs[i] = new Egg(i, d, w);
    }

    dfs(0,0, eggs);

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }

  private static void dfs(int index, int count, Egg[] eggs) {

    // 이미 끝의 계란까지 다 들어본 경우 끝.
    if(index == eggs.length) {
      answer = Math.max(answer, count);
      return;
    }

    // 현재 들고 있는 계란
    Egg egg = eggs[index];

    // 들고 있는 계란이 깨져있거나 이미 다른 모든 계란들이 깨져있는 경우
    // 다음 계란으로 넘어간다.
    if(egg.isBreak || count == eggs.length - 1) {
      dfs(index + 1, count, eggs);
      return;
    }

    for(int i = 0; i < eggs.length; i++) {
      if(egg.number == i) continue;

      // 다른 계란
      Egg cmp = eggs[i];

      // 깨져있는 경우 다음으로 넘어간다.
      if(cmp.isBreak) continue;

      // 해당 계란을 치는 경우
      egg.crash(cmp);
      cmp.crash(egg);

      int nCount = count;

      // 다른 계란이 깨진 경우
      if(cmp.isBreak) nCount++;

      // 들고 있는 계란이 깨진 경우 -> 다음 계란을 찾아야한다
      if(egg.isBreak) nCount++;


      // 들고 있는 계란을 내려놓고 다음 계란을 든다.
      dfs(index + 1, nCount, eggs);

      // 치지않은 상황으로 복구 후 다음 계란을 깨도록 한다.
      egg.unCrash(cmp);
      cmp.unCrash(egg);
    }


  }

  private static class Egg {
    int number;
    int durability;
    int weight;
    boolean isBreak;
    Egg(int number, int durability, int weight) {
      this.number = number;
      this.durability = durability;
      this.weight = weight;
      this.isBreak = false;
    }

    public void crash(Egg cmp) {
      this.durability -= cmp.weight;
      if(this.durability <= 0) this.isBreak = true;
    }

    public void unCrash(Egg cmp) {
      this.durability += cmp.weight;
      this.isBreak = false;
    }

  }

}
