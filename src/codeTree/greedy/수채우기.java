package codeTree.greedy;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.min;

public class 수채우기 {
    static int n;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[] coins;
    public static void setDP()
    {
        coins[0] = 0;
        coins[1] = -1;
        coins[2] = 1;
        coins[3] = -1;
        coins[4] = 2;
        for(int i=5;i<100001;i++)
        {
            int coin = 987654321;
            if(coins[i-2] != -1)
            {
                coin = coins[i-2] + 1;
            }
            if(coins[i-5] != -1)
            {
                coin = min(coin,coins[i-5]+1);
            }
            if(coin == 987654321)
            {
                coins[i] = -1;
            }
            else coins[i] = coin;
        }

    }
    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        coins = new int[100001];
        setDP();
        bw.write(String.valueOf(coins[n]));
        bw.newLine();
        bw.flush();
        bw.close();
    }

}
