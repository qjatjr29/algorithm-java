package codeTest.NC2022채용연계형;

import java.util.regex.Pattern;

public class 생년월일 {

    private static boolean firstCondition(String birthDay) {
//        String pattern = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$";
        String pattern = "^((19|20)\\d\\d)?([- /.])?(0[1-9]|1[012])([- /.])?(0[1-9]|[12][0-9]|3[01])$";

        if(birthDay.length() != 10) return false;
        if(!Pattern.matches(pattern, birthDay)) return false;
        return true;
    }

    private static boolean secondCondition(String year) {
        int y = Integer.parseInt(year);
        return 1900 <= y && y <= 2021;
    }

    private static boolean thirdCondition(String month) {
        int m = Integer.parseInt(month);
        if(1 <= m  && m <= 9) {
            return month.charAt(0) == '0';
        } else return 10 <= m && m <= 12;
    }

    private static boolean fourthCondition(String year, String month, String day) {
        int y = Integer.parseInt(year);
        int m = Integer.parseInt(month);

        if(m == 2) {
            // 윤년
            if(y % 400 == 0 || (y % 4 == 0 && !(y % 100 == 0))) {
                return dayCondition(29, day);
            } else return dayCondition(28, day);
        } else if(m == 4 || m == 6 || m == 9 || m == 11) {
            return dayCondition(30, day);
        } else {
            return dayCondition(31, day);
        }
    }

    private static boolean dayCondition(int maxDay, String day) {
        int d = Integer.parseInt(day);
        if(maxDay == 29) {
            if(d <= 0 || d > 29) return false;
        } else if(maxDay == 28) {
            if(d <= 0 || d > 28) return false;
        } else if(maxDay == 30) {
            if(d <= 0 || d > 30) return false;
        } else {
            if(d <= 0 || d > 31) return false;
        }

        if(1 <= d && d <= 9) {
            return day.charAt(0) == '0';
        }
        return true;
    }

    public static int solution(String[] birth) {
        int answer = 0;

        for (String birthDay : birth) {
            if (!firstCondition(birthDay)) continue;
            String[] date = birthDay.split("-");

            String year = date[0];
            String month = date[1];
            String day = date[2];

            if (!secondCondition(year)) continue;

            if (!thirdCondition(month)) continue;

            if (!fourthCondition(year, month, day)) continue;

            answer++;
        }

        return answer;
    }


    public static void main(String[] args) {
        String[] birth = {"1899-13-31", "19001231", "2001-09-04", "1900-02-29", "2021-5-31", "1950-11-30", "1996-02-29", "1999-11-31", "2000-02-29"};
        String[] birth2 = {"-2019-12-29-", "1945--10-31", "----------", "20000-123-567"};
        int ans = solution(birth2);
        System.out.println(ans);
    }
}
