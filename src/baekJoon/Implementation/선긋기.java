package baekJoon.Implementation;

import java.io.*;
import java.util.StringTokenizer;

import static java.util.Arrays.sort;

public class 선긋기 {
    static int N;
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Pair[] lines;
    static int answer;
    public static class Pair implements Comparable<Pair>{
        int start,end;
        Pair(int x,int y){
            this.start = x;
            this.end = y;
        }

        @Override
        public int compareTo(Pair o) {
            if(this.start == o.start)
                return Integer.compare(this.end, o.end);
            else
                return Integer.compare(this.start, o.start);

        }


    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        lines = new Pair[N];
        for(int i=0;i<N;i++)
        {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            lines[i] = new Pair(s,e);
        }
        sort(lines);

        int startPoint = lines[0].start;
        int endPoint = lines[0].end;
        answer += endPoint-startPoint;
        for(int i=1;i<N;i++) {
            int curStart = lines[i].start;
            int curEnd = lines[i].end;
            // 이전 선에 포함
            if (startPoint <= curStart && curEnd <= endPoint) continue;
            // 시작 점이 예전 선에 포함.
            else if (curStart < endPoint){
                answer += (curEnd - endPoint);
            }
            else answer += (curEnd-curStart);
            startPoint = curStart;
            endPoint = curEnd;
        }
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
    }

}
