package leetcode.dp;

public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {

        int strLength = s.length();
        int patternLength = p.length();

        boolean[][] matchDp = new boolean[strLength + 1][patternLength + 1];
        matchDp[0][0] = true;

        for(int i = 2; i <= patternLength; i++) {
            if(p.charAt(i - 1) == '*') {
                matchDp[0][i] = matchDp[0][i - 2];
            }
        }

        for(int i = 1; i <= strLength; i++) {
            char str = s.charAt(i - 1);
            for(int j = 1; j <= patternLength; j++) {
                char pattern = p.charAt(j - 1);

                if(str == pattern || pattern == '.') {
                    matchDp[i][j] = matchDp[i - 1][j - 1];
                    continue;
                }

                if(pattern == '*') {
                    // matching을 안하고 넘어가는 경우
                    matchDp[i][j] = matchDp[i][j - 2];

                    // matching을 하는 경우
                    char prePatter = p.charAt(j - 2);
                    if(str == prePatter || prePatter == '.') {
                        matchDp[i][j] = (matchDp[i][j] || matchDp[i - 1][j]);
                    }
                }
            }

        }

        return matchDp[strLength][patternLength];

    }
}
