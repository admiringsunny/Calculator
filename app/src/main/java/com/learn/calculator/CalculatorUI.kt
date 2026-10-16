package com.learn.calculator

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun CalculatorUI(
    modifier: Modifier = Modifier.padding(12.dp), name: String = "CalculatorUI"
) {
    val TAG = "CalculatorUI"
    val modifierBtn = Modifier.fillMaxWidth()
    var answer: MutableState<String> = remember { mutableStateOf("") }
    var queryText: MutableState<String> = remember { mutableStateOf("") }
    Column(
        modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

// total : 8 rows, 4 cols/row
// 1st row : Heading = CalculatorUI
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.05f), // 1/8th-height
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                textAlign = TextAlign.Center
            )
        }
// 2nd row -> answer area
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(
                    color = Color.White, shape = RoundedCornerShape(12.dp)
                )
                .border(
                    width = 1.dp, color = Color.Black, shape = RoundedCornerShape(12.dp)
                ), contentAlignment = Alignment.Center
        ) {
            Text(
                text = answer.value,
                color = Color.Black,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold
            )
        }
// 3rd row -> Query area
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(
                    color = Color.White, shape = RoundedCornerShape(12.dp)
                )
                .border(
                    width = 1.dp, color = Color.Black, shape = RoundedCornerShape(12.dp)
                ), contentAlignment = Alignment.Center
        ) {
            Text(
                text = queryText.value,
                color = Color.Black,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold
            )
        }

// 4th row -> " ⌫ C % / "
        Row(
            modifierBtn.weight(1f), // 1/8th-height,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            CalcButton(symbol = "⌫", queryText = queryText, answer = answer)
            CalcButton(symbol = "C", queryText = queryText, answer = answer)
            CalcButton(symbol = "%", queryText = queryText, answer = answer)
            CalcButton(
                symbol = "/", color = Color(0xFFFF9800), queryText = queryText, answer = answer
            )
        }

// 5th row -> " 7 8 9 * "
        Row(
            modifierBtn.weight(1f), // 1/8th-height,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            CalcButton(symbol = "7", queryText = queryText, answer = answer)
            CalcButton(symbol = "8", queryText = queryText, answer = answer)
            CalcButton(symbol = "9", queryText = queryText, answer = answer)
            CalcButton(
                symbol = "*", color = Color(0xFFFF9800), queryText = queryText, answer = answer
            )
        }

// 6th row -> " 4 5 6 - "
        Row(
            modifierBtn.weight(1f), // 1/8th-height,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            CalcButton(symbol = "4", queryText = queryText, answer = answer)
            CalcButton(symbol = "5", queryText = queryText, answer = answer)
            CalcButton(symbol = "6", queryText = queryText, answer = answer)
            CalcButton(
                symbol = "-", color = Color(0xFFFF9800), queryText = queryText, answer = answer
            )
        }

// 7th row -> " 1 2 3 + "
        Row(
            modifierBtn.weight(1f), // 1/8th-height,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            CalcButton(symbol = "1", queryText = queryText, answer = answer)
            CalcButton(symbol = "2", queryText = queryText, answer = answer)
            CalcButton(symbol = "3", queryText = queryText, answer = answer)
            CalcButton(
                symbol = "+", color = Color(0xFFFF9800), queryText = queryText, answer = answer
            )
        }

// 8th row -> " 0 . = "
        Row(
            modifierBtn.weight(1f), // 1/8th-height,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            CalcButton(symbol = "0", btnWeight = 2f, queryText = queryText, answer = answer)
            CalcButton(symbol = ".", queryText = queryText, answer = answer)
            CalcButton(
                symbol = "=", color = Color(0xFFFF9800), queryText = queryText, answer = answer
            )
        }
    }
}

@Composable
private fun RowScope.CalcButton(
    symbol: String,
    color: Color = Color.DarkGray,
    btnWeight: Float = 1f,
    queryText: MutableState<String>,
    answer: MutableState<String>
) {
    Box(
        modifier = Modifier
            .weight(btnWeight)
            .fillMaxHeight()
            .background(
                color, shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp, color = Color.Black, shape = RoundedCornerShape(12.dp)
            )
            .clickable {
                updateQueryText(symbol, queryText, answer = answer)
            }, contentAlignment = Alignment.Center
    ) {
        Text(
            text = symbol, color = Color.White, fontSize = 50.sp, fontWeight = FontWeight.Bold
        )
    }
}
