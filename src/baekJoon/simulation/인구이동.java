package baekJoon.simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 인구이동 {

    private static int minPopulationDifference;
    private static int maxPopulationDifference;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int landSize = Integer.parseInt(input.nextToken());
        minPopulationDifference = Integer.parseInt(input.nextToken());
        maxPopulationDifference = Integer.parseInt(input.nextToken());
        int answer = 0;

        Country[][] countries =  new Country[landSize][landSize];

        for(int i = 0; i < landSize; i++) {
            input = new StringTokenizer(br.readLine());
            for(int j = 0; j < landSize; j++) {
                countries[i][j] = new Country(i, j, Integer.parseInt(input.nextToken()));
            }
        }

        while(true) {

            List<Union> unions = new ArrayList<>();
            boolean[][] visited = new boolean[landSize][landSize];

            for(int i = 0; i < landSize; i++) {
                for(int j = 0; j < landSize; j++) {
                    if(!visited[i][j]) {
                        Union union = checkUnion(i, j, countries, visited);
                        if(union.isUnion()) {
                            unions.add(union);
                        }
                    }
                }
            }

            if(unions.isEmpty()) break;
            for(Union union : unions) {
                union.distributePopulation();
            }

            answer++;
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static Union checkUnion(int x, int y, Country[][] countries, boolean[][] visited) {

        List<Country> unionCountries = new ArrayList<>();
        Queue<Country> queue = new LinkedList<>();
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        unionCountries.add(countries[x][y]);
        queue.add(countries[x][y]);
        visited[x][y] = true;

        while(!queue.isEmpty()) {
            Country country = queue.poll();
            int cx = country.x;
            int cy = country.y;

            for(int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(nx < 0 || nx >= countries.length || ny < 0 || ny >= countries.length) continue;
                if(visited[nx][ny]) continue;

                if(country.isCanShareBorder(countries[nx][ny],
                        minPopulationDifference, maxPopulationDifference)) {
                    queue.add(countries[nx][ny]);
                    visited[nx][ny] = true;
                    unionCountries.add(countries[nx][ny]);
                }
            }

        }
        return new Union(unionCountries);
    }

    private static class Country {
        int x;
        int y;
        int population;

        public Country(int x, int y, int population) {
            this.x = x;
            this.y = y;
            this.population = population;
        }

        public boolean isCanShareBorder(Country other, int minPopulation, int maxPopulation) {

            int populationDifference = Math.abs(other.population - this.population);
            return minPopulation <= populationDifference && populationDifference <= maxPopulation;
        }

        public void reallocationPopulation(int distributedPopulation) {
            this.population = distributedPopulation;
        }
    }

    private static class Union {

        List<Country> countries;

        int totalPopulation;

        public Union(List<Country> countries) {
            this.countries = countries;
            calculatePopulation();
        }

        public void distributePopulation() {
            int distributedPopulation = totalPopulation / countries.size();

            for(Country country : countries) {
                country.reallocationPopulation(distributedPopulation);
            }
        }

        public boolean isUnion() {
            return this.countries.size() > 1;
        }

        private void calculatePopulation() {
            for(Country country : countries) {
                totalPopulation += country.population;
            }
        }
    }
}
