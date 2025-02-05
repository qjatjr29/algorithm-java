import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.Queue

private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)

fun main() = with(System.`in`.bufferedReader()) {

    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var year = 0

    val (x, y) = readLine().split(" ").map { it.toInt() }
    val ice = Array(x) { IntArray(y) }
    val iceQueue = ArrayDeque<Pair<Int, Int>>()

    for (i in 0 until x) {
        val info = readLine().split(" ").map { it.toInt() }
        for (j in 0 until y) {
            ice[i][j] = info[j]
            if (ice[i][j] != 0) {
                iceQueue.add(Pair(i, j))
            }
        }
    }

    val answer = melt(ice, iceQueue)
    bw.write("$answer")
    bw.newLine()
    bw.flush()
    bw.close()
}


private fun melt(ice: Array<IntArray>, queue: ArrayDeque<Pair<Int, Int>>): Int {

    var year = 0

    while(queue.isNotEmpty()) {

        val meltIce = ArrayDeque<Triple<Int, Int, Int>>()

        if (check(ice, queue)) {
            return year
        }

        while(queue.isNotEmpty()) {

            val (x, y) = queue.removeFirst()
            var count = 0
            for (i in 0 until 4) {
                val nx = x + dx[i]
                val ny = y + dy[i]

                if (nx < 0 || nx >= ice.size || ny < 0 || ny >= ice[0].size) {
                    continue
                }

                if (ice[nx][ny] == 0) {
                    count++
                }
            }
            meltIce.add(Triple(x, y, count))
        }

        while(meltIce.isNotEmpty()) {
            val (x, y, count) = meltIce.removeFirst()
            if (ice[x][y] - count > 0) {
                ice[x][y] = ice[x][y] - count
                queue.add(Pair(x, y))
            }
            else {
                ice[x][y] = 0
            }
        }

        year++
    }

    return 0
}

private fun check(ice: Array<IntArray>, queue: ArrayDeque<Pair<Int, Int>>): Boolean {

    val x = ice.size
    val y = ice[0].size

    val visited = Array(x) { BooleanArray(y) }
    var count = 0

    for (i in 0 until x) {
        for (j in 0 until y) {
            if (ice[i][j] != 0 && !visited[i][j]) {
                count++
                if (count == 2) return true
                visited[i][j] = true
                checkIce(i, j, ice, visited)
            }
        }
    }

    return false
}

private fun checkIce(sx: Int, sy: Int, ice: Array<IntArray>, visited: Array<BooleanArray>) {

    val queue = ArrayDeque<Pair<Int, Int>>()
    queue.add(Pair(sx, sy))

    while(queue.isNotEmpty()) {
        val (x, y) = queue.removeFirst()

        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            if (nx < 0 || nx >= ice.size || ny < 0 || ny >= ice[0].size || visited[nx][ny]) {
                continue
            }

            if (ice[nx][ny] != 0) {
                visited[nx][ny] = true
                queue.add(Pair(nx, ny))
            }
        }
    }

}