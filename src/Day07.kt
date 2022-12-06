

fun main() {

    fun part1(input: List<String>): Int {
        return 1
    }

    fun part2(input: List<String>): Int {
        return 2
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    val testResult = part1(testInput)
    val testResult2 = part2(testInput)
    check(testResult == 7) { "wrong solution for part 1, $testResult is not 7" }
    check(testResult2 == 19) { "wrong solution for part 2, $testResult is not 19" }

    val input = readInput("Day07")
    val result1 = part1(input)
    val result2 = part2(input)
    println("solution for your input, part 1 = $result1")
    println("solution for your input, part 2 = $result2")
}


