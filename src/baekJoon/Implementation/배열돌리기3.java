package baekJoon.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 배열돌리기3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        int commands = Integer.parseInt(input.nextToken());

        int[][] array = new int[N][M];

        for(int i = 0; i < N; i++) {
            input = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                array[i][j] = Integer.parseInt(input.nextToken());
            }
        }

        input = new StringTokenizer(br.readLine());
        for(int i = 0; i < commands; i++) {
            int command = Integer.parseInt(input.nextToken());

            switch (command) {
                case 1:
                    array = reverseUpDown(array);
                    break;
                case 2:
                    array = reverseLeftRight(array);
                    break;
                case 3:
                    array = rotateRight(array);
                    break;
                case 4:
                    array = rotateLeft(array);
                    break;
                case 5:
                    array = divideAndClockRotate(array);
                    break;
                case 6:
                    array = divideAndCounterClockRotate(array);
                    break;
                default:
                    break;
            }
        }

        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[i].length; j++) {
                bw.write(String.valueOf(array[i][j]) + " ");
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();

    }

    private static int[][] reverseUpDown(int[][] array) {

        int[][] reverse = new int[array.length][array[0].length];

        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[i].length; j++) {
                reverse[i][j] = array[array.length - i - 1][j];
            }
        }

        return reverse;
    }

    private static int[][] reverseLeftRight(int[][] array) {

        int[][] reverse = new int[array.length][array[0].length];

        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[i].length; j++) {
                reverse[i][j] = array[i][array[i].length - j - 1];
            }
        }

        return reverse;
    }

    private static int[][] rotateRight(int[][] array) {

        int row = array.length;
        int col = array[0].length;

        int[][] rotate = new int[col][row];

        for(int i = 0; i < col; i++) {
            for(int j = 0; j < row; j++) {
                rotate[i][j] = array[row - j - 1][i];
            }
        }

        return rotate;
    }

    private static int[][] rotateLeft(int[][] array) {

        int row = array.length;
        int col = array[0].length;

        int[][] rotate = new int[col][row];

        for(int i = 0; i < col; i++) {
            for(int j = 0; j < row; j++) {
                rotate[i][j] = array[j][col - i - 1];
            }
        }

        return rotate;
    }

    private static int[][] divideAndClockRotate(int[][] array) {
        int[][][] subArray = divideArray(array);

        int[][] result = new int[array.length][array[0].length];

        // 1 -> 2
        // 2 -> 3
        // 3 -> 4
        // 4 -> 1
        int halfRow = array.length / 2;
        int halfCol = array[0].length / 2;
        for(int i = 0; i < halfRow; i++) {
            for(int j = 0; j < halfCol; j++) {
                result[i][j] = subArray[3][i][j];
                result[i][j + halfCol] = subArray[0][i][j];
                result[i + halfRow][j + halfCol] = subArray[1][i][j];
                result[i + halfRow][j] = subArray[2][i][j];
            }
        }
        return result;
    }

    private static int[][] divideAndCounterClockRotate(int[][] array) {
        int[][][] subArray = divideArray(array);

        int[][] result = new int[array.length][array[0].length];

        // 1 -> 4
        // 2 -> 1
        // 3 -> 2
        // 4 -> 3
        int halfRow = array.length / 2;
        int halfCol = array[0].length / 2;
        for(int i = 0; i < halfRow; i++) {
            for(int j = 0; j < halfCol; j++) {
                result[i][j] = subArray[1][i][j];
                result[i][j + halfCol] = subArray[2][i][j];
                result[i + halfRow][j + halfCol] = subArray[3][i][j];
                result[i + halfRow][j] = subArray[0][i][j];
            }
        }
        return result;
    }

    private static int[][][] divideArray(int[][] array) {

        int n = array.length;
        int m = array[0].length;
        int halfN = n / 2;
        int halfM = m / 2;

        // 부분 배열 초기화
        int[][][] subArrays = new int[4][halfN][halfM];

        // 4개의 부분 배열로 나누기
        for (int i = 0; i < halfN; i++) {
            for (int j = 0; j < halfM; j++) {
                subArrays[0][i][j] = array[i][j];
                subArrays[1][i][j] = array[i][j + halfM];
                subArrays[2][i][j] = array[i + halfN][j + halfM];
                subArrays[3][i][j] = array[i + halfN][j];
            }
        }

        return subArrays;
    }

}
