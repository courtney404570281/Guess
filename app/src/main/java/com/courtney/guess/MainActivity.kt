package com.courtney.guess

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val secretNumber = SecretNumber()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity", "secret: ${secretNumber.secret}")
    }

    fun check(view : View) {
        val n = ed_number.text.toString().toInt()
        println("n: $n")
        Log.d("MainActivity", "number: $n")

        val diff = secretNumber.validate(n)

        var message = "Bingo!"
        when {
            diff < 0 -> message = "Higher"
            diff > 0 -> message = "Lower"
        }
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
            .setTitle("Message")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()

    }
}
