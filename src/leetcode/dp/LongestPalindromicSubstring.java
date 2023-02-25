package leetcode.dp;

// https://leetcode.com/problems/longest-palindromic-substring/description/
public class LongestPalindromicSubstring {

  public String longestPalindrome(String s) {

    if(s.length() == 1) return s;

    String answer = "";

    // Si ~ Sj 까지 부분 string이 palindrome 인지 아닌지 boolean 배열로 확인
    boolean[][] palindrome = new boolean[s.length()][s.length()];

    palindrome[0][0] = true;
    if(s.charAt(0) == s.charAt(1)) palindrome[0][1] = true;
    palindrome[1][1] = true;

    for(int i = 0; i < s.length(); i++) {
      for(int j = 0; j <= i; j++) {

        if(j == i) palindrome[j][i] = true;
        else if(j == i - 1) palindrome[j][i] = (s.charAt(j) == s.charAt(i));
          // sj ~ si 부분 string 확인
        else palindrome[j][i] = (palindrome[j + 1][i - 1] && (s.charAt(j) == s.charAt(i)));
        if(palindrome[j][i] && i - j + 1 > answer.length()) {
          answer = s.substring(j, i + 1);
        }
      }
    }

    return answer;

  }

}

// 점화식
// dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j)
