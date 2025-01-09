import java.io.BufferedWriter
import java.io.OutputStreamWriter

fun main() = with(System.`in`.bufferedReader()) {

    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (n, m, b) = readLine().split(" ").map { it.toInt() }

    val map = Array(n) { IntArray(m) }

    for (i in 0 until n ) {
        val line = readLine().split(" ").map { it.toInt() }
        for (j in 0 until m) {
            map[i][j] = line[j]
        }
    }

    var answer = Pair(Int.MAX_VALUE, 0)
    for (height in 0 until 257) {

        var time = 0
        var blockCount = b
        for (i in 0 until n) {
            for (j in 0 until m) {

                val cHeight = map[i][j]

                if (cHeight < height) {
                    time += height - cHeight
                    blockCount -= height - cHeight
                    continue
                }
                time += (cHeight - height) * 2
                blockCount += cHeight - height
            }
        }
        if (blockCount < 0) continue
        if (time <= answer.first) {
            answer = Pair(time, height)
        }
    }

    bw.write("${answer.first} ${answer.second}")
    bw.newLine()
    bw.flush()
    bw.close()

}