package baekJoon.tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class LCA {


    // 1번 node를 root node로 생각하고 풀이
    private static final int ROOT_NUMBER = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int nodeCount = Integer.parseInt(input.nextToken());
        Node[] nodes = new Node[nodeCount + 1];

        // 연결 정보 저장.
        List<Integer>[] connections = new List[nodeCount + 1];
        for(int i = 0; i <= nodeCount; i++) {
            connections[i] = new ArrayList<Integer>();
            nodes[i] = new Node(i, i, 0);
        }

        for(int i = 0; i < nodeCount - 1; i++) {
            input = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(input.nextToken());
            int node2 = Integer.parseInt(input.nextToken());
            connections[node1].add(node2);
            connections[node2].add(node1);
        }

        // 연결 정보를 토대로 트리 구성
        nodes[ROOT_NUMBER] = new Node(ROOT_NUMBER, ROOT_NUMBER, 0);

        PriorityQueue<Node> queue = new PriorityQueue<>();
        boolean[] checked = new boolean[nodeCount + 1];
        queue.add(nodes[ROOT_NUMBER]);
        checked[ROOT_NUMBER] = true;

        while(!queue.isEmpty()) {

            Node currentNode = queue.poll();
            int currentNumber = currentNode.number;
            int currentDepth = currentNode.depth;

            for (int next : connections[currentNumber]) {
                if (checked[next])
                    continue;
                checked[next] = true;
                nodes[next].parentNumber = currentNumber;
                nodes[next].depth = currentDepth + 1;
                queue.add(nodes[next]);
            }
        }

        input = new StringTokenizer(br.readLine());
        int queryCount = Integer.parseInt(input.nextToken());

        for(int i = 0; i < queryCount; i++) {
            input = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(input.nextToken());
            int node2 = Integer.parseInt(input.nextToken());
            int result = findMinParentNumber(node1, node2, nodes);
            bw.write(String.valueOf(result));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();

    }

    private static int findMinParentNumber(int node1, int node2, Node[] nodes) {

        while(nodes[node1].depth > nodes[node2].depth) {
            node1 = nodes[node1].parentNumber;
        }

        while(nodes[node2].depth > nodes[node1].depth) {
            node2 = nodes[node2].parentNumber;
        }

        while(node1 != node2) {
            node1 = nodes[node1].parentNumber;
            node2 = nodes[node2].parentNumber;
        }
        return node1;
    }
    private static class Node implements Comparable<Node> {
        int number;
        int parentNumber;
        int depth;

        public Node(int number, int parentNumber, int depth) {
            this.number = number;
            this.parentNumber = parentNumber;
            this.depth = depth;
        }

        @Override
        public int compareTo(Node o) {
            return this.depth - o.depth;
        }
    }

}
