import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.*


fun main() = with(System.`in`.bufferedReader()) {

    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (n, m) = readLine().split(" ").map { it.toInt() }

    val list = Array<Int>(n) { 0 }

    for (i in 0 until n) {
        list[i] = readLine().toInt()
    }

    Arrays.sort(list)

    var lp = 0
    var rp = 0
    var answer = Int.MAX_VALUE

    while (true) {

        if (lp >= n || rp >= n) break

        val gap = list[rp] - list[lp]
        if (gap < m) {
            rp++
            continue
        }

        if (gap == m) {
            answer = m
            break
        }

        answer = Math.min(answer, gap)
        lp++
    }

    bw.write("$answer")
    bw.newLine()
    bw.flush()
    bw.close()
}