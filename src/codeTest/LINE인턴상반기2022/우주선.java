package codeTest.LINE인턴상반기2022;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class 우주선 {

    private static int answer;

    private static int[] distanceList, powerList;

    private static void calcTime(List<Integer> fuel) {
        int maxTime = 0;

        for(int i = 0; i < fuel.size(); i++) {

            int area = powerList[i] * fuel.get(i) * fuel.get(i) / 2;
            int time = fuel.get(i);

            while(true) {
                if(area >= distanceList[i]) break;
                area += (powerList[i] * fuel.get(i));
                time++;
            }
            maxTime = max(maxTime, time);
        }
        answer = min(answer, maxTime);
    }

    private static void combination(int cnt, int maxFuel, int powerSize, List<Integer> arr) {

        if(arr.size() > powerSize) return;

        if(cnt == maxFuel && arr.size() == powerSize) {
            calcTime(arr);
            return;
        }

        for(int i = 1; i <= maxFuel - cnt; i++) {
            arr.add(i);
            combination(cnt + i, maxFuel, powerSize, arr);
            arr.remove(arr.size() - 1);
        }
    }

    public static int solution(int fuel, int[] powers, int[] distances) {

        answer = Integer.MAX_VALUE;

        powerList = powers;
        distanceList = distances;

        combination(0, fuel, powers.length, new ArrayList<>());

        return answer;
    }

    public static void main(String[] args) {

//        int[] power = {40, 30, 20, 10};
        int[] power = {20, 30};
//        int[] distance = {1000, 2000, 3000, 4000};
        int[] distance = {750, 675};

        System.out.println(solution(8, power, distance));
    }
}
