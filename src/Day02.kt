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

enum class ExpectedAction(val code: String) {
    Lose("X"),
    Draw("Y"),
    Win("Z");

    companion object {
        infix fun from(value: String): ExpectedAction = ExpectedAction.values().firstOrNull { it.code == value }!!
    }
}

typealias Round = Pair<OpponentChoice, ResponseChoice>
typealias Strategy = Pair<OpponentChoice, ExpectedAction>

fun main() {


    fun inputSplitted(input: List<String>) = input.map { it.split(" ") }

    fun transformInputPart1(input: List<String>): List<Round> {
        return inputSplitted(input).map { Pair(OpponentChoice.from(it[0]), ResponseChoice.from(it[1])) }
    }

    fun transformInputPart2(input: List<String>): List<Strategy> {
        return inputSplitted(input).map { Pair(OpponentChoice.from(it[0]), ExpectedAction.from(it[1])) }
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

    fun singleRoundScorePart1(round: Round): Int = outcomeScore(round) + shapeScore(round.second)

    fun part1(input: List<String>): Int {
        val transformed = transformInputPart1(input)
        val allScores = transformed.map { singleRoundScorePart1(it) }
        return allScores.sum()
    }

    fun playRound(strategy: Pair<OpponentChoice, ExpectedAction>): Pair<OpponentChoice, ResponseChoice> {
        val choice = when (strategy.second) {
            ExpectedAction.Lose -> when (strategy.first) {
                OpponentChoice.Rock -> ResponseChoice.Scissors
                OpponentChoice.Paper -> ResponseChoice.Rock
                OpponentChoice.Scissors -> ResponseChoice.Paper
            }

            ExpectedAction.Draw -> when (strategy.first) {
                OpponentChoice.Rock -> ResponseChoice.Rock
                OpponentChoice.Paper -> ResponseChoice.Paper
                OpponentChoice.Scissors -> ResponseChoice.Scissors
            }

            ExpectedAction.Win -> when (strategy.first) {
                OpponentChoice.Rock -> ResponseChoice.Paper
                OpponentChoice.Paper -> ResponseChoice.Scissors
                OpponentChoice.Scissors -> ResponseChoice.Rock
            }
        }
        return Pair(strategy.first, choice)
    }

    fun part2(input: List<String>): Int {
        val strategies = transformInputPart2(input)
        val rounds = strategies.map { playRound(it) }
        val allScores = rounds.map { singleRoundScorePart1(it) }
        return allScores.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    val testResult = part1(testInput)
    val testResult2 = part2(testInput)
    check(testResult == 15) { "wrong input loco $testResult is not 15" }
    check(testResult2 == 12) { "wrong input loco $testResult is not 12" }

    val input = readInput("Day02")
    val result1 = part1(input)
    val result2 = part2(input)
    println("solution for your input, part 1 = $result1")
    println("solution for your input, part 2 = $result2")
}

