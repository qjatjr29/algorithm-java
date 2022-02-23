package baekJoon.bruteForce;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public class 링크와스타트 {
    static int N;
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] stat;
    static int[] temp;
    static int answer;
    static int count;
    public static void chk(int c,int index)
    {
        if(c == count)
        {
            int link = 0;
            int start = 0;
            for(int i=0;i<N-1;i++)
            {
                for(int j=i+1;j<N;j++)
                {
                    if(temp[i]==1 && temp[j]==1)
                        link += stat[i][j] + stat[j][i];
                    if(temp[i]==0 && temp[j]==0)
                        start += stat[i][j]+ stat[j][i];
                }
            }

            answer = min(answer,abs(start-link));
        }

        for(int i=index;i<N;i++)
        {
            temp[i] = 1;
            chk(c+1,i+1);
            temp[i] = 0;

        }
    }
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        stat = new int[N+1][N+1];
        answer = Integer.MAX_VALUE;
        temp = new int[N+1];
        for(int i=0;i<N;i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++)
            {
                stat[i][j] = Integer.parseInt(st.nextToken());

            }
        }


        for(count=1;count<N;count++)
        {
            chk(0,0);
        }
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
    }

}
