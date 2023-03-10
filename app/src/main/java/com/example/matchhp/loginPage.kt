package com.example.matchhp

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class loginPage : AppCompatActivity() {

    var database: FirebaseDatabase? = null
    private lateinit var resetButton: Button
    private lateinit var signupButton: Button
    private lateinit var loginButton: Button
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var bgMediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bgMediaPlayer = MediaPlayer.create(this, R.raw.bgmusic)
        bgMediaPlayer.start()

        var username = findViewById<EditText>(R.id.textName)
        var password = findViewById<EditText>(R.id.textPassword)
        database = FirebaseDatabase.getInstance()

        var resetButton = findViewById<Button>(R.id.passResetButton)
        resetButton.setOnClickListener(View.OnClickListener { openResetPage() })

        var loginButton = findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener(View.OnClickListener {
            val username2 = username.getText().toString()
            val password2 = password.getText().toString()
            val mDatabase = FirebaseDatabase.getInstance().reference.child("User")
            mDatabase.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (userSnapshot in dataSnapshot.children) {
                        val username1 = userSnapshot.child("username").getValue(
                            String::class.java
                        )
                        val password1 = userSnapshot.child("password").getValue(
                            String::class.java
                        )
                        if ((username1 == username2) and (password1 == password2)) {
                            val intent = Intent(this@loginPage, oyunSec::class.java)
                            startActivity(intent)
                            break
                        } else {
                            Toast.makeText(
                                this@loginPage,
                                "Incorrect username or password",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {}
            })
        })

        signupButton = findViewById(R.id.signupButton)
        signupButton.setOnClickListener { openRegisterPage() }
    }

    fun openRegisterPage() {
        val intent = Intent(this, signupPage::class.java)
        startActivity(intent)
    }

    fun openResetPage() {
        val intent = Intent(this, resetPage::class.java)
        startActivity(intent)
    }

    fun openOyunSec() {
        val intent = Intent(this, oyunSec::class.java)
        startActivity(intent)
    }
}