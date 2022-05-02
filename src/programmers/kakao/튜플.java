package programmers.kakao;

import java.util.*;

public class 튜플 {
    public static int[] solution(String s) {
        int[] answer;
        s = s.replaceAll("[\\{, \\}]", " ");
        String[] str = s.split("[ ]+");
        Map<String, Integer> hashMap = new HashMap<>();

        for(int i = 0; i < str.length; i++) {
            if(str[i].equals("")) continue;
            if(hashMap.containsKey(str[i])) {
                hashMap.replace(str[i], hashMap.get(str[i]) + 1);
            }
            else hashMap.put(str[i], 1);
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(hashMap.entrySet());

        Collections.sort(list, (o1, o2) -> {return o2.getValue().compareTo(o1.getValue());});

        answer = new int[list.size()];

        for(int i = 0; i < answer.length; i++) {
            answer[i] = Integer.parseInt(list.get(i).getKey());
        }

        return answer;
    }
    public static void main(String[] args) {
        String[] s = {"{{2},{2,1},{2,1,3},{2,1,3,4}}",
                "{{1,2,3},{2,1},{1,2,4,3},{2}}",
                "{{20,111},{111}}",
                "{{123}}",
                "{{4,2,3},{3},{2,3,4,1},{2,3}}"
        };
        for (String s1 : s) {
            int[] answer = solution(s1);
            for (int i : answer) {
                System.out.print(i+ " ");
            }
            System.out.println();
        }
    }
}
