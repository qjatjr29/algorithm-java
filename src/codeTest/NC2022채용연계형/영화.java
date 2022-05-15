package codeTest.NC2022채용연계형;

import programmers.dp.등굣길;

import java.util.*;


public class 영화 {

    public static String[] solution(String[] movie) {
        String[] ans;

        Map<String, Integer> movieMap = new HashMap<>();

        Arrays.stream(movie)
                .forEach(m ->
                {
                    if(movieMap.containsKey(m)) {
                        movieMap.replace(m, movieMap.get(m) + 1);
                    } else movieMap.put(m, 1);
                });

        List<Map.Entry<String, Integer>> movieList = new LinkedList<>(movieMap.entrySet());

        movieList.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(o1.getValue() > o2.getValue()) return -1;
                else if(o1.getValue().equals(o2.getValue())) {
                    System.out.println(o1.getKey().getClass());
                    return String.valueOf(o1.getKey()).compareTo(String.valueOf(o2.getKey()));
                } else return 1;
            }
        });
        ans = new String[movieList.size()];

        for(int i = 0; i < movieList.size(); i++) {
            ans[i] = movieList.get(i).getKey();
        }

        return ans;
    }




    public static void main(String[] args) {

        String[] movie = {
                "spy", "ray", "spy" , "room", "once", "spy", "once"
        };
        String[] answer = solution(movie);
        for (String s : answer) {
            System.out.println(s);
        }
    }
}
