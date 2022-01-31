package baekJoon.Implementation.p11719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    // BufferdReader 객체 생성
    private final static BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        //  출력을 위한 StringBuffer
        StringBuffer sb=new StringBuffer();

        N=Integer.parseInt(br.readLine());
        // BufferReader의 readLine을 이용해서 String 형태로 한 '\n' 까지 포함해
        // 한줄을 통째로 입력 받는다.
        // 그 한줄 한줄을 StringTokenizer에 저장.
        StringTokenizer st;
        for(int i=0;i<N;i++)
        {
            st=new StringTokenizer(br.readLine());
            StringBuilder result=new StringBuilder();
            while(st.hasMoreTokens())
            {
                StringBuilder word=new StringBuilder(st.nextToken());
                result.append(word.reverse().toString());
                result.append(" ");
            }
            System.out.println(result.toString());
        }

    }
}
