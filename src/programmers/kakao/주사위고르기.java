package programmers.kakao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 주사위고르기 {
    private static int totalDiceCount;
    private static List<int[]> aDices;
    private static int maxSum;
    private static int[] answer;

    public int[] solution(int[][] dice) {

        totalDiceCount = dice.length;
        maxSum = 0;
        answer = new int[dice.length / 2];
        aDices = new ArrayList<>();

        selectADice(0, 0, new int[totalDiceCount / 2], new boolean[totalDiceCount]);

        for(int[] aDice : aDices) {
            int[] bDice = new int[totalDiceCount / 2];
            boolean[] check = new boolean[totalDiceCount];

            for(int a : aDice) {
                check[a] = true;
            }

            int index = 0;
            for(int i = 0; i < totalDiceCount; i++) {
                if(check[i]) {
                    continue;
                }
                bDice[index] = i;
                index++;
            }

            List<Integer> aSumList = new ArrayList<>();
            List<Integer> bSumList = new ArrayList<>();

            calculateDiceSum(0, aDice, dice, 0, aSumList);
            calculateDiceSum(0, bDice, dice, 0, bSumList);

            Collections.sort(bSumList);

            int winCount = 0;

            for (Integer a : aSumList) {
                int left = 0;
                int right = bSumList.size();

                while (left + 1 < right) {
                    int mid = (left + right) / 2;

                    if (a > bSumList.get(mid)) {
                        left = mid;
                    } else {
                        right = mid;
                    }
                }

                winCount += left;
            }

            if(winCount > maxSum) {
                maxSum = winCount;
                answer = aDice;
            }
        }

        for(int i = 0; i < answer.length; i++) {
            answer[i]++;
        }

        return answer;
    }

    private void selectADice(int depth, int index, int[] dices, boolean[] selected) {
        if (depth == totalDiceCount / 2) {
            aDices.add(dices.clone());
            return;
        }

        for (int i = index; i < totalDiceCount; i++) {
            if (!selected[i]) {
                selected[i] = true;
                dices[depth] = i;
                selectADice(depth + 1, i + 1, dices, selected);
                selected[i] = false;
            }
        }
    }

    private int upperBound(List<Integer> list, int target) {
        int left = 0;
        int right = list.size();

        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private void calculateDiceSum(int index, int[] diceNumber, int[][] dices, int sum, List<Integer> result) {
        if(index == diceNumber.length) {
            result.add(sum);
            return;
        }

        for(int i = 0; i < 6; i++) {
            int nextDice = diceNumber[index];
            int nextDiceValue = dices[nextDice][i];
            calculateDiceSum(index + 1, diceNumber, dices, sum + nextDiceValue, result);
        }
    }
}
