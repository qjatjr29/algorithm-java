package baekJoon.dataStructure;

import java.io.*;
import java.util.*;

public class 컵라면 {

    public static class Pair implements Comparable<Pair> {
        int deadLine;
        int ramen;
        Pair(int deadLine,int ramen){
            this.deadLine = deadLine;
            this.ramen = ramen;
        }

        @Override
        public int compareTo(Pair o) {
            return this.deadLine - o.deadLine;
        }
    }
    static int N;
    static PriorityQueue<Integer> pq;
    static Pair[] problems;
    static Long answer = 0L;
    public static void sol(){
        int index = 0;
        int cnt = problems[0].deadLine;
        for(int i=0;i<N;i++){
            while(true){
                if(index >= N || problems[index].deadLine < cnt ) break;
                pq.add(problems[index].ramen);
                index++;
            }
            cnt--;
            if(cnt < 0 )return;
            if(!pq.isEmpty()) answer += pq.poll();
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        pq = new PriorityQueue<>(Collections.reverseOrder());

        N = Integer.parseInt(st.nextToken());
        problems = new Pair[N+1];
        int deadLine;
        int cupRamen;
        problems[0] = new Pair(0,0);
        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            deadLine = Integer.parseInt(st.nextToken());
            cupRamen = Integer.parseInt(st.nextToken());
            problems[i] = new Pair(deadLine,cupRamen);
        }

        Arrays.sort(problems, Collections.reverseOrder());
        sol();
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}
