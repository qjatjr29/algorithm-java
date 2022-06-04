package baekJoon.Implementation;

import java.io.*;
import java.util.StringTokenizer;

public class 스도쿠 {

    private static int[][] map = new int[9][9];
    private static boolean chk;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        StringTokenizer st;

        for(int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            String inputString = st.nextToken();
            for(int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(inputString.charAt(j)));
            }
        }

        chk = false;
        sol(0, 0);

        for(int i = 0; i < 9; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < 9; j++) {
                sb.append(map[i][j]);
            }
            bw.write(String.valueOf(sb));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }


    private static void sol (int x, int y) {

        if(x == 9) {
            chk = true;
            return;
        }

        if(map[x][y] != 0) {
            if(y == 8) sol(x + 1, 0);
            else sol(x, y + 1);
            return;
        }

        int modX = x % 3;
        int modY = y % 3;

        Boolean[] numbers = new Boolean[10];

        grid(x - modX, y - modY, numbers);
        line(x, y, numbers);

        for(int i = 1; i <= 9; i++) {
            if(numbers[i] == null) {
                map[x][y] = i;
                if(y == 8) sol(x + 1, 0);
                else sol(x, y + 1);
                if(chk) return;
                map[x][y] = 0;
            }
        }

    }

    private static void line(int x, int y, Boolean[] numbers) {
        for(int i = 0; i < 9; i++) {
            numbers[map[x][i]] = true;
            numbers[map[i][y]] = true;
        }
    }

    private static void grid(int x, int y, Boolean[] numbers) {
        for(int i = x; i < x + 3; i++) {
            for(int j = y; j < y + 3; j++) {
                numbers[map[i][j]] = true;
            }
        }
    }

}
