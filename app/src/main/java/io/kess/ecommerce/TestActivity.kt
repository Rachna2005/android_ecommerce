package io.kess.ecommerce

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test)

        val test = FirebaseTest()
        test.testFirebaseConnection()
//        test.testFirebaseAuth()
    }
}