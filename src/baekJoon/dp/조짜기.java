package baekJoon.dp;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.abs;
import static java.lang.Math.max;


public class 조짜기 {
    static int N;
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] students;
    static int[] dp;
    static int answer;
    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        students = new int[N+1];
        dp = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i =1;i<=N;i++)
        {
            students[i] = Integer.parseInt(st.nextToken());
            for(int j=i-1;j>0;j--)
            {
                answer = max(answer, abs(students[i]-students[j]) + dp[j-1]);
            }
            dp[i] = answer;
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
    }

}
