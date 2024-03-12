package programmers.kakao;

import java.util.ArrayList;
import java.util.List;

public class n_1카드게임 {

    private static int targetNumber;

    public int solution(int coin, int[] cards) {
        int answer = 0;

        int totalCount = cards.length;
        targetNumber = totalCount + 1;

        List<Integer> hasNumbers = new ArrayList<>();
        List<Integer> addNumbers = new ArrayList<>();

        // 처음 n/3 개의 카드를 뽑아 가져간다.
        for(int i = 0; i < totalCount / 3; i++) {
            int card = cards[i];
            hasNumbers.add(card);
        }

        int index = totalCount / 3;

        while(true) {

            answer++;

            // 모든 카드를 뽑음 = 게임 종료
            if (index >= totalCount) {
                break;
            }

            addNumbers.add(cards[index]);
            addNumbers.add(cards[index + 1]);
            index += 2;

            boolean isCanNextRound = false;

            for (int hasNumber : hasNumbers) {
                if (hasNumbers.contains(targetNumber - hasNumber)) {
                    hasNumbers.remove(Integer.valueOf(hasNumber));
                    hasNumbers.remove(Integer.valueOf(targetNumber - hasNumber));
                    isCanNextRound = true;
                    break;
                }
            }

            // 카드를 하나만 선택하는 경우
            // 기존에 가지고 있던 카드 1장 + 새로 뽑은 카드 1장
            if (!isCanNextRound) {
                if (coin > 0) {
                    for (int hasNumber : hasNumbers) {
                        if (addNumbers.contains(targetNumber - hasNumber)) {
                            hasNumbers.remove(Integer.valueOf(hasNumber));
                            addNumbers.remove(Integer.valueOf(targetNumber - hasNumber));
                            coin--;
                            isCanNextRound = true;
                            break;
                        }
                    }
                }
            }

            // 카드 2장 사용
            // 새로 뽑은 카드들 중에 2장을 사용
            if (!isCanNextRound) {
                if (coin > 1) {
                    for (int addNumber : addNumbers) {
                        if (addNumbers.contains(targetNumber - addNumber)) {
                            addNumbers.remove(Integer.valueOf(addNumber));
                            addNumbers.remove(Integer.valueOf(targetNumber - addNumber));
                            coin -= 2;
                            isCanNextRound = true;
                            break;
                        }
                    }
                }
            }

            if (!isCanNextRound) {
                break;
            }

        }
        return answer;
    }

}
