import java.io.BufferedWriter
import java.io.OutputStreamWriter

fun main() = with(System.`in`.bufferedReader()) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (n, k) = readLine().split(" ").map { it.toInt() }
    val arr = readLine().toCharArray()
    val visited = BooleanArray(n)

    var answer = 0

    for (i in 0 until n) {
        if (arr[i] == 'P') {
           for (j in -k .. k) {
               val idx = i + j
               if (idx >= n) break
               if (idx < 0) continue

               if (arr[idx] == 'H' && !visited[idx]) {
                   visited[idx] = true
                   answer++
                   break
               }
           }
        }
    }

    bw.write("$answer")
    bw.newLine()
    bw.flush()
    bw.close()
}