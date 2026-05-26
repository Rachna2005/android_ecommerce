package io.kess.ecommerce.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import io.kess.ecommerce.R
import io.kess.ecommerce.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var homeFragment: HomeFragment? = null
    private var categoryFragment: CategoryFragment? = null
    private var cartFragment: CartFragment? = null
    private var profileFragment: ProfileFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        homeFragment = HomeFragment()
        supportFragmentManager.beginTransaction().add(binding.container.id, homeFragment!!).commit()

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    if(homeFragment == null){
                        homeFragment = HomeFragment()
                    }
                    switchFragment(homeFragment!!)
                }
                R.id.nav_category -> {
                    if(categoryFragment == null){
                        categoryFragment = CategoryFragment()
                    }
                    switchFragment(categoryFragment!!)
                }
                R.id.nav_cart -> {
                    if(cartFragment == null){
                        cartFragment = CartFragment()
                    }
                    switchFragment(cartFragment!!)
                }
                R.id.nav_profile -> {
                    if(profileFragment == null){
                        profileFragment = ProfileFragment()
                    }
                    switchFragment(profileFragment!!)
                }
            }
            true
        }
    }
    private fun switchFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        val fragments = listOfNotNull(
            homeFragment,
            categoryFragment,
            cartFragment,
            profileFragment
        )
        for (f in fragments){
            transaction.hide(f)
        }
        if(!fragment.isAdded){
            transaction.add(binding.container.id, fragment)
        }else{
            transaction.show(fragment)
        }
        transaction.commit()
    }

    fun navigation(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
            .addToBackStack(null).commit()
    }
    fun showButtonNav(show: Boolean) {
        binding.bottomNav.visibility = if (show) View.VISIBLE else View.GONE
    }
}