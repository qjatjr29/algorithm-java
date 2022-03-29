package baekJoon.dataStructure;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class 보석도둑 {
    static int N,M,V,K,C;
    static Long answer = 0l;
    public static class Pair implements Comparable<Pair> {
        int weight,cost;
        Pair(int weight,int cost){
            this.cost = cost;
            this.weight = weight;
        }

        @Override
        public int compareTo(Pair o) {
            return this.weight - o.weight;
        }
    }
    static Pair[] jewelry;
    static int[] bag;
    public static void sol(){
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int index = 0;
        for(int i=0;i<K;i++){
            while(true){
                if(index >= N || jewelry[index].weight > bag[i]) break;
                pq.add(jewelry[index].cost);
                index++;
            }
            if(!pq.isEmpty()) answer += pq.poll();
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 보석개수
        K = Integer.parseInt(st.nextToken()); // 가방개수
        jewelry = new Pair[N];
        bag = new int[K];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken()); // 각 보석의 무게
            V = Integer.parseInt(st.nextToken()); // 각 보석 가격
            jewelry[i] = new Pair(M,V);
        }
        for(int i=0;i<K;i++){
            st = new StringTokenizer(br.readLine());
            C = Integer.parseInt(st.nextToken());
            bag[i] = C;
        }
        Arrays.sort(jewelry);
        Arrays.sort(bag);
        sol();
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}
