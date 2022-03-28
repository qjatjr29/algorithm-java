package baekJoon.dataStructure;

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 최소힙 {

    static int N;
    static PriorityQueue<Integer> pq;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>();
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
                    pq.remove();
                    bw.write(String.valueOf(top));
                    bw.newLine();
                }
            }
            else{
                pq.add(x);
            }
        }
        bw.flush();
        bw.close();


    }
}
