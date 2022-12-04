fun main() {

    fun parseToRange(z:String) : IntRange = z.substringBefore("-").toInt()..z.substringAfter("-").toInt()

    fun isEitherRangeContainingTheOther(ranges: Pair<IntRange, IntRange>) =
        ranges.first.toList().containsAll(ranges.second.toList()) ||
                ranges.second.toList().containsAll(ranges.first.toList())

    fun part1(input: List<String>): Int {
        val ranges = input
            .map { it.split(",") }
            .map { Pair(parseToRange(it[0]), parseToRange(it[1]))}

        val fullyContainedRanges = ranges.filter { isEitherRangeContainingTheOther(it) }

        return fullyContainedRanges.size
    }

    fun part2(input: List<String>): Int {
        return 3
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    val testResult = part1(testInput)
    val testResult2 = part2(testInput)
    check(testResult == 2) { "wrong input $testResult is not 2" }
//    check(testResult2 == 70) { "wrong input $testResult is not whatever they put on the 2nd assignment" }

    val input = readInput("Day04")
    val result1 = part1(input)
    val result2 = part2(input)
    println("solution for your input, part 1 = $result1")
    println("solution for your input, part 2 = $result2")
}

