package baekJoon.sort;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.abs;
import static java.util.Arrays.sort;

public class 두용액2470 {
    static int N;
    static Long[] result = new Long[2];
    static Long[] liquid;
    static Long amount;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void findLiquid()
    {
        int left = 0;
        int right = liquid.length-1;
        while(true)
        {
            if(left>=right) break;
            Long sum = liquid[left] + liquid[right];
            Long absNum= abs(sum);
            if(amount > absNum)
            {
                amount = absNum;
                result[0] = liquid[left];
                result[1] = liquid[right];
            }
            if(sum > 0 )right--;
            else left++;
        }
    }
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        amount = 2000000001L;

        st = new StringTokenizer(br.readLine());
        liquid = new Long[N];
        for(int i=0;i<N;i++)
        {
            liquid[i] = Long.parseLong(st.nextToken());
        }
        sort(liquid);
        findLiquid();
        for(int i=0;i<2;i++)
        {
            bw.write(String.valueOf(result[i])+" ");
        }
        bw.newLine();
        bw.flush();
        bw.close();

    }
}
