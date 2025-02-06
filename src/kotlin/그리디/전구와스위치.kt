import java.io.BufferedWriter
import java.io.OutputStreamWriter

fun main() = with(System.`in`.bufferedReader()) {

    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val n = readLine().toInt()
    // 첫 번째 스위치를 누르지 않음
    val lights = readLine().toCharArray()
    // 첫 번째 스위치를 누름
    val lights2 = lights.copyOf()
    val target = readLine().toCharArray()

    lights2[0] = toggle(lights2[0])
    lights2[1] = toggle(lights2[1])

    val count1 = turnSwitch(lights, target)
    var count2 = turnSwitch(lights2, target)

    if (count2 != -1) count2++
    if (count1 == -1) bw.write("$count2")
    else if (count2 == -1) bw.write("$count1")
    else {
        val cnt = count1.coerceAtMost(count2)
        bw.write("$cnt")
    }
    bw.newLine()
    bw.flush()
    bw.close()
}

private fun toggle(value: Char): Char = if (value == '0') '1' else '0'

private fun isTarget(lights: CharArray, target: CharArray): Boolean{
    for (i in lights.indices) {
        if (lights[i] != target[i]) return false
    }
    return true
}

private fun turnSwitch(lights: CharArray, target: CharArray): Int {
    var cnt = 0

    for (i in 1 until lights.size) {
        if (lights[i - 1] != target[i - 1]) {
            lights[i - 1] = toggle(lights[i - 1])
            lights[i] = toggle(lights[i])

            if (i + 1 < lights.size) {
                lights[i + 1] = toggle(lights[i + 1])
            }
            cnt++
        }
    }

    if (!isTarget(lights, target)) cnt = -1
    return cnt
}