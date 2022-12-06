typealias ElfStack = ArrayDeque<Char>
typealias Warehouse = List<ElfStack>

data class Command(val quantity: Int, val source: Int, val destination: Int)

fun calculateRealIndex(currentIndex: Int): Int {
    //2,1,1 --> f(1) = 1
    //6,5,2 --> f(2) = f(1) + 4
    //10,9,3 --> f(3) = f(2) + 4
    //14,13,4
    //18,17,5
    //22,21,6
    return if (currentIndex <= 1) {
        1
    } else {
        calculateRealIndex(currentIndex - 1) + 4
    }
}

fun memoizeIndexes(size: Int): List<Pair<Int, Int>> {
    return (1..size).map {
        Pair(it, calculateRealIndex(it))
    }
}

fun extractNumbers(str: String): List<Int> {
    val numbers = mutableListOf<Int>()
    val numberRegex = Regex("\\d+")
    val matches = numberRegex.findAll(str)
    for (match in matches) {
        numbers.add(match.value.toInt())
    }
    return numbers
}

fun extractLettersWithIndices(s: String): Map<Int, Char> {
    val result = mutableMapOf<Int, Char>()

    for (i in s.indices) {
        val c = s[i]
        if (c.isLetter()) {
            result[i] = c
        }
    }

    return result
}

fun transposeIndex(badIndex: Int, indexMap: List<Pair<Int, Int>>): Int {
    return indexMap.find { it.second == badIndex }!!.first
}

fun main() {

    fun buildInput(input: List<String>): Pair<Warehouse, List<Command>> {

        val rawWarehouse = input.subList(0, input.indexOf(""))

        val numberOfElfStacks = extractNumbers(rawWarehouse.last()).last()
        val indexMap = memoizeIndexes(numberOfElfStacks)
        val lettersWithIndices = rawWarehouse.dropLast(1).map { extractLettersWithIndices(it) }


        val emptyWarehouse = (1..numberOfElfStacks).map { ArrayDeque<Char>() }

        //fillWarehouse
        lettersWithIndices.forEach { level ->
            level.forEach { index: Int, content: Char ->
                emptyWarehouse[transposeIndex(index, indexMap) - 1].addLast(content)
            }
        }

        val rawCommands = input.subList((input.indexOf("") + 1), input.size)
        val commands = rawCommands.map { extractNumbers(it) }.map { Command(it[0], it[1], it[2]) }

        return Pair(emptyWarehouse, commands)
    }

    fun applyCommandCrateMover9001(warehouse: List<ArrayDeque<Char>>, command: Command) {
            println("moving ${command.quantity} from ${command.source} to ${command.destination}")
            println("b = $warehouse")
            val tempDeque = ArrayDeque<Char>()
            repeat(command.quantity) {
                tempDeque.addLast(warehouse[command.source-1].removeFirst())
            }
            repeat(command.quantity) {
                warehouse[command.destination-1].addFirst(tempDeque.removeLast())
            }
            println("a = $warehouse")
    }

    fun applyCommandCrateMover9000(warehouse: List<ArrayDeque<Char>>, command: Command) {
        repeat(command.quantity) {
//            println("moving ${warehouse[command.source-1].first()} from ${command.source} to ${command.destination}")
//            println("b = $warehouse")
            warehouse[command.destination-1].addFirst(warehouse[command.source-1].removeFirst())
//            println("a = $warehouse")
        }
    }

    fun orderWarehouse(typedInput: Pair<List<ArrayDeque<Char>>, List<Command>>): Unit {
        typedInput.second.forEach {
            applyCommandCrateMover9000(typedInput.first, it)
        }
    }

    fun orderWarehouse2(typedInput: Pair<List<ArrayDeque<Char>>, List<Command>>): Unit {
        typedInput.second.forEach {
            applyCommandCrateMover9001(typedInput.first, it)
        }
    }

    fun part1(input: List<String>): String {
        val typedInput = buildInput(input)
        orderWarehouse(typedInput)
        val topElements = typedInput.first.map { it.first() }
        return String(topElements.toCharArray())
    }


    fun part2(input: List<String>): String {
        val typedInput = buildInput(input)
        orderWarehouse2(typedInput)
        val topElements = typedInput.first.map { it.first() }
        return String(topElements.toCharArray())
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    val testResult = part1(testInput)
    val testResult2 = part2(testInput)
    check(testResult == "CMZ") { "wrong input $testResult is not CMZ" }
    check(testResult2 == "MCD") { "wrong input $testResult is not MCD" }

    val input = readInput("Day05")
    val result1 = part1(input)
    val result2 = part2(input)
    println("solution for your input, part 1 = $result1")
    println("solution for your input, part 2 = $result2")
}


