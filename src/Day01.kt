fun main() {
    fun asListOfListOfInts(input: List<String>): List<List<Int>> {
        val accumlatorList = mutableListOf<MutableList<String>>()
        accumlatorList.add(mutableListOf())
        var currentIndex = 0
        input.forEach {
            if (it == "") {
                currentIndex++
                accumlatorList.add(mutableListOf())
            } else {
                accumlatorList[currentIndex].add(it)
            }
        }
        return accumlatorList.map { it.map { it.toInt() }.toList() }.toList()
    }

    fun part1(input: List<String>): Int {
        return asListOfListOfInts(input).map { it.sum() }.max()
    }

    fun part2(input: List<String>): Int {
        val summedList = asListOfListOfInts(input).map { it.sum() }
        val sortedList = summedList.sortedDescending()
        val trimmedList = sortedList.take(3)
        return trimmedList.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)


    val input = readInput("Day01")
    println("solution for your input, part 1 = ${part1(input)}")
    println("solution for your input, part 2 = ${part2(input)}")
}
