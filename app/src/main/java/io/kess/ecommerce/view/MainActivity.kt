package io.kess.ecommerce.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.kess.ecommerce.R
import io.kess.ecommerce.databinding.ActivityMainBinding
import io.kess.ecommerce.model.Cart

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replace(fragment = HomeFragment())
//        val homeBtn = findViewById<ImageView>(R.id.nav_home)
//        homeBtn.setOnClickListener {
//          replace(fragment = HomeFragment())
//        }
//        val profileBtn = findViewById<ImageView>(R.id.nav_profile)
//        profileBtn.setOnClickListener {
//            replace(fragment = ProfileFragment())
//        }
//        val categoryBtn = findViewById<ImageView>(R.id.nav_category)
//        categoryBtn.setOnClickListener {
//            replace(fragment = CategoryFragment())
//        }
//        val cartBtn = findViewById<ImageView>(R.id.nav_cart)
//        cartBtn.setOnClickListener {
//            replace(fragment = CartFragment())
//        }

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> replace(fragment = HomeFragment())
                R.id.nav_category -> replace(fragment = CategoryFragment())
                R.id.nav_cart -> replace(fragment = CartFragment())
                R.id.nav_profile -> replace(fragment = ProfileFragment())
            }
            true
        }

    }

    private fun replace(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }

    fun navigation(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
            .addToBackStack(null).commit()
    }
    fun showButtonNav(show: Boolean) {
        binding.bottomNav.visibility = if (show) View.VISIBLE else View.GONE
    }
}