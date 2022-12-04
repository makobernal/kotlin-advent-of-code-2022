fun main() {

    fun parseToRange(z:String) : IntRange = z.substringBefore("-").toInt()..z.substringAfter("-").toInt()

    fun isEitherRangeContainingTheOther(ranges: Pair<IntRange, IntRange>): Boolean =
        ranges.first.toList().containsAll(ranges.second.toList()) ||
                ranges.second.toList().containsAll(ranges.first.toList())

    fun toRangePairs(input: List<String>) = input
        .map { it.split(",") }
        .map { Pair(parseToRange(it[0]), parseToRange(it[1])) }

    fun part1(input: List<String>): Int {
        val fullyContainedRanges = toRangePairs(input)
            .filter { isEitherRangeContainingTheOther(it) }

        return fullyContainedRanges.size
    }

    fun isThereAnyOverlap(pair: Pair<IntRange, IntRange>) : Boolean {
        val totalSize = pair.first.count() + pair.second.count()
        val mergedSize = (pair.first.toList() + pair.second.toList()).toSet().size
        return totalSize != mergedSize
    }

    fun part2(input: List<String>): Int {
        val pairsOverlapping = toRangePairs(input)
            .filter { isThereAnyOverlap(it) }

        return pairsOverlapping.size
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    val testResult = part1(testInput)
    val testResult2 = part2(testInput)
    check(testResult == 2) { "wrong input $testResult is not 2" }
    check(testResult2 == 4) { "wrong input $testResult is not 4" }

    val input = readInput("Day04")
    val result1 = part1(input)
    val result2 = part2(input)
    println("solution for your input, part 1 = $result1")
    println("solution for your input, part 2 = $result2")
}

