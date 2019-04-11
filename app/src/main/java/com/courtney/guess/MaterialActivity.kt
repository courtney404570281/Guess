package com.courtney.guess

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_material.*
import kotlinx.android.synthetic.main.content_material.*

class MaterialActivity : AppCompatActivity() {

    val secretNumber = SecretNumber()
    val TAG = MaterialActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        Log.d(TAG, "secret: ${secretNumber.secret}")
    }

    fun check(view : View) {
        val n = ed_number.text.toString().toInt()
        println("n: $n")
        Log.d(TAG, "number: $n")

        val diff = secretNumber.validate(n)

        var message = getString(R.string.bingo)
        when {
            diff < 0 -> message = getString(R.string.Higher)
            diff > 0 -> message = getString(R.string.Lower)
        }
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.Message))
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok), null)
            .show()

    }

}
