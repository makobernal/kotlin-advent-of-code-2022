fun main() {


    fun part1(input: List<String>): String {
        return "CMD"
    }


    fun part2(input: List<String>): String {
        return "perhapsAString"
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    val testResult = part1(testInput)
    val testResult2 = part2(testInput)
    check(testResult == "CMZ") { "wrong input $testResult is not CMZ" }
//    check(testResult2 == 4) { "wrong input $testResult is not 4" }

    val input = readInput("Day05")
    val result1 = part1(input)
    val result2 = part2(input)
    println("solution for your input, part 1 = $result1")
    println("solution for your input, part 2 = $result2")
}

