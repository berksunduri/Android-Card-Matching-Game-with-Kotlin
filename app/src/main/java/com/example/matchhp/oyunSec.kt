package com.example.matchhp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class oyunSec : AppCompatActivity() {

    private lateinit var button2: Button
    private lateinit var button4: Button
    private lateinit var button6: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oyun_sec)

        button2 = findViewById(R.id.button)
        button4 = findViewById(R.id.button2)
        button6 = findViewById(R.id.button3)

        button2.setOnClickListener { baslat_2() }
        button4.setOnClickListener { baslat_4() }
        button6.setOnClickListener { baslat_6() }
    }

    private fun baslat_6() {
        val intent = Intent(this, game6x6::class.java)
        startActivity(intent)
    }

    private fun baslat_4() {
        val intent = Intent(this, game4x4::class.java)
        startActivity(intent)
    }

    fun baslat_2() {
        val intent = Intent(this, game2x2::class.java)
        startActivity(intent)
    }
}
