package baekJoon.graph.bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 확장게임 {

    private static final char WALL = '#';
    private static final char EMPTY_SPACE = '.';

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        char[][] board = new char[N][M];

        int players = Integer.parseInt(input.nextToken());
        int[] states = new int[players + 1];
        int[] castles = new int[players + 1];
        Queue<Node>[] queue = new LinkedList[players + 1];

        input = new StringTokenizer(br.readLine());
        for(int i = 1; i <= players; i++) {
            states[i] = Integer.parseInt(input.nextToken());
            queue[i] = new LinkedList<>();
        }

        for(int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
            for(int j = 0; j < M; j++) {
                if('1' <= board[i][j] && board[i][j] <= '9') {
                    int playerNo = board[i][j] - '0';
                    queue[playerNo].add(new Node(i, j));
                    castles[playerNo]++;
                }
            }
        }
        move(players, states, board, queue, castles);

        for(int i = 1; i <= players; i++) {
            bw.write(String.valueOf(castles[i]) + " ");
        }
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static void move(int players, int[] states, char[][] board, Queue<Node>[] nodes, int[] castles) {

        int[] dx = new int[] {-1, 1, 0, 0};
        int[] dy = new int[] {0, 0, -1, 1};

        while(true) {
            for(int i = 1; i <= players; i++) {
                int moveCnt = states[i];
                for(int j = 0; j < moveCnt; j++) {
                    int size = nodes[i].size();
                    for(int z = 0; z < size; z++) {
                        Node cur = nodes[i].poll();
                        int cx = cur.x;
                        int cy = cur.y;
                        for(int d = 0; d < 4; d++) {
                            int nx = cx + dx[d];
                            int ny = cy + dy[d];
                            if(nx < 0 || nx >= board.length || ny < 0 || ny >= board[0].length) continue;
                            if(board[nx][ny] == EMPTY_SPACE) {
                                board[nx][ny] = (char) i;
                                nodes[i].add(new Node(nx, ny));
                                castles[i]++;
                            }
                        }
                    }
                    if(nodes[i].isEmpty()) break;
                }
            }
            boolean check = true;
            for(int i = 1; i <= players; i++) {
                if(!nodes[i].isEmpty()) {
                    check = false;
                    break;
                }
            }
            if(check) break;
        }


    }

    private static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
