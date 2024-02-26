package leetcode.dp;

public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {

        int s1Length = s1.length();
        int s2Length = s2.length();
        int s3Length = s3.length();

        if(s1Length + s2Length != s3Length) return false;

        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;

        // 초기화 작업
        // 첫번째 string의 char값과 s3의 char이 같은 경우 (2번째 string은 하나도 사용하지 않음)
        for(int i = 1; i <= s1Length; i++) {
            dp[i][0] = (dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1));
        }

        // 두번째 String 초기화
        for(int i = 1; i <= s2Length; i++) {
            dp[0][i] = (dp[0][i - 1] && s2.charAt(i - 1) == s3.charAt(i - 1));
        }

        // match
        for(int i = 1; i <= s1Length; i++) {
            for(int j = 1; j <= s2Length; j++) {
                dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) ||
                        (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }

        return dp[s1.length()][s2.length()];
    }
}
