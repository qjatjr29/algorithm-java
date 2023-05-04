package baekJoon.simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 국회의원선거 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int candidateSize = Integer.parseInt(st.nextToken());
    int answer = 0;

    st = new StringTokenizer(br.readLine());
    int firstCandidate = Integer.parseInt(st.nextToken());

    PriorityQueue<Integer> candidatePriorityQueue = new PriorityQueue<>(Comparator.reverseOrder());

    for(int i = 0; i < candidateSize - 1; i++) {
      st = new StringTokenizer(br.readLine());
      candidatePriorityQueue.add(Integer.parseInt(st.nextToken()));
    }

    while(true) {

      if(candidatePriorityQueue.isEmpty()) break;
      int count = candidatePriorityQueue.poll();
      if(firstCandidate + answer > count) break;
      answer++;
      candidatePriorityQueue.add(count - 1);
    }

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }

}
