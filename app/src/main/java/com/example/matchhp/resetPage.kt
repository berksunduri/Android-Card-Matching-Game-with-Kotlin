package com.example.matchhp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class resetPage : AppCompatActivity() {
    private lateinit var usernameText: EditText
    private lateinit var passwordText: EditText
    private lateinit var resetpassButton: Button
    private lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_page)

        fun openLoginPage() {
            val intent = Intent(this, loginPage::class.java)
            startActivity(intent)
        }

        fun updateData(username: String, password: String) {
            var kullanici: HashMap<String, String> = HashMap<String, String>()
            kullanici["password"] = password
            mDatabase = FirebaseDatabase.getInstance().getReference("User")
            mDatabase.child(username).updateChildren(kullanici as Map<String, String>)
        }

        usernameText = findViewById(R.id.editTextTextPersonName);
        passwordText = findViewById(R.id.editTextTextPassword);


        resetpassButton = findViewById(R.id.resetpassButton);
        resetpassButton.setOnClickListener {
            val username = usernameText.text.toString()
            val password = passwordText.text.toString()
            updateData(username, password)
            openLoginPage()

        }


    }
}
