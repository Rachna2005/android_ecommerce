package io.kess.ecommerce.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.kess.ecommerce.R

class Onboarding2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_payment)
        val skip = findViewById<TextView>(R.id.skip)
        val btn = findViewById<Button>(R.id.button)
        val listener = View.OnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
        skip.setOnClickListener (listener)
        btn.setOnClickListener (listener)
        val login = findViewById<TextView>(R.id.signUp)
        login.setOnClickListener {
            val intent = Intent(this, LoginActivity:: class.java)
            startActivity(intent)
            finish()
        }
    }
}