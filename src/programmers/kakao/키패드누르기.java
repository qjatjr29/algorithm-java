package programmers.kakao;

import static java.lang.Math.abs;

public class 키패드누르기 {

    public static class Hand {
        int x, y;
        Hand(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
    public static int dist(int x, int y, int distX, int distY) {
        return abs(x - distX) + abs(y - distY);
    }
    public static String solution(int[] numbers, String hand) {
        StringBuilder answer = new StringBuilder();

        // 1 4 7
        // 2 5 8 0
        // 3 6 9

        int[][] map = new int[4][3];
        Hand left = new Hand(3, 0);
        Hand right = new Hand(3, 2);

        for (int number : numbers) {
            if(number == 0) {
                selectHand(hand, answer, left, right, 3 ,1);
            }
            // 왼손이 움직임
            else if(number % 3 == 1) {
                answer.append("L");
                left.setX(number / 3);
                left.setY(0);
            }
            // 오른손이 움직임
            else if(number % 3 == 0) {
                answer.append("R");
                right.setX(number / 3 - 1);
                right.setY(2);
            }
            else {
                selectHand(hand, answer, left, right, number / 3 ,1);
            }
        }
        return answer.toString();
    }

    private static void selectHand(String hand, StringBuilder answer, Hand left, Hand right, int x, int y) {
        int leftDistance = dist(left.x, left.y, x, y);
        int rightDistance = dist(right.x, right.y, x, y);
        if (leftDistance < rightDistance) {
            setHandPosition(answer, "L", left, x, y);
        } else if (leftDistance > rightDistance) {
            setHandPosition(answer, "R", right, x, y);
        } else {
            if(hand.equals("right")) {
                setHandPosition(answer, "R", right, x, y);
            } else {
                setHandPosition(answer, "L", left, x, y);
            }
        }
    }

    private static void setHandPosition(StringBuilder answer, String priority, Hand hand, int x, int y) {
        answer.append(priority);
        hand.setY(y);
        hand.setX(x);
    }

    public static void main(String[] args) {
        int[] numbers = {1,2,3,4,5,6,7,8,9,0};
        String right = solution(numbers, "right");
        System.out.println(right);

    }


}
