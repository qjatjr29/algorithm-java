package swExpertAcademy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Flatten {

	private static final int ROW_LENGTH = 100;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer input;

		for (int testcase = 1; testcase <= 10; testcase++) {

			int answer = 0;

			input = new StringTokenizer(br.readLine());

			int dumpCount = Integer.parseInt(input.nextToken());
			Box[] boxes = new Box[ROW_LENGTH];

			input = new StringTokenizer(br.readLine());
			for(int i = 0; i < ROW_LENGTH; i++) {
				int height = Integer.parseInt(input.nextToken());
				boxes[i] = new Box(i, height);
			}

			Arrays.sort(boxes);

			for(int i = 0; i < dumpCount; i++) {
				Box high = boxes[boxes.length - 1];
				Box low = boxes[0];

				high.height--;
				low.height++;
				Arrays.sort(boxes);
			}

			answer = boxes[boxes.length - 1].height - boxes[0].height;

			bw.write("#" + String.valueOf(testcase) + " " + String.valueOf(answer));
			bw.newLine();
		}

		bw.flush();
		bw.close();
		br.close();

	}

	private static class Box implements Comparable<Box> {
		int id;
		int height;

		public Box(int id, int height) {
			this.id = id;
			this.height = height;
		}

		@Override
		public int compareTo(Box o) {
			return this.height - o.height;
		}
	}
	
}
