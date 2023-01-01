package programmers.kakao;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 후보키 {

    private static int rows;
    private static int columns;
    private static String[][] infos;
    private static List<Set<Integer>> candidateKey;

    public static int solution(String[][] relation) {

        rows = relation.length;
        columns = relation[0].length;
        infos = relation;
        candidateKey = new ArrayList<>();

//        if(relation[0].length == 1) {
//            return isUnique(Set.of('0')) ? 1 : 0;
//        }

        String columnStr = "";
        for(int i = 0; i < relation[0].length; i++) {
            columnStr += i;
        }

//        System.out.println(columnStr);

        for(int i = 1; i < columns + 1; i++) {
           dfs(columnStr, new HashSet<>(), i);
        }

        return candidateKey.size();

    }

    private static void dfs(String columnStr, Set<Integer> set, int count) {

        if(count == 0 && isUnique(set) && isMinimal(set)) {
            candidateKey.add(set);
            return;
        }

        for(int i = 0; i < columnStr.length(); i++) {
            Set<Integer> nextSet = new HashSet<>(set);
            char c = columnStr.charAt(i);
            nextSet.add((int) c - 48);
            dfs(columnStr.substring(i + 1), nextSet, count - 1);
        }

    }

    private static boolean isUnique(Set<Integer> key) {

        Set<String> chkUnique = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        for (String[] row : infos) {
            sb.delete(0, sb.length());
            for (int index :key){
                sb.append(row[index]);
            }
            String cmp = sb.toString();
            if(chkUnique.contains(cmp)) return false;
            else chkUnique.add(cmp);
        }
        return true;
    }

    private static boolean isMinimal(Set<Integer> key) {
        for (Set<Integer> candidate : candidateKey) {
            if(key.containsAll(candidate)) return false;
        }
        return true;
    }

    public static void main(String[] args) {

        String[][] relation = {
            {"100","ryan","music","2"},
            {"200","apeach","math","2"},
            {"300","tube","computer","3"},
            {"400","con","computer","4"},
            {"500","muzi","music","3"},
            {"600","apeach","music","2"}
        };

        int solution = solution(relation);
        System.out.println(solution);
    }
}
