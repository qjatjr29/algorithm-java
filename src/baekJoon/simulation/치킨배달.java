package baekJoon.simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 치킨배달 {

    private static final int EMPTY = 0;
    private static final int HOUSE = 1;
    private static final int CHICKEN_STORE = 2;
    private static int answer;
    private static List<House> houses;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        answer = Integer.MAX_VALUE;
        houses = new ArrayList<>();

        int[][] city = new int[N][N];
        List<ChickenStore> chickenStores = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            input = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                city[i][j] = Integer.parseInt(input.nextToken());
                if(city[i][j] == CHICKEN_STORE) {
                    chickenStores.add(new ChickenStore(i, j));
                }
                if(city[i][j] == HOUSE) {
                    houses.add(new House(i, j));
                }
            }
        }

        boolean[] checked = new boolean[chickenStores.size()];
        ChickenStore[] selectedStore = new ChickenStore[M];
        selectStore(0, M, 0, chickenStores, selectedStore, checked);

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static void calculateDistance(ChickenStore[] selectedStores) {

        int distance = 0;

        for (House house : houses) {
            int dist = 987654321;

            for (ChickenStore store : selectedStores) {
                dist = Math.min(dist, Math.abs(store.x - house.x) + Math.abs(store.y - house.y));
            }
            distance += dist;
        }

        answer = Math.min(answer, distance);
    }

    private static void selectStore(int index, int max, int count,
                                    List<ChickenStore> chickenStores, ChickenStore[] selectedStores,
                                    boolean[] checked) {
        if(count == max) {
            calculateDistance(selectedStores);
            return;
        }

        for(int i = index; i < chickenStores.size(); i++) {
            if(checked[i]) continue;
            checked[i] = true;
            selectedStores[count] = chickenStores.get(i);
            selectStore(i + 1, max, count + 1, chickenStores, selectedStores, checked);
            checked[i] = false;
        }

    }

    private static class ChickenStore {
        int x, y;

        public ChickenStore(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class House {
        int x;
        int y;

        public House(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
