package com.example.calculatorapp

import kotlin.math.sqrt

var countOperator = 0
var countOperand = 0
var equalPressed = false    // Variable to check "=" is pressed or not
var onlyOperator = true     // Checks only operator is pressed or not only operator
// is used to not crass the app if we press the = after operator
var decimalCount = 0
var sqrt = false    // It's used to make our app not to able to enter any other operator
// after sqrt
var sqrtCount = 0

/* The logic part should be in the main function, main function is not used here */
fun main() {
    println("Initially $countOperator")

}

fun stringConcatenation(displayNumber: String, nextChar: String): String { // √55+

    var result = ""

    if (countOperator <= 1 &&
        !equalPressed &&
        countOperand < 4 &&
        decimalCount <= 2) {
        result = displayNumber + nextChar       // √55+
        countOperand++

        // 29-12-2023
        if (result.startsWith("+") ||
            result.startsWith("*") ||
            result.startsWith("/") ||
            result.startsWith("%") ||
            result.startsWith(".")) {

            return ""
        }

        // (28-12-2023)
        if ((result.endsWith("+") ||
            result.endsWith("-") ||
            result.endsWith("*") ||
            result.endsWith("/") ||
            result.endsWith("√") || result.endsWith("%")) && countOperand == 4) {

            return result.substring(0, result.length - 1)
        }

        // this code take cares of not able to enter any other operator after sqrt (01-01-2024)
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

fun stringCancel(displayNumber: String): String {

    if ((!displayNumber.endsWith("+") ||
        !displayNumber.endsWith("-") ||
        !displayNumber.endsWith("*") ||
        !displayNumber.endsWith("/") ||
        !displayNumber.endsWith("%")) && displayNumber.isNotEmpty()) {
        countOperand--
    }

    var result = ""
    if (displayNumber.isNotEmpty() && !equalPressed) {
        return displayNumber.substring(0, displayNumber.length - 1)
    }else {
        return displayNumber
    }
}

// I have to learn List,listOf Must (25-12-2023) - Done
var elements = listOf<String>()

fun separateOperators(displayNumber: String): String { // -

    var result = "0"
    var isSqrt = false

    // If we have not press any operator then do nothing
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
            elements = displayNumber.split(operator)        //

            var firstOperand = 0.0f
            var secondOperand = 0.0f
            if (operator == "-" && displayNumber.startsWith("-")) {  //Special case for "-" operator
                firstOperand = elements[1].toFloat()    // -
                secondOperand = elements[2].toFloat()
            }else {
                firstOperand = elements[0].toFloat()    //
                secondOperand = elements[1].toFloat()   //
            }

            // For Calculating the result
            result = calculation(firstOperand, operator, secondOperand, displayNumber)

            return trimZero(result)

        }else if (isSqrt) {         // 29-12-2023
            elements = displayNumber.split("√")
            var operand = elements[1].toDouble()

            return trimZero(sqrt(operand).toString())
        }
    }
    return result
}

// Calculation function
fun calculation(
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
fun addition(firstNumber: Float, secondNumber: Float): Float {
    return firstNumber + secondNumber
}

// Function for subtraction operation
fun subtraction(firstNumber: Float, secondNumber: Float, displayNumber: String): Float {
    var result = 0.0f
    if (displayNumber.startsWith("-")) {
        result = firstNumber + secondNumber
        return ("-$result").toFloat()
    }else {
        return firstNumber - secondNumber
    }
}

// Function for multiplication operation
fun multiplication(firstNumber: Float, secondNumber: Float): Float {
    return firstNumber * secondNumber
}

// Function for division operation
fun division(firstNumber: Float, secondNumber: Float): Float {
    return firstNumber / secondNumber
}

// Function for modulo operation
fun modulo(firstNumber: Float, secondNumber: Float): Float {
    return firstNumber % secondNumber
}

// Logic for trim the digit after . if 0
fun trimZero(noWithZero: String): String {

    var trim = 0
    if (noWithZero.contains(".")) {
        elements = noWithZero.split(".")
        val intPart = elements[0]
        val decimalPart = elements[1]

        if (decimalPart[0].toString() == "0") {
            return intPart
        }else {
            return noWithZero
        }
    }else {
        return noWithZero
    }
}

/* Today's list of issue to resolve (24-12-2023)
    * 1. Have to implement squire root operation
    * 2. Have to implement Object concept here - (26-12-2023)
    * - leave a comment to ask else i have to implement by myself
    * 3. two operator should not come next to each other */

// I need to implement a logic which will make sure not able to add more than
// two operator - (28-12-2023) And also not able to add operand if two operator
// is added - (28-12-2023)

// Decimal shouldn't come more than one at once - (30-12-2023)

// String concatenation function (26-12-2023)
// -> String isn't concatenating with "-" sine before head
// we take equalPressed variable to counter the problem
// (after the equal pressed user are able to enter digits on to the screen)

/* Now let's make sure that any operator should not come at the beginning
* except "-" (29-12-2023) */

// Now it breaks when we press any operator and then press "=" (27-12-2023)
// -> now we know that why it breaks. - Done (Because in the 0th position
// here in nothing in the list)

/* Most of the functionality is added, Still lot to improve */

// 31-12-2023 -> make your app to work with decimal, - Already Done
// 31-01-2024 -> make sure it can use at least 4 operand - Done

// 01-01-2024 -> If sqrt is pressed there should not be any other operator. - Done
// 01-01-2024 -> No double sqrt should be able to enter. - Done

// 02-01-2024 -> Have to implement the functionality of cross (Symbol) operation.