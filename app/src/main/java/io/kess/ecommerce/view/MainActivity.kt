package io.kess.ecommerce.view

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import io.kess.ecommerce.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        replace(fragment = HomeFragment())
        val homeBtn = findViewById<ImageView>(R.id.homeBtn)
        homeBtn.setOnClickListener {
          replace(fragment = HomeFragment())
        }
        val profileBtn = findViewById<ImageView>(R.id.profileBtn)
        profileBtn.setOnClickListener {
            replace(fragment = ProfileFragment())
        }
        val categoryBtn = findViewById<ImageView>(R.id.categoryBtn)
        categoryBtn.setOnClickListener {
            replace(fragment = CategoryFragment())
        }
        val cartBtn = findViewById<ImageView>(R.id.cartBtn)
        cartBtn.setOnClickListener {
            replace(fragment = CartFragment())
        }
        val onSearch = findViewById<ImageView>(R.id.search)
        onSearch.setOnClickListener {
            val intent = Intent(this, SearchActivity:: class.java)
            startActivity(intent)
        }
    }

    private fun replace (fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
}