package com.example.database_advanced_ui_ux

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val email = intent.getStringExtra(SignInActivity.Key1)
        val name = intent.getStringExtra(SignInActivity.Key2)
        val id = intent.getStringExtra(SignInActivity.Key3)

        val welcomeText = findViewById<TextView>(R.id.tvWelcome)
        val mailText = findViewById<TextView>(R.id.tvmail)
        val idText = findViewById<TextView>(R.id.tvid)

        welcomeText.text = "Welcome $name"
        mailText.text="Mail :  $email"
        idText.text="Unique Id :  $id"
    }
}