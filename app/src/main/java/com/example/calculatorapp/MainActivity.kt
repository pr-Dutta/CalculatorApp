package com.example.calculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatorapp.ui.theme.CalculatorAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Calculator()
                }
            }
        }
    }
}

@Composable
fun Calculator() {

    val calculatorLogic = CalculatorLogic()

    // State management
    var displayNumber by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("0") }

    Column(
        modifier =Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(text = displayNumber,
                style = TextStyle(fontSize = 55.sp),
                maxLines = 1)       // It makes the text field to take only one line
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End) {
            Text(text = result,style = TextStyle(fontSize =70.sp),
                maxLines = 1)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            ) {

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    Row {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .fillMaxHeight()
                        ) {
                            /* The combination of wrapContentHeight
                            * and fillMaxWidth allows the text to be
                            *  centered both horizontally and vertically
                            * within its parent container.*/
                            Text(text = "C",
                                style = TextStyle(fontSize = 60.sp),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth()
                                    .padding(20.dp)
                                    .wrapContentHeight()
                                    .background(Color.Gray)
                                    .clickable {
                                        calculatorLogic.sqrt = false
                                        calculatorLogic.sqrtCount = 0
                                        calculatorLogic.countOperand = 0
                                        calculatorLogic.onlyOperator = true
                                        calculatorLogic.equalPressed = false
                                        calculatorLogic.countOperator = 0
                                        calculatorLogic.decimalCount = 0
                                        displayNumber = ""
                                        result = "0"
                                    })
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .fillMaxHeight()
                        ) {
                            Text(text = "\u221A",
                                style = TextStyle(fontSize = 60.sp),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth()
                                    .padding(20.dp)
                                    .wrapContentHeight()
                                    .background(Color.Gray)
                                    .clickable {
                                        calculatorLogic.sqrt = true
                                        calculatorLogic.sqrtCount++
                                        calculatorLogic.countOperator++
                                        displayNumber = calculatorLogic.stringConcatenation(displayNumber,"√")
                                    })
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .fillMaxHeight()
                        ) {
                            Text(text = "%",
                                style = TextStyle(fontSize = 60.sp),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth()
                                    .padding(20.dp)
                                    .wrapContentHeight()
                                    .background(Color.Gray)
                                    .clickable {
                                        calculatorLogic.countOperator++
                                        displayNumber = calculatorLogic.stringConcatenation(displayNumber,"%")
                                    })
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .fillMaxHeight()
                        ) {
                            Text(text = "\u2715",
                                style = TextStyle(fontSize = 60.sp),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth()
                                    .padding(20.dp)
                                    .wrapContentHeight()
                                    .background(Color.Gray)
                                    .clickable {
                                        displayNumber = calculatorLogic.stringCancel(displayNumber)
                                    })
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {

                    Row {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .fillMaxHeight()
                        ) {
                            Text(text = "1",
                                style = TextStyle(fontSize = 60.sp),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth()
                                    .padding(20.dp)
                                    .wrapContentHeight()
                                    .clickable {
                                        calculatorLogic.decimalCount = 0
                                        calculatorLogic.countOperator = 0
                                        calculatorLogic.onlyOperator = false
                                        displayNumber = calculatorLogic.stringConcatenation(displayNumber, "1")
                                    })
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .fillMaxHeight()
                        ) {
                            Text(text = "2",
                                style = TextStyle(fontSize = 60.sp),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth()
                                    .padding(20.dp)
                                    .wrapContentHeight()
                                    .clickable {
                                        calculatorLogic.decimalCount = 0
                                        calculatorLogic.countOperator = 0
                                        calculatorLogic.onlyOperator = false
                                        displayNumber = calculatorLogic.stringConcatenation(displayNumber,"2")
                                    })
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .fillMaxHeight()
                        ) {
                            Text(text = "3",
                                style = TextStyle(fontSize = 60.sp),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth()
                                    .padding(20.dp)
                                    .wrapContentHeight()
                                    .clickable {
                                        calculatorLogic.decimalCount = 0
                                        calculatorLogic.countOperator = 0
                                        calculatorLogic.onlyOperator = false
                                        displayNumber = calculatorLogic.stringConcatenation(displayNumber,"3")
                                    })
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .fillMaxHeight()
                        ) {
                            Text(text = "*",
                                style = TextStyle(fontSize = 60.sp),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth()
                                    .padding(20.dp)
                                    .wrapContentHeight()
                                    .background(Color.Gray)
                                    .clickable {
                                        calculatorLogic.countOperator++
                                        displayNumber = calculatorLogic.stringConcatenation(displayNumber,"*")
                                    })
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    Row {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .fillMaxHeight()
                        ) {
                            Text(text = "4",
                                style = TextStyle(fontSize = 60.sp),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth()
                                    .padding(20.dp)
                                    .wrapContentHeight()
                                    .clickable {
                                        calculatorLogic.decimalCount = 0
                                        calculatorLogic.countOperator = 0
                                        calculatorLogic.onlyOperator = false
                                        displayNumber = calculatorLogic.stringConcatenation(displayNumber,"4")
                                    })
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .fillMaxHeight()
                        ) {
                            Text(text = "5",
                                style = TextStyle(fontSize = 60.sp),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth()
                                    .padding(20.dp)
                                    .wrapContentHeight()
                                    .clickable {
                                        calculatorLogic.decimalCount = 0
                                        calculatorLogic.countOperator = 0
                                        calculatorLogic.onlyOperator = false
                                        displayNumber = calculatorLogic.stringConcatenation(displayNumber,"5")
                                    })
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .fillMaxHeight()
                        ) {
                            Text(text = "6",
                                style = TextStyle(fontSize = 60.sp),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth()
                                    .padding(20.dp)
                                    .wrapContentHeight()
                                    .clickable {
                                        calculatorLogic.decimalCount = 0
                                        calculatorLogic.countOperator = 0
                                        calculatorLogic.onlyOperator = false
                                        displayNumber = calculatorLogic.stringConcatenation(displayNumber,"6")
                                    })
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .fillMaxHeight()
                        ) {
                            Text(text = "-",
                                style = TextStyle(fontSize = 60.sp),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth()
                                    .padding(20.dp)
                                    .wrapContentHeight()
                                    .background(Color.Gray)
                                    .clickable {
                                        calculatorLogic.countOperator++
                                        displayNumber = calculatorLogic.stringConcatenation(displayNumber,"-")
                                    })
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    Row {

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .fillMaxHeight()
                        ) {
                            Text(text = "7",
                                style = TextStyle(fontSize = 60.sp),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth()
                                    .padding(20.dp)
                                    .wrapContentHeight()
                                    .clickable {
                                        calculatorLogic.decimalCount = 0
                                        calculatorLogic.countOperator = 0
                                        calculatorLogic.onlyOperator = false
                                        displayNumber = calculatorLogic.stringConcatenation(displayNumber,"7")
                                    })
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .fillMaxHeight()
                        ) {
                            Text(text = "8",
                                style = TextStyle(fontSize = 60.sp),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth()
                                    .padding(20.dp)
                                    .wrapContentHeight()
                                    .clickable {
                                        calculatorLogic.decimalCount = 0
                                        calculatorLogic.countOperator = 0
                                        calculatorLogic.onlyOperator = false
                                        displayNumber = calculatorLogic.stringConcatenation(displayNumber,"8")
                                    })
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .fillMaxHeight()
                        ) {
                            Text(text = "9",
                                style = TextStyle(fontSize = 60.sp),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth()
                                    .padding(20.dp)
                                    .wrapContentHeight()
                                    .clickable {
                                        calculatorLogic.decimalCount = 0
                                        calculatorLogic.countOperator = 0
                                        calculatorLogic.onlyOperator = false
                                        displayNumber = calculatorLogic.stringConcatenation(displayNumber,"9")
                                    })
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .fillMaxHeight()
                        ) {
                            Text(text = "+",
                                style = TextStyle(fontSize = 60.sp),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth()
                                    .padding(20.dp)
                                    .wrapContentHeight()
                                    .background(Color.Gray)
                                    .clickable {
                                        calculatorLogic.countOperator++
                                        displayNumber = calculatorLogic.stringConcatenation(displayNumber,"+")
                                    })
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    Row {

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .fillMaxHeight()
                        ) {
                            Text(text = "0",
                                style = TextStyle(fontSize = 60.sp),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth()
                                    .padding(20.dp)
                                    .wrapContentHeight()
                                    .clickable {
                                        calculatorLogic.decimalCount = 0
                                        calculatorLogic.countOperator = 0
                                        calculatorLogic.onlyOperator = false
                                        displayNumber = calculatorLogic.stringConcatenation(displayNumber,"0")
                                    })
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .fillMaxHeight()
                        ) {
                            Text(text = ".",
                                style = TextStyle(fontSize = 60.sp),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth()
                                    .padding(20.dp)
                                    .wrapContentHeight()
                                    .clickable {
                                        calculatorLogic.decimalCount++
                                        displayNumber = calculatorLogic.stringConcatenation(displayNumber,".")
                                    })
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .fillMaxHeight()
                        ) {
                            Text(text = "/",
                                style = TextStyle(fontSize = 60.sp),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth()
                                    .padding(20.dp)
                                    .wrapContentHeight()
                                    .background(Color.Gray)
                                    .clickable {
                                        calculatorLogic.equalPressed = true
                                        result = calculatorLogic.separateOperators(displayNumber)
                                    })
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .fillMaxHeight()
                        ) {
                            Text(text = "=",
                                style = TextStyle(fontSize = 60.sp),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth()
                                    .padding(20.dp)
                                    .wrapContentHeight()
                                    .background(Color.Gray)
                                    .clickable {
                                        calculatorLogic.equalPressed = true
                                        result = calculatorLogic.separateOperators(displayNumber)
                                    })
                        }
                    }
                }
            }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    CalculatorAppTheme {
        Calculator()
    }
}