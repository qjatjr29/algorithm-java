package baekJoon.segmentTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 부분배열고르기 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int size = Integer.parseInt(input.nextToken());
        int[] numbers = new int[size + 1];

        int height = (int) Math.ceil(Math.log(size) / Math.log(2));
        long[] sumTree = new long[(int) Math.pow(2, (height + 1))];
        int[] minTree = new int[(int) Math.pow(2, (height + 1))];

        input = new StringTokenizer(br.readLine());

        for(int i = 1; i <= size; i++) {
            numbers[i] = Integer.parseInt(input.nextToken());
        }

        initMinIndexArray(1, 1, size, numbers, minTree);
        initSumArray(1, 1, size, numbers, sumTree);

        long answer = getAnswer(1, size, size, numbers, sumTree, minTree);
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();


    }

    private static long getAnswer(int start, int end, int size, int[] numbers, long[] sumTree, int[] minTree) {
        if(start == end) return (long) numbers[start] * numbers[start];

        int minIndex = getMinValue(1, 1, size, start, end, numbers, minTree);
        long result = numbers[minIndex] * getSubArraySum(1, 1, size, start, end, sumTree);

        if(start <= minIndex - 1) {
            result = Math.max(result, getAnswer(start, minIndex - 1, size, numbers, sumTree, minTree));
        }

        if(minIndex + 1 <= end) {
            result = Math.max(result, getAnswer(minIndex + 1, end, size, numbers, sumTree, minTree));
        }

        return result;
    }

    private static long initSumArray(int node, int start, int end, int[] numbers, long[] sumTree) {

        if(start == end) {
            return sumTree[node] = numbers[start];
        }

        int mid = (start + end) / 2;
        long leftSide = initSumArray(node * 2, start, mid, numbers, sumTree);
        long rightSide = initSumArray(node * 2 + 1, mid + 1, end, numbers, sumTree);

        return sumTree[node] = leftSide + rightSide;
    }

    private static int initMinIndexArray(int node, int start, int end, int[] numbers, int[] minTree) {
        if(start == end) return minTree[node] = start;

        int mid = (start + end) / 2;
        int leftMinIndex = initMinIndexArray(node * 2 , start, mid, numbers, minTree);
        int rightMinIndex = initMinIndexArray(node * 2 + 1, mid + 1, end, numbers, minTree);

        return minTree[node] = numbers[leftMinIndex] < numbers[rightMinIndex] ? leftMinIndex : rightMinIndex;
    }

    private static long getSubArraySum(int node, int start, int end, int left, int right, long[] sum) {
        if(left > end || right < start) return 0;

        if(left <= start && end <= right) return sum[node];

        int mid = (start + end) / 2;
        return getSubArraySum(node * 2, start, mid, left, right, sum) +
            getSubArraySum(node * 2 + 1, mid + 1, end, left, right, sum);
    }

    private static int getMinValue(int node, int start, int end, int left, int right, int[] numbers, int[] minTree){

        // 구하고자 하는 범위를 넘어간 경우
        if(start > right || end < left ) return -1;

        // leaf 노드 도달
        if(start >= left && end <= right) return minTree[node];

        int mid = (start + end) / 2;
        int leftSide = getMinValue(node * 2 , start , mid ,left, right, numbers, minTree);
        int rightSide = getMinValue(node * 2 + 1 , mid + 1, end, left, right, numbers, minTree);

        if(leftSide == -1) return rightSide;
        else if(rightSide == -1) return leftSide;

        return numbers[leftSide] < numbers[rightSide] ? leftSide : rightSide;
    }

}
