import java.io.BufferedWriter
import java.io.OutputStreamWriter

// 북 동 남 서
private val dx = intArrayOf(-1, 0, 1, 0)
private val dy = intArrayOf(0, 1, 0, -1)
private val unCleanPoint: Int = 0
private val cleanPoint: Int = 2
private val wall: Int = 1

fun main() = with(System.`in`.bufferedReader()) {

    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (n, m) = readLine().split(" ").map { it.toInt() }

    val board = Array(n) { IntArray(m) }

    val (rx, ry, rd) = readLine().split(" ").map { it.toInt() }

    val robot = Robot(
        x = rx,
        y = ry,
        dir = rd
    )

    for (i in 0 until n) {
        val state = readLine().split(" ").map { it.toInt() }
        for (j in 0 until m) {
            board[i][j] = state[j]
        }
    }

    robot.move(board)

    bw.write("${robot.getCleanCount()}")
    bw.newLine()
    bw.flush()
    bw.close()
}

class Robot(
    private var x: Int,
    private var y: Int,
    private var dir: Int,
    private var cleanCount: Int = 0
) {

    fun move(board: Array<IntArray>) {

        while (true) {
            // 1번 행동
            if (board[x][y] == unCleanPoint) {
                cleanCount++
                board[x][y] = cleanPoint
            }

            if (checkUnCleanPoint(board)) {
                // 3번 행동
                while (true) {

                    dir--
                    if (dir == -1) dir = 3
                    val nx = x + dx[dir]
                    val ny = y + dy[dir]

                    if (board[nx][ny] == unCleanPoint) {
                        x = nx
                        y = ny
                        break
                    }
                }
                continue
            }
            // 2번 행동
            val nd = (dir + 2) % 4
            x += dx[nd]
            y += dy[nd]
            if (x == -1 || y == -1 || x == board.size || y == board[0].size || board[x][y] == wall) {
                break
            }
        }
    }

    fun getCleanCount(): Int {
        return cleanCount
    }

    private fun checkUnCleanPoint(board: Array<IntArray>) : Boolean {
        for (i in 0 until 4) {
            val nd = (dir + i) % 4
            val nx = x + dx[nd]
            val ny = y + dy[nd]
            if (nx < 0 || ny < 0 || nx >= board.size || ny >= board[0].size) continue
            if (board[nx][ny] == unCleanPoint) return true
        }

        return false
    }

}