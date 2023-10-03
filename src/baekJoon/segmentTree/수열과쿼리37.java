package baekJoon.segmentTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 수열과쿼리37 {

    private static final int UPDATE_COMMAND = 1;
    private static final int PRINT_EVEN = 2;
    private static final int PRINT_ODD = 3;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int size = Integer.parseInt(input.nextToken());
        int[] numbers = new int[size + 1];

        input = new StringTokenizer(br.readLine());
        for(int i = 1; i <= size; i++) {
            numbers[i] = Integer.parseInt(input.nextToken());
        }

        int treeHeight = (int) Math.ceil(Math.log(size) / Math.log(2));
        int treeSize = (int) Math.pow(2, treeHeight + 1);
        int[] evenTree = new int[treeSize];
        int[] oddTree = new int[treeSize];

        initEvenTree(1, 1, size, numbers, evenTree);
        initOddTree(1, 1, size, numbers, oddTree);

        input = new StringTokenizer(br.readLine());
        int updateCount = Integer.parseInt(input.nextToken());

        for(int i = 0; i < updateCount; i++) {
            input = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(input.nextToken());

            if(command == UPDATE_COMMAND) {
                int index = Integer.parseInt(input.nextToken());
                int value = Integer.parseInt(input.nextToken());
                numbers[index] = value;
                updateEvenTree(1, index, 1, size, numbers, evenTree);
                updateOddTree(1, index, 1, size, numbers, oddTree);
            }
            else {
                int left = Integer.parseInt(input.nextToken());
                int right = Integer.parseInt(input.nextToken());
                if(command == PRINT_EVEN) {
                    int result = getEvenCount(1, 1, size, left, right, evenTree);
                    bw.write(String.valueOf(result));
                }
                else if(command == PRINT_ODD) {
                    int result = getOddCount(1, 1, size, left, right, oddTree);
                    bw.write(String.valueOf(result));
                }
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
        br.close();

    }

    private static int initEvenTree(int node, int start, int end, int[] numbers, int[] tree) {

        if(start == end) {
            if(numbers[start] % 2 == 0) tree[node] = 1;
            else tree[node] = 0;
            return tree[node];
        }

        int mid = (start + end) / 2;

        return tree[node] = initEvenTree(node * 2, start, mid, numbers, tree)
            + initEvenTree(node * 2 + 1, mid + 1, end, numbers, tree);
    }

    private static int initOddTree(int node, int start, int end, int[] numbers, int[] tree) {

        if(start == end) {
            if(numbers[start] % 2 == 1) tree[node] = 1;
            else tree[node] = 0;
            return tree[node];
        }

        int mid = (start + end) / 2;

        return tree[node] = initOddTree(node * 2, start, mid, numbers, tree)
            + initOddTree(node * 2 + 1, mid + 1, end, numbers, tree);
    }

    private static void updateEvenTree(int node, int index, int start, int end, int[] numbers, int[] tree) {

        // 수정한 index 와는 관계가 없는 범위인 경우
        if(start > index || end < index) return;

        // leaf 노드까지 온 경우
        if(start == end) {
            if(numbers[start] % 2 == 0) tree[node] = 1;
            else tree[node] = 0;
            return;
        }

        int mid = (start + end) / 2;
        updateEvenTree(node * 2, index, start, mid, numbers, tree);
        updateEvenTree(node * 2 + 1, index, mid + 1, end, numbers, tree);

        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    private static void updateOddTree(int node, int index, int start, int end, int[] numbers, int[] tree) {

        // 수정한 index 와는 관계가 없는 범위인 경우
        if(start > index || end < index) return;

        // leaf 노드까지 온 경우
        if(start == end) {
            if(numbers[start] % 2 == 1) tree[node] = 1;
            else tree[node] = 0;
            return;
        }

        int mid = (start + end) / 2;
        updateOddTree(node * 2, index, start, mid, numbers, tree);
        updateOddTree(node * 2 + 1, index, mid + 1, end, numbers, tree);

        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    private static int getEvenCount(int node, int start, int end, int left, int right, int[] tree) {
        if(left > end || right < start) return 0;

        if(left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;

        return getEvenCount(node * 2, start, mid, left, right, tree)
            + getEvenCount(node * 2 + 1, mid + 1, end, left, right, tree);
    }

    private static int getOddCount(int node, int start, int end, int left, int right, int[] tree) {
        if(left > end || right < start) return 0;

        if(left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;
        return getOddCount(node * 2, start, mid, left, right, tree) +
            getOddCount(node * 2 + 1, mid + 1, end, left, right, tree);
    }

}
