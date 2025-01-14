import java.io.BufferedWriter
import java.io.OutputStreamWriter

fun main() = with(System.`in`.bufferedReader()) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val houseCount = readLine().toInt()

    val cost = Array(houseCount + 1) { IntArray(3) }
    var answer = 1000001

    for (i in 1 .. houseCount) {
        val (r, g, b) = readLine().split(" ").map { it.toInt() }

        cost[i][0] = r
        cost[i][1] = g
        cost[i][2] = b
    }

    for (color in 0 until 3) {
        val dp = Array(houseCount + 1) { IntArray(3) }
        for (c in 0 until 3) {
            if (color == c) dp[1][c] = cost[1][c]
            else dp[1][c] = 1000001
        }

        for (i in 2 .. houseCount) {
            dp[i][0] = dp[i - 1][1].coerceAtMost(dp[i - 1][2]) + cost[i][0]
            dp[i][1] = dp[i - 1][0].coerceAtMost(dp[i - 1][2]) + cost[i][1]
            dp[i][2] = dp[i - 1][0].coerceAtMost(dp[i - 1][1]) + cost[i][2]
        }
        for (i in 0 until 3) {
            if (i == color) continue
            answer = answer.coerceAtMost(dp[houseCount][i])
        }
    }

    bw.write("$answer")
    bw.newLine()
    bw.flush()
    bw.close()
}