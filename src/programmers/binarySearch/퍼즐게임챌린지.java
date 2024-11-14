package programmers.binarySearch;

public class 퍼즐게임챌린지 {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 100000;

        int left = 1;
        int right = 100000;

        while(left <= right) {
            int mid = (left + right) / 2;

            if(isPossible(diffs, times, limit, mid)) {
                answer = mid;
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }

        return answer;
    }

    private boolean isPossible(int[] diffs, int[] times, long limit, int level) {

        long time = 0;
        boolean chk = true;
        for(int i = 0; i < diffs.length; i++) {

            int gap = diffs[i] - level;

            if(gap > 0) {
                if(i == 0) {
                    time += gap * times[i];
                }
                else {
                    time += gap * (times[i] + times[i - 1]) + times[i];
                }
            }
            else time += times[i];

            if(time > limit) {
                chk = false;
                break;
            }
        }
        return chk;
    }
}
