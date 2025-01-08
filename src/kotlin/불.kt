import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.*

private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)
private var w = 0
private var h = 0
private var answer = 0
private lateinit var map: Array<CharArray>
private lateinit var visited: Array<BooleanArray>
private lateinit var movedQueue: Queue<Position>
private lateinit var fireQueue: Queue<Position>

fun main() = with(System.`in`.bufferedReader()) {

    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val t = readLine().toInt()

    repeat(t) {

        val (width, height) = readLine().split(" ").map { it.toInt() }
        w = width
        h = height
        answer = -1

        map = Array(h) { CharArray(w) }
        visited = Array(h) { BooleanArray(w) }
        movedQueue = ArrayDeque()
        fireQueue = ArrayDeque()

        for (i in 0 until h) {
            val line = readLine()
            for (j in 0 until w) {
                map[i][j] = line[j]
                visited[i][j] = when(map[i][j]) {
                    '.' -> false
                    '@' -> {
                        movedQueue.add(Position(i, j))
                        false
                    }
                    '*' -> {
                        fireQueue.add(Position(i, j))
                        true
                    }
                    else -> true
                }
            }
        }

        solve()

        if (answer == -1) {
            bw.write("IMPOSSIBLE")
        }
        else {
            bw.write("${answer + 1}")
        }

        bw.newLine()
    }
    bw.flush()
    bw.close()
}

private fun solve() {
    var result = 0
    while (true) {
        if (answer != -1 || movedQueue.isEmpty()) {
            break
        }
        moveFire()
        move(result++)
    }
}

private fun moveFire() {

    repeat(fireQueue.size) {
        val (fx, fy) = fireQueue.poll() ?: return

        for (i in 0 until 4) {
            val nx = fx + dx[i]
            val ny = fy + dy[i]

            if (nx in 0 until h && ny in 0 until w && !visited[nx][ny]) {
                visited[nx][ny] = true
                fireQueue.add(Position(nx, ny))
            }
        }
    }
}

private fun move(count: Int) {

    repeat(movedQueue.size) {

        val (cx, cy) = movedQueue.poll() ?: return
        if (cx == 0 || cy == 0 || cx == h - 1 || cy == w - 1) {
            answer = count
            return
        }

        for (i in 0 until 4) {
            val nx = cx + dx[i]
            val ny = cy + dy[i]

            if (nx in 0 until h && ny in 0 until w && !visited[nx][ny]) {
                visited[nx][ny] = true
                movedQueue.add(Position(nx, ny))
            }
        }
    }
}

data class Position(val x: Int, val y: Int)