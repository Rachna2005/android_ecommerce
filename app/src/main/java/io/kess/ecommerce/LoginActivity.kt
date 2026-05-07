package io.kess.ecommerce

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        val signIn = findViewById<Button>(R.id.btnSignIn)
        signIn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val signUp = findViewById<TextView>(R.id.signUp)
        signUp.setOnClickListener {
            val Intent = Intent(this, SignUpActivity::class.java)
            startActivity(Intent)
        }
    }
}