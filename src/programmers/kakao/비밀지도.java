package programmers.kakao;

public class 비밀지도 {

    private static final char EMPTY = '0';
    private static final char WALL = '1';

    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        for(int i = 0; i < n; i++) {
            int num1 = arr1[i];
            int num2 = arr2[i];

            String binaryString1 = Integer.toBinaryString(num1);
            String formattedNumber1 = String.format("%" + n + "s", binaryString1)
                    .replace(" ", "0");

            String binaryString2 = Integer.toBinaryString(num1);
            String formattedNumber2 = String.format("%" + n + "s", binaryString2)
                    .replace(" ", "0");

            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < n; j++) {
                if(formattedNumber1.charAt(j) == EMPTY && formattedNumber2.charAt(j) == EMPTY) {
                    sb.append(" ");
                    continue;
                }
                sb.append("#");
            }
            answer[i] = sb.toString();
        }

        return answer;
    }
}
