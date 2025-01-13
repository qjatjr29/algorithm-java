import java.io.BufferedWriter
import java.io.OutputStreamWriter

fun main() = with(System.`in`.bufferedReader()) {

    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (townCount, roadCount) = readLine().split(" ").map { it.toInt() }

    var answer = 987654321

    val roads = Array(townCount + 1) { IntArray(townCount + 1) }

    for (i in 1 .. townCount) {
        for (j in 1 .. townCount) {
            roads[i][j] = 987654321
        }
    }

    for (i in 0 until roadCount) {
        val (s, e, c) = readLine().split(" ").map { it.toInt() }
        roads[s][e] = c
    }

    for (i in 1 .. townCount) {
        for (j in 1 .. townCount) {
            for(k in 1 .. townCount) {
                roads[i][j] = roads[i][j].coerceAtMost(roads[i][k] + roads[k][j])
            }
        }
    }

    for (i in 1 .. townCount) {
        if (roads[i][i] != 987654321) {
            answer = answer.coerceAtMost(roads[i][i])
        }
    }

    if (answer == 987654321) answer = -1

    bw.write("$answer")
    bw.newLine()
    bw.flush()
    bw.close()
}