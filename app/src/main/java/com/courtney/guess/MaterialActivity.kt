package com.courtney.guess

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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
            AlertDialog.Builder(this)
                .setMessage(getString(R.string.Message))
                .setMessage(getString(R.string.restart))
                .setPositiveButton(getString(R.string.ok)) { dialog, which ->
                    secretNumber.restart()
                    counter.setText(secretNumber.count.toString())
                    ed_number.setText("")
                    Log.d(TAG, "secret: ${secretNumber.secret}")
                }
                .setNeutralButton(getString(R.string.cancel), null)
                .show()
        }

        counter.setText(secretNumber.count.toString())
        Log.d(TAG, "secret: ${secretNumber.secret}")

        val count = getSharedPreferences("guess", Context.MODE_PRIVATE)
            .getInt("REC_COUNTER", -1)
        val nick = getSharedPreferences("guess", Context.MODE_PRIVATE)
            .getString("REC_NICKNAME", null)
        Log.d(TAG, "${count} ${nick}")
    }

    fun check(view : View) {
        val n = ed_number.text.toString().toInt()
        println("n: $n")
        Log.d(TAG, "number: $n")

        val diff = secretNumber.validate(n)

        var message = when {
            diff < 0 -> getString(R.string.Higher)
            diff > 0 -> getString(R.string.Lower)
            diff == 0 && secretNumber.count < 3 -> "${getString(R.string.excellent)} ${secretNumber.secret}"
            else ->  getString(R.string.bingo)
        }

        counter.setText(secretNumber.count.toString())
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.Message))
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok), {dialog, which ->
                if (diff == 0) {
                    val intent = Intent(this, RecordActivity::class.java)
                    intent.putExtra("COUNTER", secretNumber.count)
                    startActivity(intent)
                }
            })
            .show()

    }

}
