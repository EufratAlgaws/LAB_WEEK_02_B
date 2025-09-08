package com.example.lab_week_02_b

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    companion object {
        const val COLOR_KEY = "COLOR_KEY"
        const val ERROR_MESSAGE_KEY = "ERROR_MESSAGE"
    }

    // Register activity result launcher
    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val errorMessage = result.data?.getStringExtra(ERROR_MESSAGE_KEY)
                if (errorMessage != null) {
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val submitButton: Button = findViewById(R.id.submit_button)
        val inputField: TextInputEditText = findViewById(R.id.color_code_input_field)

        submitButton.setOnClickListener {
            val colorCode = inputField.text.toString()

            if (colorCode.isEmpty()) {
                Toast.makeText(
                    this,
                    getString(R.string.color_code_input_empty),
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            if (colorCode.length < 6) {
                Toast.makeText(
                    this,
                    getString(R.string.color_code_input_wrong_length),
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra(COLOR_KEY, colorCode)
            resultLauncher.launch(intent)
        }
    }
}