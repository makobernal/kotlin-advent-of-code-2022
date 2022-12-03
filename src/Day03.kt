fun main() {

    val ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    data class Compartment(val content:String)
    data class Rucksack(val left:Compartment, val right:Compartment)

    fun mapToRucksacks(input: List<String>) =
        input.map { it.chunked(it.length / 2) }.map { Rucksack(Compartment(it[0]), Compartment(it[1])) }
    fun findCommonItem(rucksack: Rucksack) = rucksack.left.content.toList().intersect(rucksack.right.content.toList()).first()
    fun findCharValue(char: Char) : Int = ALPHABET.indexOf(char) + 1

    fun part1(input: List<String>): Int {
        val ruckSacks = mapToRucksacks(input)
        val commonItems = ruckSacks.map { findCommonItem(it) }
        val priorityValues = commonItems.map { findCharValue(it) }
        return priorityValues.sum()
    }

    fun part2(input: List<String>): Int {
        return input.chunked(3)
            .map { it[0].toList().intersect(it[1].toList()).intersect(it[2].toList()).first()}
            .map { findCharValue(it) }
            .sum()
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    val testResult = part1(testInput)
    val testResult2 = part2(testInput)
    check(testResult == 157) { "wrong input loco $testResult is not 157" }
    check(testResult2 == 70) { "wrong input loco $testResult is not 70" }

    val input = readInput("Day03")
    val result1 = part1(input)
    val result2 = part2(input)
    println("solution for your input, part 1 = $result1")
    println("solution for your input, part 2 = $result2")
}

