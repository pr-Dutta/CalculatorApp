package com.example.calculatorapp

//This class file is only for practice kotlin code

fun main() {

    var result = "3-"
    var countOperand = 1

    if ((result.endsWith("+") ||
        result.endsWith("-") ||
        result.endsWith("*") ||
        result.endsWith("/") ||
        result.endsWith("%")) && countOperand == 2) {

        // This colored tex is taken as one Condition

        println( result.substring(0, result.length - 1) )

    }
}