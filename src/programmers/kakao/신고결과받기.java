package programmers.kakao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 신고결과받기 {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        Map<String, Integer> id = new HashMap<>();
        Map<String, List<String>> reports = new HashMap<>();
        for(int i = 0; i < id_list.length; i++) {
            id.put(id_list[i], i);
            reports.put(id_list[i], new ArrayList<>());
        }
        int[] check;
        for(int i = 0; i < report.length; i++) {
            String[] persons = report[i].split(" ");
            String personA = persons[0];
            String personB = persons[1];

            List<String> list = reports.get(personB);
            if(list.contains(personA)) continue;

            reports.get(personB).add(personA);
        }

        for(int i = 0; i < id_list.length; i++) {
            List<String> list = reports.get(id_list[i]);
            if(list.size() >= k) {
                for(int j = 0; j < list.size(); j++) {
                    String p = list.get(j);
                    answer[id.get(p)]++;
                }
            }
        }
        return answer;
    }
}
