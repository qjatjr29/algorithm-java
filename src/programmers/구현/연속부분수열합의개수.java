package programmers.구현;
import java.util.*;
public class 연속부분수열합의개수 {
    public int solution(int[] elements) {
        int answer = 0;

        int length = elements.length;
        int[] sum = new int[length * 2 + 1];
        Set<Integer> result = new HashSet<>();

        for(int i = 1; i <= length * 2; i++) {
            sum[i] = sum[i - 1] + elements[i % length];
        }

        for(int i = 1; i <= length; i++) {
            for(int j = i; j <= length + i; j++) {
                result.add(sum[j] - sum[j - i]);
            }
        }
        answer = result.size();
        return answer;
    }

}
