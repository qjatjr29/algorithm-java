package baekJoon.dp;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.max;


public class 상자넣기 {
    static int n;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[] dp;
    static int[] box;
    public static int setDp()
    {
        dp[1] = 1;
        int answer = 1;
        for(int i=2;i<=n;i++)
        {
            int count = 1;
            for(int j=1;j<i;j++)
            {
                if(box[i] > box[j])
                {
                    count = max(count,dp[j]+1);
                }
            }
            dp[i] = count;
            answer = max(answer,dp[i]);
        }
        return answer;
    }
    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        dp = new int[n+1];
        box = new int[n+1];

        st = new StringTokenizer(br.readLine());

        for(int i=1;i<=n;i++)
        {
            box[i] = Integer.parseInt(st.nextToken());
        }
        int result = setDp();
        bw.write(String.valueOf(result));
        bw.newLine();
        bw.flush();
        bw.close();

    }

}
