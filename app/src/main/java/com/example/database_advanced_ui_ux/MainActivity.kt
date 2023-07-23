package com.example.database_advanced_ui_ux

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userName = findViewById<TextInputEditText>(R.id.name)
        val userMail = findViewById<TextInputEditText>(R.id.mail)
        val userPassword = findViewById<TextInputEditText>(R.id.password)
        val userId = findViewById<TextInputEditText>(R.id.id)
        val signupBtn =  findViewById<Button>(R.id.btnsignup)

        signupBtn.setOnClickListener {

            val name = userName.text.toString()
            val mail = userMail.text.toString()
            val id = userId.text.toString()
            val password = userPassword.text.toString()

            val user = User(name,mail,id,password)
            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(id).setValue(user).addOnSuccessListener {

                userName.text?.clear()
                userMail.text?.clear()
                userPassword.text?.clear()
                userId.text?.clear()

                Toast.makeText(this,"User Registered",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }
        }
        val signinBtn = findViewById<TextView>(R.id.tvsignin)
        signinBtn.setOnClickListener {
            val openSignInActivity = Intent(this,SignInActivity::class.java)
            startActivity(openSignInActivity)
        }
    }
}