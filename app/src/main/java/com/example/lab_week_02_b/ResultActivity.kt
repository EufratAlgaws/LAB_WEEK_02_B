package com.example.lab_week_02_b

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val colorCode = intent.getStringExtra(MainActivity.COLOR_KEY)

        val backgroundScreen = findViewById<ConstraintLayout>(R.id.background_screen)
        val resultMessage = findViewById<TextView>(R.id.color_code_result_message)

        if (colorCode != null) {
            try {
                // Apply background color
                backgroundScreen.setBackgroundColor(Color.parseColor("#$colorCode"))

                // Show success message
                resultMessage.text = getString(
                    R.string.color_code_result_message,
                    colorCode.uppercase()
                )

            } catch (e: IllegalArgumentException) {
                // Invalid color -> send error back to MainActivity
                val errorIntent = Intent()
                errorIntent.putExtra(
                    MainActivity.ERROR_MESSAGE_KEY,
                    "Invalid color code: #$colorCode"
                )
                setResult(RESULT_OK, errorIntent)
                finish()
            }
        }
    }
}
