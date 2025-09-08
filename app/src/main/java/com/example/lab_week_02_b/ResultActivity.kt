package com.example.lab_week_02_b

import android.app.Activity
import android.content.Intent
// import android.graphics.Color // No longer needed directly if using KTX
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.toColorInt // Import for String.toColorInt()

class ResultActivity : AppCompatActivity() {

    companion object {
        const val COLOR_KEY = "COLOR_KEY"
        const val ERROR_KEY = "ERROR_KEY"
        private const val TAG = "ResultActivity" // For logging
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val colorCode = intent?.getStringExtra(COLOR_KEY).orEmpty()
        val backgroundScreen = findViewById<ConstraintLayout>(R.id.background_screen)
        val resultMessage = findViewById<TextView>(R.id.color_code_result_message)
        val backButton = findViewById<Button>(R.id.back_button)

        if (colorCode.isNotEmpty()) {
            try {
                // Apply background color using KTX extension
                backgroundScreen.setBackgroundColor("#$colorCode".toColorInt())

                // Show success message
                resultMessage.text = getString(
                    R.string.color_code_result_message,
                    colorCode.uppercase()
                )
            } catch (_: IllegalArgumentException) { // Changed "ex" to "_" as it's not used
                // Log the error for debugging purposes (optional but good practice)
                // Log.e(TAG, "Invalid color code format received: #$colorCode")

                // Send error back to MainActivity
                setResult(
                    RESULT_OK, // Removed redundant Activity qualifier
                    Intent().apply { putExtra(ERROR_KEY, true) }
                )
                finish()
            }
        }

        // Back button → return to MainActivity
        backButton.setOnClickListener { finish() }
    }
}