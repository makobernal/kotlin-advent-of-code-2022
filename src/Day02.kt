typealias Round = Pair<OpponentChoice, ResponseChoice>

fun main() {


    fun transformInput(input: List<String>): List<Round> {
//        println(input)
//        return listOf(
//            Pair(OpponentChoice.from("A"), ResponseChoice.from("Y")),
//            Pair(OpponentChoice.from("B"), ResponseChoice.from("X")),
//            Pair(OpponentChoice.from("C"), ResponseChoice.from("Z")),
//        )
        return input.map { it.split(" ") }.map { Pair(OpponentChoice.from(it[0]), ResponseChoice.from(it[1]))}
    }

    fun shapeScore(choice: ResponseChoice) = when (choice) {
        ResponseChoice.Rock -> 1
        ResponseChoice.Paper -> 2
        ResponseChoice.Scissors -> 3
    }

    val youLose = 0
    val youDraw = 3
    val youWin = 6

    fun outcomeScore(round: Round): Int {
        return when (round.first) {
            OpponentChoice.Rock -> when (round.second) {
                ResponseChoice.Rock -> youDraw
                ResponseChoice.Paper -> youWin
                ResponseChoice.Scissors -> youLose
            }

            OpponentChoice.Paper -> when (round.second) {
                ResponseChoice.Rock -> youLose
                ResponseChoice.Paper -> youDraw
                ResponseChoice.Scissors -> youWin
            }

            OpponentChoice.Scissors -> when (round.second) {
                ResponseChoice.Rock -> youWin
                ResponseChoice.Paper -> youLose
                ResponseChoice.Scissors -> youDraw
            }
        }
    }

    fun part2PredictingScore(round: Round): Int {
        return outcomeScore(round)
    }

    fun singleRoundScorePart1(round: Round): Int = outcomeScore(round) + shapeScore(round.second)
    fun singleRoundScorePart2(round: Round): Int = part2PredictingScore(round) + shapeScore(round.second) // not really, shapescore is wrong

    fun part1(input: List<String>): Int {
        val transformed = transformInput(input)
        val allScores = transformed.map { singleRoundScorePart1(it) }
        return allScores.sum()
    }

    fun part2(input: List<String>): Int {
        val transformed = transformInput(input)
        val allScores = transformed.map { singleRoundScorePart2(it) }
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    val testResult = part1(testInput)
    val testResult2 = part2(testInput)
    check(testResult == 15) { "wrong input loco $testResult is not 15" }
    check(testResult2 == 12)

    val input = readInput("Day02")
    val result1 = part1(input)
    val result2 = part2(input)
    println("solution for your input, part 1 = $result1")
    println("solution for your input, part 2 = $result2")
}

enum class OpponentChoice(val code: String) {
    Rock("A"),
    Paper("B"),
    Scissors("C");

    companion object {
        infix fun from(value: String): OpponentChoice = OpponentChoice.values().firstOrNull { it.code == value }!!
    }
}

enum class ResponseChoice(val code: String) {
    Rock("X"),
    Paper("Y"),
    Scissors("Z");

    companion object {
        infix fun from(value: String): ResponseChoice = ResponseChoice.values().firstOrNull { it.code == value }!!
    }
}
