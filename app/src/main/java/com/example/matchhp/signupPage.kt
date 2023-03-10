package com.example.matchhp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.*

class signupPage : AppCompatActivity() {

    private lateinit var registerButton: Button
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var database: FirebaseDatabase
    private lateinit var mDatabase: DatabaseReference
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_page)

        fun openLoginPage() {
            val intent = Intent(this, loginPage::class.java)
            startActivity(intent)
        }

        usernameEditText = findViewById<EditText>(R.id.usernameText)
        passwordEditText = findViewById<EditText>(R.id.passText)
        registerButton = findViewById<Button>(R.id.registerButton)
        registerButton.setOnClickListener(View.OnClickListener {
            val username: String = usernameEditText.getText().toString()
            val password: String = passwordEditText.getText().toString()
            user = User(username, password)
            database = FirebaseDatabase.getInstance()
            mDatabase = database.getReference("User")
            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(this@signupPage, "Please add some data.", Toast.LENGTH_SHORT).show()
            } else {
                mDatabase.child(username).setValue(user)
                    .addOnCompleteListener(OnCompleteListener<Void?> {
                        usernameEditText.setText("")
                        passwordEditText.setText("")
                        Toast.makeText(this@signupPage, "Successfuly Added", Toast.LENGTH_SHORT)
                            .show()
                        openLoginPage()
                    })
                //addDatatoFirebase(username, password);
            }
        })
    }


    private fun addDatatoFirebase(username: String, password: String) {
        user.setUsername(username)
        user.setPassword(password)
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                mDatabase.setValue(user)
                Toast.makeText(this@signupPage, "data added", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@signupPage, "Fail to add data $error", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}
