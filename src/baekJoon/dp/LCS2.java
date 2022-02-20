package baekJoon.dp;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.Math.max;

public class LCS2 {
    static String s1,s2;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[][] LCS;
    static ArrayList<Character> answer;
    public static void sol()
    {
        int count = 0;
        for(int i= 0; i<=s1.length();i++)
        {
            for(int j=0;j<=s2.length();j++)
            {
                if(i==0 || j==0) continue;
                if(s1.charAt(i-1) == s2.charAt(j-1))
                {
                    LCS[i][j] = LCS[i-1][j-1] + 1;
                }
                else LCS[i][j] = max(LCS[i-1][j],LCS[i][j-1]);
                count = max(count,LCS[i][j]);
            }
        }

        int x = s1.length();
        int y = s2.length();
        while(true)
        {
            if(x==0 || y==0) break;
            int curCount = LCS[x][y];

            if(curCount == LCS[x-1][y])
            {
                x = x-1;
                continue;
            }
            if(curCount == LCS[x][y-1])
            {
                y = y-1;
                continue;
            }
            answer.add(s1.charAt(x-1));
            x = x-1;
            y = y-1;
        }
    }
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        s1 = st.nextToken();
        st = new StringTokenizer(br.readLine());
        s2 = st.nextToken();
        answer = new ArrayList<>();
        LCS = new int[1001][1001];
        sol();
        bw.write(String.valueOf(answer.size()));
        bw.newLine();
        for(int i=answer.size()-1;i>=0;i--)
        {
            bw.write(String.valueOf(answer.get(i)));
        }
        bw.newLine();
        bw.flush();
        bw.close();
    }

}
