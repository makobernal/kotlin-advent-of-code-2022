fun findIndex(list: List<String>, condition: (String) -> Boolean): Int {
    for (i in list.indices) {
        if (condition(list[i])) {
            return i
        }
    }
    return -1
}

fun main() {


    fun solutioner(input: List<String>, windowSize: Int): Int {
        val indexWhereThisHappens = findIndex(
            input[0].windowed(windowSize)
        ) {
            it.toSet().size == it.length
        }
        return indexWhereThisHappens + windowSize
    }

    fun part1(input: List<String>): Int {
        return solutioner(input, 4)
    }


    fun part2(input: List<String>): Int {
        return solutioner(input, 14)
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    val testResult = part1(testInput)
    val testResult2 = part2(testInput)
    check(testResult == 7) { "wrong input $testResult is not 7" }
    check(testResult2 == 19) { "wrong input $testResult is not XXX" }

    val input = readInput("Day06")
    val result1 = part1(input)
    val result2 = part2(input)
    println("solution for your input, part 1 = $result1")
    println("solution for your input, part 2 = $result2")
}


