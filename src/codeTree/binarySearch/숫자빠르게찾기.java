package codeTree.binarySearch;

import java.io.*;
import java.util.StringTokenizer;

public class 숫자빠르게찾기 {
    static int n,m;
    static int[] numbers;
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int bs(int left,int right,int value)
    {
        if(left >= right) return -1;
        int mid = (left+right)/2;
        if(numbers[mid] == value) return mid+1;
        if(numbers[mid] < value)
        {
            return bs(mid + 1, right,value);
        }
        else if(numbers[mid]> value){
            return bs(left,mid,value);
        }
        return -1;
    }
    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        numbers = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++)
        {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<m;i++)
        {
            st = new StringTokenizer(br.readLine());
            int findNumber = Integer.parseInt(st.nextToken());
            int answer = bs(0,n,findNumber);
            bw.write(String.valueOf(answer));
            bw.newLine();
        }
        bw.flush();
        bw.close();

    }

}
