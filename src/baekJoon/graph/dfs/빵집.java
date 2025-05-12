package baekJoon.graph.dfs;

import java.io.*;
import java.util.StringTokenizer;

public class 빵집 {
    private static final int[] dx = {-1, 0, 1};
    private static final int[] dy = {1, 1, 1};
    private static int answer;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(input.nextToken());
        int c = Integer.parseInt(input.nextToken());
        char[][] map = new char[r][c];

        for (int i = 0; i < r; i++) {
            input = new StringTokenizer(br.readLine());
            String s = input.nextToken();
            for (int j = 0; j < c; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        visited = new boolean[r][c];
        answer = 0;
        for (int i = 0; i < r; i++) {
            if (map[i][0] == 'x') continue;
            dfs(map, i, 0);
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean dfs(char[][] map, int x, int y) {

        // 도착한 경우
        if (y == map[0].length - 1) {
            answer++;
            visited[x][y] = true;
            return true;
        }
        // 해당 칸 사용
        visited[x][y] = true;
        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= map.length || ny >= map[0].length || visited[nx][ny]) continue;
            if (map[nx][ny] == 'x') continue;
            // 하나의 경우라도 가스관 연결에 성공했다면 true 반환하고 해당 칸은 사용x
            if (dfs(map, nx, ny)) return true;
        }
        // 가스관 연결에 실패 -> false 반환
        return false;
    }
}
