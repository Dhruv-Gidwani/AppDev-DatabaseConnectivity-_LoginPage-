package com.example.database_advanced_ui_ux

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity() {

    lateinit var databaseReference: DatabaseReference

    companion object{
        const val Key1 = "com.example.database_advanced_ui_ux.SignInActivity.mail"
        const val Key2 = "com.example.database_advanced_ui_ux.SignInActivity.name"
        const val Key3 = "com.example.database_advanced_ui_ux.SignInActivity.id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val signInButton = findViewById<Button>(R.id.btnsignin)
        val id = findViewById<TextInputEditText>(R.id.userId)

        signInButton.setOnClickListener {
            val userId = id.text.toString()
            if(userId.isNotEmpty()){
                readData(userId)
            }else{
                Toast.makeText(this,"Please enter user Id",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(userId: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(userId).get().addOnSuccessListener {
         // check if the user exists or not
        if(it.exists())
        {
            val email = it.child("email").value
            val name = it.child("name").value
            val userId = it.child("id").value

            val intentWelcome = Intent(this,HomeActivity::class.java)
            intentWelcome.putExtra(Key1,email.toString())
            intentWelcome.putExtra(Key2,name.toString())
            intentWelcome.putExtra(Key3,userId.toString())
            startActivity(intentWelcome)
        }
        else{
            Toast.makeText(this,"User does not exist",Toast.LENGTH_SHORT).show()
        }

        }.addOnFailureListener {
            Toast.makeText(this,"failed",Toast.LENGTH_SHORT).show()
        }
    }
}