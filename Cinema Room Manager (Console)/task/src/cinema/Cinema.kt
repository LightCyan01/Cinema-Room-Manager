package cinema

import java.lang.IndexOutOfBoundsException

var CURRENT_INCOME = 0
fun show(seats:Int, cinema: MutableList<MutableList<Char>>){
    print("Cinema:\n ")
    for(i in 1.. seats){
        print(" $i")
    }
    for(i in cinema.indices){
        print("\n${i + 1} ${cinema[i].joinToString(" ")}")
    }
    println("")
}

fun buy(rows:Int, seats:Int, cinema: MutableList<MutableList<Char>>){
    var exit = false
    do {
        println("\nEnter a row number:")
        val row_num = readln().toInt()
        println("Enter a seat number in that row:")
        val seat_num = readln().toInt()
        val price = if (rows * seats <= 60) 10 else if (row_num > rows / 2) 8 else 10

        try {
            if (cinema[row_num - 1][seat_num - 1] == 'B') {
                println("\nThat ticket has already been purchased!")
            } else {
                CURRENT_INCOME += price
                println("Ticket price: $${price}")

                cinema[row_num - 1][seat_num - 1] = 'B'
                exit = true
            }
        } catch (e: IndexOutOfBoundsException) {
            println("\nWrong input!")
        }
    }while(!exit)
}

fun statistics(rows: Int, seats: Int, cinema: MutableList<MutableList<Char>>){
    var count = 0
    val first_half = rows / 2
    val sec_half = rows - first_half
    val total_tickets = rows * seats
    var income = 0
    income = if(total_tickets < 60){
        total_tickets * 10
    }
    else{
        (first_half * seats * 10) + (sec_half * seats * 8)
    }

    for(row in cinema){
        for(column in row){
            if(column == 'B'){
                count++
            }
        }
    }
    val percent = ((count.toDouble() * 100) / total_tickets.toDouble())
    val format_percent = "%.2f".format(percent)
    println("Number of purchased tickets: $count")
    println("Percentage: $format_percent%")
    println("Current income: $$CURRENT_INCOME")
    println("total income: $$income")
}
fun main() {

    println("Enter the number of rows:")
    val rows = readln().toInt()

    println("Enter the number of seats in each row:")
    val seats = readln().toInt()

    val cinema = MutableList(rows) { MutableList(seats) { 'S' } }

    var exit = false
    do {
        println("\n1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit")

        when (readln().toInt()) {
            1 -> show(seats, cinema)
            2 -> buy(rows, seats, cinema)
            3 -> statistics(rows, seats, cinema)
            0 -> exit = true
        }
    }while(!exit)
}