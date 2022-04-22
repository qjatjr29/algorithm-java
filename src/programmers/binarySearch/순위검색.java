package programmers.binarySearch;

import java.util.*;

import static java.util.Collections.sort;

public class 순위검색 {

    static Map<String,List<Integer>> map = new HashMap<>();
    static int[] answer;
    static void bfs(String str, int depth, String[] info) {
        if(depth == 4) {
            if(map.containsKey(str)) {
                map.get(str).add(Integer.parseInt(info[4]));
            } else {
                List<Integer> scoreList = new ArrayList<>();
                scoreList.add(Integer.parseInt(info[4]));
                map.put(str, scoreList);
            }
            return;
        }

        bfs(str + info[depth], depth + 1, info);
        bfs(str + "-", depth + 1, info);
    }

    static void setup(String[] info) {
        for(int i = 0; i < info.length; i++) {
            bfs("", 0, info[i].split(" "));
        }

        Iterator<String> iterator = map.keySet().iterator();
        while(iterator.hasNext()) {
            String key = iterator.next();
            List<Integer> scores = map.get(key);
            sort(scores);
        }

    }

    static int binarySearch(String str, int score) {
        if(!map.containsKey(str)) return 0;

        List<Integer> scoreList = map.get(str);

        int start = 0;
        int end = scoreList.size() - 1;

        while(start <= end) {
            int mid = (start + end) / 2;
            if(scoreList.get(mid) < score) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return scoreList.size() - start;
    }

    static void sol(String[] query) {
        answer = new int[query.length];
        int idx = 0;
        for (String s : query) {

            String[] temp = s.split(" ");

            String str = "";
            for (int i = 0; i < temp.length - 1; i++) {
                if(temp[i].equals("and")) continue;
                str += temp[i];
            }
            int score = Integer.parseInt(temp[temp.length - 1]);
            answer[idx] = binarySearch(str, score);
            idx++;
        }
    }


    public static int[] solution(String[] info, String[] query) {
        setup(info);
        sol(query);
        return answer;
    }

    public static void main(String[] args) {

    }

}
