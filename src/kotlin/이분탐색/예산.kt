import java.io.BufferedWriter
import java.io.OutputStreamWriter

private var answer = 0
private var n = 0
private var amount = 0

fun main() = with(System.`in`.bufferedReader()){

    /**
     *     [ 국가 예산 배정 ]
     *     모든 요청이 배정될 수 있는 경우 그대로 배정
     *     그렇지 않는 경우 특정한 정수 상한액 계산 후 그 이상인 예산요청에는 모두 상한액을 배정한다.
     *     상한액 이하의 예산요청에 대해서는 요청한 금액을 그대로 배정한다.
     */

    val bw = BufferedWriter(OutputStreamWriter(System.out))

    n = readLine().toInt()
    val countries = readLine().split(" ").map { it.toInt() }
    amount = readLine().toInt()

    val sum = countries.sum()
    val sortedCountries = countries.sorted()

    if (sum <= amount) {
        answer = sortedCountries.last()
    }
    else {
        binarySearch(1, sortedCountries.last(), sortedCountries)
    }
    bw.write("$answer")
    bw.newLine()
    bw.flush()
    bw.close()
}

private fun binarySearch(left: Int, right: Int, countries: List<Int>) {

    if (left >= right) return

    val cost = (left + right) / 2;

    var usedAmount = 0

    for (country in countries) {

        usedAmount += if (country <= cost) country else cost
    }

    if (usedAmount > amount) {
        binarySearch(left, cost, countries)
    }
    else {
        answer = answer.coerceAtLeast(cost)
        binarySearch(cost + 1, right, countries)
    }
}