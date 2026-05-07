package io.kess.ecommerce.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.kess.ecommerce.R

class Onboarding1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_delivery)
        val btn = findViewById<Button>(R.id.button)
        val skip = findViewById<TextView>(R.id.skip)
        btn.setOnClickListener {
            val intent = Intent(this, Onboarding2Activity::class.java)
            startActivity(intent)
            finish()
        }
        skip.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}