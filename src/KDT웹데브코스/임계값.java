package KDT웹데브코스;

import java.io.*;

import static java.lang.Math.abs;
import static java.lang.Math.min;
import static java.util.Arrays.sort;

public class 임계값 {
    static int answer;
    public static int dq(int value,int index,int[] arr){

        // value = 가운데 값.
        // index = 몇번째 인덱스인지(가운데값이)
        int left = index;
        int right = index;
        int size = arr.length;
        while(true)
        {
            if(left<=0) break;
            left--;
            if(arr[left] != value) break;
        }
        while(true)
        {
            if(right>=size-1) break;
            right++;
            if(arr[right] != value) break;
        }
        // 예외 상황  11111 -> 0 , 00000-> 1
        if(left==0 && right == size-1 && arr[left]==value && arr[right]==value){
            int r = 0;
            if(value == 0) return 1;
            else return 0;
        }

        left ++;
        right = size - right;
        int leftSide = abs(size - left - left);
        int rightSide = abs(size-right-right);

        if(leftSide > rightSide)
        {
            return value + 1;
        }
        else return arr[left-1] +1;

    }
    public static int solution(int[] arr) {

        sort(arr);

        int size = arr.length;
        answer = arr[size/2-1] +1 ;
        int temp = dq(answer-1, size/2-1,arr);
        answer = min(answer,temp);
        return answer;
    }

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[] a;


    public static void main(String[] args) throws IOException {

        a = new int[4];
        a[0] = 100;
        a[1] = 50;
        a[2] = 100;
        a[3] = 200;
//        a[4] = 0;
//        a[5] = 0;
//        a[6] = 255;
//        a[7] = 255;
//        a[8] = 255;
//        a[9] = 23;
//        a[10] = 203;
//        a[11] = 98;
//        a[12] = 154;
//        a[13] = 255;
        int ans = solution(a);
        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.flush();
        bw.close();

    }
}
