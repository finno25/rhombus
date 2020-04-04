package com.aura.myrhombus

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputView.setOnEditorActionListener { v, actionId, event ->
            if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                drawRhombusPattern()
            }
            false
        }
    }

    fun drawRhombusPattern() {
        val inputValueText = inputView.text.toString()
        if (!inputValueText.matches(Regex.fromLiteral("\\d+(?:\\.\\d+)?"))) {
            // find left and right
            val inputValue = inputValueText.toInt()
            inputView.setText("${inputValue}")
            if(inputValue >= 5 && inputValue <= 100) {
                var left = 0
                var right = 0
                val isEven = inputValue % 2 == 0
                val centerRow = inputValue / 2

                var result = ""
                for (i in 0 until inputValue) {
                    if (i == 0) {
                        right = inputValue / 2
                        left = if(isEven) right - 1 else right
                    } else {
                        if ((isEven && i < centerRow) || (!isEven && i < (centerRow + 1))) {
                            left--
                            right++
                        } else if (isEven && i > centerRow || (!isEven && i > centerRow)) {
                            left++
                            right--
                        }
                    }

                    for (j in 0 until inputValue) {
                        if (j == left || j == right) {
                            result += "*"
                        } else {
                            result += " "
                        }
                    }
                    result += "\n"
                }
                resultView.text = result
            } else {
                resultView.text = "Masukan harus hanya angka 5-100"
            }
        } else {
            resultView.text = "Masukan harus hanya angka 5-100"
        }
    }
}
