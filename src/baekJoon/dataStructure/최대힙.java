package baekJoon.dataStructure;

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class 최대힙 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            if(x==0){
                if(pq.isEmpty()) {
                    bw.write("0");
                    bw.newLine();
                }
                else{
                    int top = pq.peek();
                    pq.poll();
                    bw.write(String.valueOf(top));
                    bw.newLine();
                }
            }else{
                pq.add(x);
            }
        }
        bw.flush();
        bw.close();
    }
}
