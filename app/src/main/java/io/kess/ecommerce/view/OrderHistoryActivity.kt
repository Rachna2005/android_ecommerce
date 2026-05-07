package io.kess.ecommerce.view

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import io.kess.ecommerce.R

class OrderHistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.order_history)
        findViewById<ImageView>(R.id.back).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}