package baekJoon.Implementation;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.abs;

public class 다각형의면적 {

    private static class Position {
        long x, y;
        Position(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    private static Position[] positions;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        positions = new Position[N + 1];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            positions[i] = new Position(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }
        positions[N] = new Position(positions[0].x, positions[0].y);

        long answer;
        long plus = 0;
        long minus = 0;
        for(int i = 0; i < N; i++) {
            plus += positions[i].x * positions[i + 1].y;
            minus += positions[i].y * positions[i + 1].x;
        }
        answer = abs(plus - minus);
        bw.write(String.format("%.1f", answer / 2.0));
        bw.newLine();
        bw.flush();
        bw.close();

    }

}
