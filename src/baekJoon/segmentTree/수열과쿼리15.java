package baekJoon.segmentTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 수열과쿼리15 {

    private static final int PRINT = 2;
    private static final int UPDATE = 1;

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

        int[] tree = new int[treeSize];
        init(1, 1, size, numbers, tree);

        input = new StringTokenizer(br.readLine());
        int queryCount = Integer.parseInt(input.nextToken());

        for(int i = 0; i < queryCount; i++) {
            input = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(input.nextToken());
            if(command == PRINT) {
                bw.write(String.valueOf(tree[1]));
                bw.newLine();
            }
            else if(command == UPDATE) {
                int index = Integer.parseInt(input.nextToken());
                int value = Integer.parseInt(input.nextToken());
                numbers[index] = value;
                update(1, index, 1, size, numbers, tree);
            }
        }

        bw.flush();
        bw.close();
        br.close();

    }

    private static int checkMinIndex(int index1, int index2, int[] numbers) {
        if(numbers[index1] > numbers[index2]) return index2;
        else if(numbers[index1] == numbers[index2]) return Math.min(index1, index2);
        return index1;
    }

    private static void init(int node, int start, int end, int[] numbers, int[] tree) {

        // leaf 노드 도착한 경우
        if(start == end) {
            tree[node] = start;
            return;
        }

        int mid = (start + end) / 2;
        init(node * 2, start, mid, numbers, tree);
        init(node * 2 + 1, mid + 1, end, numbers, tree);

        tree[node] = checkMinIndex(tree[node * 2], tree[node * 2 + 1], numbers);
    }

    private static void update(int node, int index, int start, int end, int[] numbers, int[] tree) {

        // 수정한 index 와는 관계가 없는 범위인 경우
        if(start > index || end < index) return;

        // leaf 노드까지 온 경우
        if(start == end) {
            tree[node] = index;
            return;
        }

        int mid = (start + end) / 2;
        update(node * 2, index, start, mid, numbers, tree);
        update(node * 2 + 1, index, mid + 1, end, numbers, tree);

        tree[node] = checkMinIndex(tree[node * 2], tree[node * 2 + 1], numbers);
    }
}
