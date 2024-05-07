package baekJoon.greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 최대페이지수 {

    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int days = Integer.parseInt(input.nextToken());
        int chapter = Integer.parseInt(input.nextToken());
        answer = 0;
        Chapter[] chapters = new Chapter[chapter];

        for(int i = 0; i < chapter; i++) {
            input = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(input.nextToken());
            int page = Integer.parseInt(input.nextToken());
            chapters[i] = new Chapter(day, page);
        }

        Arrays.sort(chapters);
        dfs(0, days, 0, 0, chapters);

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static void dfs(int day, int deadline, int index, int page, Chapter[] chapters) {

        answer = Math.max(answer, page);

        if(index == chapters.length || day == deadline) {
            return;
        }

        Chapter chapter = chapters[index];
        int cDay = chapter.day;
        int cPage = chapter.page;
        // 해당 챕터를 읽는 경우
        if(day + cDay <= deadline) {
            dfs(day + cDay, deadline, index + 1, page + cPage, chapters);
        }
        // 해당 챕터를 읽지 않는 경우
        dfs(day, deadline, index + 1, page, chapters);

    }

    private static class Chapter implements Comparable<Chapter> {
        int day;
        int page;

        public Chapter(int day, int page) {
            this.day = day;
            this.page = page;
        }

        @Override
        public int compareTo(Chapter o) {
           if((o.page / o.day) == (this.page / this.day)) {
               return this.day - o.day;
           }
           return (o.page / o.day) - (this.page / this.day);
        }
    }
}
