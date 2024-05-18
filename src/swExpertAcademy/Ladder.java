package swExpertAcademy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.*;

public class Ladder {
	
	private static final int SIZE = 100;
	private static final int EMPTY = 0;
	private static final int LADDER = 1;
	private static final int TARGET = 2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer input;
		
		
		for(int testcase = 1; testcase <= 10; testcase++) {
			
			input = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(input.nextToken());
			int answer = -1;
		
			int[][] board = new int[SIZE][SIZE];
			int targetX = -1;
			int targetY = -1;
		
		
			for(int i = 0; i < SIZE; i++) {
				input = new StringTokenizer(br.readLine());
				for(int j = 0; j < SIZE; j++) {
					board[i][j] = Integer.parseInt(input.nextToken());
					if(i == SIZE - 1 && board[i][j] == TARGET) {
						targetX = i;
						targetY = j;
					}
				}
			}
			
			answer = move(targetX, targetY, board);
		
			
			bw.write("#" + String.valueOf(T) + " " + String.valueOf(answer));
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	private static int move(int x, int y, int[][] board) {
		
		int[] dx = {0, 0, -1};
		int[] dy = {-1, 1, 0};
		
		
		while(x != 0) {
			for(int i = 0; i < 3; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx < 0 || nx >= board.length || ny < 0 || ny >= board.length || board[nx][ny] == EMPTY) {
					continue;
				}
				x = nx;
				y = ny;
				board[nx][ny] = EMPTY;
			}
		}
		
		return y;
	}
	
}
