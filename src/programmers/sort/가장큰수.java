package programmers.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;

import static java.lang.Math.min;
import static java.util.Arrays.sort;


public class 가장큰수 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[] numbers;

    public static String solution(int[] numbers){
        String answer = "";
        //StringBuilder answer = new StringBuilder();
        String[] strNumbers= new String[numbers.length];

        for(int i=0;i<numbers.length;i++)
        {
            strNumbers[i] = Integer.toString(numbers[i]);
        }

        sort(strNumbers,new Comparator<String>(){
            @Override
            public int compare(String a, String b) {
                return (b+a).compareTo(a+b);
            }
        });

        if(strNumbers[0].equals("0")) return "0";

        return String.join("",strNumbers);
    }

    public static void main(String[] args) {
//        int[] nums= {6,10,2};
        int[] nums= {3,30,34,5,9};
        String ans = solution(nums);
        System.out.println(ans);
    }
}
