fun main() {
    start()
}

fun start() {
    var str = "_________"
    val list = mutableListOf<MutableList<String>>()
    repeat(3){
        val score = str.take(3)

        val row = mutableListOf("${score[0]}", "${score[1]}", "${score[2]}")
        list += row

        if (str.length > 3) {
            str = str.removeRange(0,3)
        }
    }

    showGrid(list)

    var moves = 0
    var winner = "D"
    var currentPlayer = "X"

    loop@while (moves < 9){
        var play = true
        while (play) {
            var y: Int
            var x: Int

            val move = readln().split("\\s".toRegex()).toTypedArray()

            try {
                y = move[0].toInt()

                try {
                    x = move[1].toInt()

                    if (y !in 1..3 || x !in 1..3) {
                        println("Coordinates should be from 1 to 3!")
                    } else {
                        if (list[y - 1][x - 1] == "_") {
                            list[y - 1][x - 1] = currentPlayer

                            currentPlayer = if (currentPlayer == "X") {
                                "O"
                            } else {
                                "X"
                            }

                            moves += 1
                            play = false
                            showGrid(list)

                            winner = checkWinner(list)
                            if (winner == "X" || winner == "O") break@loop
                        } else {
                            println("This cell is occupied! Choose another one!")
                        }
                    }

                } catch (e: NumberFormatException) {
                    println("You should enter numbers!")
                }

            } catch (e: NumberFormatException) {
                println("You should enter numbers!")
            }
        }
    }

    if (winner == "D") {
        println("Draw")
    } else {
        println("$winner wins")
    }
}

fun showGrid(list: MutableList<MutableList<String>>) {
    println("---------")
    for (element in list){
        print("| ")
        for (row in element) {
            print("$row ")
        }
        print("|")
        println()
    }
    println("---------")
}

fun checkWinner(list: MutableList<MutableList<String>>): String {
    var xWin = 0
    var oWin = 0

    // Check rows
    repeat(3){ row ->
        if (list[row].count { it == "X" } == 3){
            xWin += 1
        }
        if (list[row].count { it == "O" } == 3){
            oWin += 1
        }
    }

    // Check columns
    repeat(3){ column ->
        if (list[0][column] == "X" && list[1][column] == "X" && list[2][column] == "X"){
            xWin += 1
        }
        if (list[0][column] == "O" && list[1][column] == "O" && list[2][column] == "O"){
            oWin += 1
        }
    }

    // Check diagonals
    if (list[0][0] == "X" && list[1][1] == "X" && list[2][2] == "X"){
        xWin += 1
    }
    if (list[0][0] == "O" && list[1][1] == "O" && list[2][2] == "O"){
        oWin += 1
    }
    if (list[0][2] == "X" && list[1][1] == "X" && list[2][0] == "X"){
        xWin += 1
    }
    if (list[0][2] == "O" && list[1][1] == "O" && list[2][0] == "O"){
        oWin += 1
    }

    return if (xWin == 1 && oWin == 0){
        "X"
    } else if (xWin == 0 && oWin == 1){
        "O"
    } else {
        "D"
    }

}

fun staticPlay(){
    var str = readln()

    val inputStr = str
    val list = mutableListOf<MutableList<String>>()
    repeat(3){
        val score = str.take(3)

        val row = mutableListOf("${score[0]}", "${score[1]}", "${score[2]}")
        list += row

        if (str.length > 3) {
            str = str.removeRange(0,3)
        }
    }

    println("---------")
    for (element in list){
        print("| ")
        for (row in element) {
            print("$row ")
        }
        print("|")
        println()
    }
    println("---------")

    var xWin = 0
    var oWin = 0
    var state = 0

    // Check rows
    repeat(3){ row ->
        if (list[row].count { it == "X" } == 3){
            xWin += 1
        }
        if (list[row].count { it == "O" } == 3){
            oWin += 1
        }
    }

    // Check columns
    repeat(3){ column ->
        if (list[0][column] == "X" && list[1][column] == "X" && list[2][column] == "X"){
            xWin += 1
        }
        if (list[0][column] == "O" && list[1][column] == "O" && list[2][column] == "O"){
            oWin += 1
        }
    }

    // Check diagonals
    if (list[0][0] == "X" && list[1][1] == "X" && list[2][2] == "X"){
        xWin += 1
    }
    if (list[0][0] == "O" && list[1][1] == "O" && list[2][2] == "O"){
        oWin += 1
    }
    if (list[0][2] == "X" && list[1][1] == "X" && list[2][0] == "X"){
        xWin += 1
    }
    if (list[0][2] == "O" && list[1][1] == "O" && list[2][0] == "O"){
        oWin += 1
    }

    val numOfX = inputStr.count { it == 'X' }
    val numOfO = inputStr.count { it == 'O' }

    // Draw
    if (numOfX + numOfO == 9){
        state = 1
    }

    // Impossible
    if (numOfX - numOfO >=2 || numOfO - numOfX >=2 ) {
        state = 2
    }

    if (xWin >= 1 && oWin >= 1){
        println("Impossible")
    } else if (xWin == 1 && oWin == 0){
        println("X wins")
    } else if (xWin == 0 && oWin == 1){
        println("O wins")
    } else if (state == 1){
        println("Draw")
    } else if (state == 2){
        println("Impossible")
    }

    if (xWin == 0 && oWin == 0 && state == 0){
        println("Game not finished")
    }
}