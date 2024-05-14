package swExpertAcademy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 사람네트워크2 {

    private static final int CONNECT = 1;
    private static final int INF = 1000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(input.nextToken());

        for(int testcase = 1; testcase <= T; testcase++) {

            int answer = Integer.MAX_VALUE;

            input = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(input.nextToken());

            int[][] network = new int[size][size];

            for(int i = 0; i < size; i++) {
                for(int j = 0; j < size; j++) {
                    int connect = Integer.parseInt(input.nextToken());
                    if(connect == CONNECT) {
                        network[i][j] = connect;
                        continue;
                    }
                    network[i][j] = INF;
                }
            }

            for(int i = 0; i < size; i++) {
                for(int j = 0; j < size; j++) {
                    for(int z = 0; z < size; z++) {
                        if(i == j) {
                            continue;
                        }
                        network[i][j] = Math.min(network[i][j], network[i][z] + network[z][j]);
                    }
                }
            }

            for(int i = 0; i < size; i++) {
                int result = 0;
                for(int j = 0; j < size; j++) {
                    if(i == j) continue;
                    result += network[i][j];
                }
                answer = Math.min(answer, result);
            }

            bw.write("#" + String.valueOf(testcase) + " " + String.valueOf(answer));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();

    }

}
