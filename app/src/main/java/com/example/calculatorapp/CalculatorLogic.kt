package com.example.calculatorapp

import kotlin.math.sqrt
class CalculatorLogic {

    internal var countOperator = 0
    internal var countOperand = 0

    // to check "=" is pressed or not
    internal var equalPressed = false

    // Checks only operator is pressed or not, only operator
    // is used to not crash the app if we press the = after operator
    internal var onlyOperator = true
    internal var decimalCount = 0

    // It's used to make our app not to able to enter any other operator after sqrt
    internal var sqrt = false
    internal var sqrtCount = 0

    internal fun stringConcatenation(displayNumber: String, nextChar: String): String {

        var result: String

        if (countOperator <= 1 &&
            !equalPressed &&
            countOperand < 4 &&
            decimalCount <= 2) {
            result = displayNumber + nextChar

            if(nextChar != "+" &&
                nextChar != "-" &&
                nextChar != "*" &&
                nextChar != "/" &&
                nextChar != "%" &&
                nextChar != "√" ) {

                countOperand++
            }

            if (result.startsWith("+") ||
                result.startsWith("*") ||
                result.startsWith("/") ||
                result.startsWith("%") ||
                result.startsWith(".")) {

                return ""
            }

            if ((result.endsWith("+") ||
                        result.endsWith("-") ||
                        result.endsWith("*") ||
                        result.endsWith("/") ||
                        result.endsWith("√") || result.endsWith("%")) && countOperand == 4) {

                return result.substring(0, result.length - 1)
            }

            // This code take cares of:-  to not able to enter any other operator after sqrt
            if ((result.endsWith("+") ||
                        result.endsWith("-") ||
                        result.endsWith("*") ||
                        result.endsWith("/") ||
                        result.endsWith("%")) && sqrt) {

                return displayNumber
            }else if (sqrtCount > 1) {
                return displayNumber
            }

            return result
        }else {
            return displayNumber
        }
    }

    internal fun stringCancel(displayNumber: String): String {

        if ((!displayNumber.endsWith("+") ||
                    !displayNumber.endsWith("-") ||
                    !displayNumber.endsWith("*") ||
                    !displayNumber.endsWith("/") ||
                    !displayNumber.endsWith("%")) && displayNumber.isNotEmpty()) {
            countOperand--
        }

        if (displayNumber.isNotEmpty() && !equalPressed) {
            return displayNumber.substring(0, displayNumber.length - 1)
        }else {
            return displayNumber
        }
    }

    private var elements = listOf<String>()

//    internal fun separateOperators(displayNumber: String): String {
//
//        var result = "0"
//        var isSqrt = false


        if (!onlyOperator) {
            var operator = ""

            if (displayNumber.contains("+")) {
                operator = "+"
            }else if (displayNumber.contains("-")) {
                operator = "-"
            }else if (displayNumber.contains("*")) {
                operator = "*"
            }else if (displayNumber.contains("/")) {
                operator = "/"
            }else if (displayNumber.contains("%")) {
                operator = "%"
            }else if (displayNumber.contains("√")) {
                isSqrt = true
                operator = "√"
            }

            // This if block will only run the the operator in not empty
            if (operator.isNotEmpty() && !isSqrt) {

                elements = displayNumber.split(operator)

                var firstOperand: Float
                var secondOperand: Float

                //Special case for "-" operator
                if (operator == "-" && displayNumber.startsWith("-")) {
                    firstOperand = elements[1].toFloat()
                    secondOperand = elements[2].toFloat()
                }else {
                    firstOperand = elements[0].toFloat()
                    secondOperand = elements[1].toFloat()
                }

                // For calculating the result
                result = calculation(firstOperand, operator, secondOperand, displayNumber)

                return trimZero(result)

            }else if (isSqrt) {

                elements = displayNumber.split("√")
                var operand = elements[1].toDouble()

                return trimZero(sqrt(operand).toString())
            }
        }
        return result
    }

    // Calculation function
    private fun calculation(
        firstNumber: Float,
        operator: String,
        secondNumber: Float,
        displayNumber: String): String
    {
        var result = ""
        if (operator == "+") {
            result = addition(firstNumber, secondNumber).toString()
        }else if (operator == "-") {
            result = subtraction(firstNumber, secondNumber, displayNumber).toString()
        }else if (operator == "*") {
            result = multiplication(firstNumber, secondNumber).toString()
        }else if (operator == "/") {
            result = division(firstNumber, secondNumber).toString()
        }else if (operator == "%") {
            result = modulo(firstNumber, secondNumber).toString()
        }
        return result
    }

    // Function for addition operation
    private fun addition(firstNumber: Float, secondNumber: Float): Float {
        return firstNumber + secondNumber
    }

    // Function for subtraction operation
    private fun subtraction(firstNumber: Float, secondNumber: Float, displayNumber: String): Float {
        var result: Float
        if (displayNumber.startsWith("-")) {
            result = firstNumber + secondNumber
            return ("-$result").toFloat()
        }else {
            return firstNumber - secondNumber
        }
    }

    // Function for multiplication operation
    private fun multiplication(firstNumber: Float, secondNumber: Float): Float {
        return firstNumber * secondNumber
    }

    // Function for division operation
    private fun division(firstNumber: Float, secondNumber: Float): Float {
        return firstNumber / secondNumber
    }

    // Function for modulo operation
    private fun modulo(firstNumber: Float, secondNumber: Float): Float {
        return firstNumber % secondNumber
    }


    private fun trimZero(numWithZero: String): String {

        if (numWithZero.contains(".")) {
            elements = numWithZero.split(".")
            val intPart = elements[0]
            val decimalPart = elements[1]

            if (decimalPart[0].toString() == "0") {
                return intPart
            }else {
                return numWithZero
            }
        }else {
            return numWithZero
        }
    }
}