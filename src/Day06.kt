

fun main() {


    fun part1(input: List<String>): Int {
        return 8
    }


    fun part2(input: List<String>): Int {
        return 13
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    val testResult = part1(testInput)
    val testResult2 = part2(testInput)
    check(testResult == 7 ) { "wrong input $testResult is not 7" }
    check(testResult2 == 12) { "wrong input $testResult is not XXX" }

    val input = readInput("Day06")
    val result1 = part1(input)
    val result2 = part2(input)
    println("solution for your input, part 1 = $result1")
    println("solution for your input, part 2 = $result2")
}


