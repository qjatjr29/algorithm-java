package swExpertAcademy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class View {

	private static final int DIST = 2;
	private static final int MAX_HEIGHT = 255;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer input;

		for(int testcase = 1; testcase <= 10; testcase++) {

			int answer = 0;

			input = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(input.nextToken());
			Building[] buildings = new Building[N];

			input = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				buildings[i] = new Building(Integer.parseInt(input.nextToken()), i);
			}

			for(int i = 2; i < buildings.length - 2; i++) {

				Building cBuilding = buildings[i];
				int count = MAX_HEIGHT;

				for(int j = 1; j <= DIST; j++) {
					Building leftBuildingHeight = buildings[i - j];
					Building rightBuildingHeight = buildings[i + j];

					if(!cBuilding.isBiggerThan(leftBuildingHeight) || !cBuilding.isBiggerThan(rightBuildingHeight)) {
						count = 0;
						break;
					}

					count = Math.min(count, cBuilding.calculateHeightGap(leftBuildingHeight));
					count = Math.min(count, cBuilding.calculateHeightGap(rightBuildingHeight));
				}

				answer += count;
			}

			bw.write("#" + String.valueOf(testcase) + " " + String.valueOf(answer));
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
		
	}

	private static class Building {
		int height;
		int id;

		public Building(int height, int id) {
			this.height = height;
			this.id = id;
		}

		public boolean isBiggerThan(Building o) {
            return this.height > o.height;
        }

		public int calculateHeightGap(Building o) {
			return this.height - o.height;
		}
	}

}
