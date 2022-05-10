package programmers.kakao;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.*;

public class 뉴스클러스터링 {

    static int sum = 0;
    static int same = 0;

    public int solution(String str1, String str2) {
        int answer = 0;

        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();

        storeMap(str1, map1);
        storeMap(str2, map2);

        map1.forEach((str,index) -> {
            System.out.println(str + " " + index);
        });

        System.out.println("---");

        map2.forEach((str,index) -> {
            System.out.println(str + " " + index);
        });

        getCount(map1, map2);

        if(sum == 0 && same == 0) answer = 65536;

        else answer = (int) floor((double) same * 65536 / sum);

        return answer;
    }

    private void getCount(Map<String, Integer> map1, Map<String, Integer> map2) {
        map1.forEach((str, cnt) -> {
            if(map2.containsKey(str)) {
                Integer cmp = map2.get(str);
                same += min(cnt, cmp);
                sum += max(cnt, cmp);
            } else {
                sum += cnt;
            }
        });
        map2.forEach((str, cnt) -> {
            if(!map1.containsKey(str)) {
                sum += cnt;
            }
        });
    }

    private void storeMap(String str, Map<String, Integer> map) {
        for(int i = 1; i < str.length(); i++) {
            String cut = str.substring(i - 1, i + 1);
            char c = cut.charAt(0);
            char c1 = cut.charAt(1);

            if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z')) {
                c = Character.toUpperCase(c);
            } else continue;
            if (('a' <= c1 && c1 <= 'z') || ('A' <= c1 && c1 <= 'Z')) {
                c1 = Character.toUpperCase(c1);
            } else continue;

            cut = String.valueOf(c) + c1;

            if(map.containsKey(cut)) {
                Integer integer = map.get(cut);
                map.replace(cut, integer + 1);
            } else {
                map.put(cut, 1);
            }
        }
    }

    public static void main(String[] args) {

    }
}
