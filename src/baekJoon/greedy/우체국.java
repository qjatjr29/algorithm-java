package baekJoon.greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2141
public class 우체국 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int answer = 0;
    int N = Integer.parseInt(st.nextToken());

    int totalPeople = 0;
    City[] cities = new City[N];


    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int location = Integer.parseInt(st.nextToken());
      int people = Integer.parseInt(st.nextToken());
      cities[i] = new City(location, people);
      totalPeople += people;
    }

    Arrays.sort(cities);

    // 전체 인원의 중간값을 넘어갈 때 그곳의 위치..! (사람 수의 중간에 우체국이 위치할 때 가장 최소값)
    int people = 0;
    for(int i = 0; i < N; i++) {
      people += cities[i].people;
      if(people >= (totalPeople + 1) / 2) {
        answer = cities[i].location;
        break;
      }
    }
    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }

  private static class City implements Comparable<City> {
    int location;
    int people;

    public City(int location, int people) {
      this.location = location;
      this.people = people;
    }

    @Override
    public int compareTo(City o) {
      return this.location - o.location;
    }
  }

}
