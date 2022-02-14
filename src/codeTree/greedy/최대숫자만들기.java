package codeTree.greedy;

import java.io.*;
import java.util.*;

import static java.lang.Math.min;
import static java.util.Arrays.sort;

public class 최대숫자만들기 {
    static int n;
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static String[] numbers;

    public static String solution()
    {
        String answer = "";

        sort(numbers,new Comparator<String>(){
            @Override
            public int compare(String a, String b) {
                return (b+a).compareTo(a+b);
            }
        });

        //if(numbers[0].equals("0")) return "0";
        return String.join("",numbers);
    }


    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        numbers=new String[n+1];
        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine());
            numbers[i] = st.nextToken();
        }

        String ans = solution();
        bw.write(ans);
        bw.newLine();
        bw.flush();
        bw.close();

    }

}
