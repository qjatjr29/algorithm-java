import java.io.BufferedWriter
import java.io.OutputStreamWriter

fun main() = with(System.`in`.bufferedReader()) {

    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (t, w) = readLine().split(" ").map { it.toInt() }

    // t초에 w만큼 이동했을 때, i번째 나무에서의 받아먹은 열매 최대 개수
    // 이때, w의 1이 한 번도 움직이지 않은 경우임.
    val dp = Array(t + 1) { Array(w + 2) { IntArray(3) } }
    var answer = 0

    for (i in 1 .. t) {
        val tree = readLine().toInt()
        for (j in 1 .. w + 1) {
            if (tree == 1) {
                // 1. 해당 나무에서 떨어지는 열매를 받는다.
                // (i - 1)시간에 떨어지는 위치에 있었던 경우
                // (i - 1) 시간에 떨어지지 않는 위치에서 이동한 경우
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][2]) + 1

                // 2. 떨어지는 열매를 받지 않는다.
                dp[i][j][2] = Math.max(dp[i - 1][j - 1][1], dp[i - 1][j][2])
            }
            else {
                // 2번 나무에서 떨어질 때, 처음 자두는 1번 나무 밑에 있으므로 1초일때 움직이지 않고서는 열매를 받을 수 없다.
                if (i == 1 && j == 1) continue

                // 1. 떨어지는 열매를 받지 않는다.
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][2])

                // 2. 해당 나무에서 떨어지는 열매를 받는다.
                dp[i][j][2] = Math.max(dp[i - 1][j - 1][1], dp[i - 1][j][2]) + 1
            }
        }
    }

    for (i in 1 .. w + 1) {
        val result = Math.max(dp[t][i][1], dp[t][i][2])
        answer = Math.max(answer, result)
    }

    bw.write("$answer")
    bw.newLine()
    bw.flush()
    bw.close()
}