package com.learn.calculator

import android.util.Log
import androidx.compose.runtime.MutableState


fun updateQueryText(
    btnValue: String, queryText: MutableState<String>, answer: MutableState<String>
) {
    Log.d("CalculatorUI", "updateQueryText: clicked $btnValue")
    if (queryText.value.isNotEmpty() && queryText.value.startsWith("0")) {
        queryText.value = queryText.value.drop(1)
    }

    when (btnValue) {
        "=" -> {
            answer.value = calculateResult(queryText.value)
            queryText.value = ""
        }

        "⌫" -> {
            queryText.value = queryText.value.dropLast(1)
        }

        "C" -> {
            queryText.value = ""
            answer.value = ""
        }

        "+", "-", "*", "/", "%" -> {
            if (queryText.value.isNotEmpty()) {
                val calculateResult = calculateResult(queryText.value)
                if (queryText.value.last() in "+-*/%") {
                    // replace last operator with new
                    queryText.value = queryText.value.dropLast(1)
                }
                queryText.value = calculateResult + btnValue

                if (queryText.value.endsWith("%")) {
                    answer.value = calculateResult(queryText.value)
                    queryText.value = ""
                }
            }
        }

        "." -> {
            val currentNumber = queryText.value.split("+", "-", "*", "/").last()
            // Prevent multiple decimal points in current Number
            if (!currentNumber.contains(".")) {
                queryText.value += btnValue
            }
        }

        else -> {
            queryText.value += btnValue
        }
    }
    Log.d("CalculatorUI", "updateQueryText: queryText= ${queryText.value}")
}

fun calculateResult(expression: String): String {
    try {
        if (expression.isEmpty())
            return "Error"
        val operatorIndex = expression.indexOfAny(
            charArrayOf('+', '-', '*', '/', '%'),
            startIndex = 1
        )

        if (operatorIndex == -1)
            return expression

        val num1 = expression.substring(0, operatorIndex).toDoubleOrNull() ?: 0.0
        val operator = expression[operatorIndex]
        val num2 = expression.substring(operatorIndex + 1).toDoubleOrNull() ?: 0.0
        val answer = when (operator) {
            '+' -> num1 + num2
            '-' -> num1 - num2
            '*' -> num1 * num2
            '/' -> num1 / num2
            '%' -> num1 / 100
            else -> return "Error"
        }
        if (answer.isFinite())
            return answer.toBigDecimal().stripTrailingZeros().toPlainString()

        return "Error"
    } catch (e: Exception) {
        return "Error"
    }
}