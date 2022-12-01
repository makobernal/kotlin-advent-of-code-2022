fun main() {
    fun asListOfListOfInts(input: List<String>): List<List<Int>> {
        val accumlatorList = mutableListOf<MutableList<String>>()
        accumlatorList.add(mutableListOf())
        var currentIndex = 0
        input.forEach {
            if (it == "") {
                currentIndex++
    //                println("currentIndex = ${currentIndex}, it = ${it}, templist = $templist")
                accumlatorList.add(mutableListOf())
            } else {
    //                println("currentIndex = ${currentIndex}, it = ${it}, templist = $templist")
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
        println("sortedList = ${sortedList}")

        val trimmedList = sortedList.take(3)
        println("trimmedList = ${trimmedList}")
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
