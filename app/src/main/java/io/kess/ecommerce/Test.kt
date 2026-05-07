package io.kess.ecommerce

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Test : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_checkout_screen)
        val test = FirebaseTest()
        test.testFirebaseConnection()
    }
}