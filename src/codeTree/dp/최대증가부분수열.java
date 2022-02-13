package codeTree.dp;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.max;

public class 최대증가부분수열 {
    static int N;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[] numbers;
    static int[] dp;

    public static int solution()
    {
        int answer = 1;
        dp[1] = 1;
        for(int i=2;i<=N;i++)
        {
            int maxNUM=1;
            for(int j=1;j<i;j++)
            {
                if(numbers[i] > numbers[j])
                {
                    maxNUM = max(maxNUM,dp[j] + 1);
                }
            }
            dp[i] = maxNUM;
            answer = max(answer,dp[i]);
        }
        return answer;
    }
    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        dp = new int[N+1];
        numbers = new int[N+1];

        st = new StringTokenizer(br.readLine());

        for(int i=1;i<=N;i++)
        {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        int result = solution();
        bw.write(String.valueOf(result));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}
