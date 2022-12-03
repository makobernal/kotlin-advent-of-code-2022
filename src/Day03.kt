
fun main() {

    fun part1(input: List<String>): Int {
        return 2
    }

    fun part2(input: List<String>): Int {
        return 2
    }



    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    val testResult = part1(testInput)
    val testResult2 = part2(testInput)
    check(testResult == 157) { "wrong input loco $testResult is not 157" }
    check(testResult2 == -1) { "wrong input loco $testResult is not X" }

    val input = readInput("Day03")
    val result1 = part1(input)
    val result2 = part2(input)
    println("solution for your input, part 1 = $result1")
    println("solution for your input, part 2 = $result2")
}

