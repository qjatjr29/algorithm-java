package programmers.bruteForce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

import static java.lang.Math.sqrt;

public class 소수찾기 {
  //  private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    //private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] sosu = new int[10000000];
    static Set<Integer> findNumbers = new HashSet<>();
    public static void set()
    {
        sosu[0]=0;
        sosu[1]=0;

        for(int i=2;i<=9999999;i++)
        {
            sosu[i]=i;
        }

        for(int i=2;i<=(int)sqrt(9999999);i++)
        {
            if(sosu[i]==0) continue;
            for(int j=i+i;j<=9999999;j+=i)
            {
                sosu[j]=0;
            }
        }
    }
    public static void find(String preStr,String str)
    {
        int size = str.length();
        if(preStr != "") findNumbers.add(Integer.parseInt(preStr));

        for(int i=0;i<size;i++)
        {
            find(preStr+str.charAt(i),str.substring(0,i)+str.substring(i+1,size));
        }
    }
    public static int solution(String numbers)
    {
        int answer = 0;
        set();
        find("",numbers);

        while(findNumbers.iterator().hasNext())
        {
            int num = findNumbers.iterator().next();
            findNumbers.remove(num);
            if(sosu[num]!=0) answer++;
        }
        return answer;
    }

    public static void main(String[] args) {
        //String s = "17";
        String s = "011";
        System.out.println(solution(s));
    }
}
