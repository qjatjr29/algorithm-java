package baekJoon.graph.dfs;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.max;

public class 알파벳 {
    static int R,C;
    static char[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int answer;

    static boolean alpha[];
    public static void sol(int x,int y,int cnt)
    {
        answer = max(answer,cnt);
        for(int i=0;i<4;i++)
        {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || nx>=R || ny < 0 || ny>=C) continue;
            int chk = (int)map[nx][ny]-65;
            if(alpha[chk]) continue;
            alpha[chk] = true;
            sol(nx,ny,cnt+1);
            alpha[chk] = false;
        }
    }
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        alpha = new boolean[27];
        for(int i=0;i<R;i++)
        {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for(int j=0;j<C;j++)
            {
                map[i][j] = s.charAt(j);
            }
        }

        alpha[(int)map[0][0]-65] = true;
        sol(0,0,1);

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
    }

}
