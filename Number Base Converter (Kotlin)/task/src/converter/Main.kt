package converter

import kotlin.math.*
import java.math.BigDecimal
import java.math.RoundingMode

fun operation1(number: String, base1: String, base2: String) =
    (number.toBigInteger(base1.toInt())).toString(base2.toInt())

fun operation2(numberStr: String, base1: String, base2: String): String {
    var num10 = BigDecimal(0)
    var number = 0
    for (i in 1..numberStr.length) {
        when {
            numberStr[i - 1].uppercaseChar() in ('A'..'Z') -> number = numberStr[i - 1].uppercaseChar() - 'A' + 10
            else -> number = numberStr[i - 1].toString().toInt()
        }
        num10 += BigDecimal(number * (base1.toDouble()).pow(-i))
    }
    var numFin = ""
    var num3 = num10
    repeat(5) {
        val num4 = (num3 * base2.toBigDecimal())
        var num5 = num4.setScale(0, RoundingMode.DOWN)
        num3 = (num4 - num5)
        val num6 = base2.toInt()
        numFin += (num5.toInt().toString(num6))
    }
    return numFin.substring(0, 5)
}

fun main() {
    while (true) {
        print("Enter two numbers in format: {source base} {target base} (To quit type /exit) ")
        val list1 = readln().split(" ")
        if (list1[0] == "/exit") break
        while (true) {
            print("Enter number in base ${list1[0]} to convert to base ${list1[1]} (To go back type /back) ")
            val list2 = readln().split(".")
            if (list2[0] == "/back") break
            val number1 = operation1(list2[0], list1[0], list1[1])
            var numberFin = number1
            if (list2.size == 2) {
                val number2 = operation2(list2[1], list1[0], list1[1])
                numberFin += ".${number2}"
            }
            println("Conversion result: ${numberFin}")
            println()
        }
    }
}