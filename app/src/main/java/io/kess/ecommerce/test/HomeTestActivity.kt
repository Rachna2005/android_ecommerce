package io.kess.ecommerce.test

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.RecyclerView
import io.kess.ecommerce.R
import io.kess.ecommerce.ui.CheckOutFragment
import io.kess.ecommerce.ui.adapter.ProductAdapter

import io.kess.ecommerce.view_model.FavoriteViewModel
import io.kess.ecommerce.view_model.ProductViewModel

class HomeTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test)
        supportFragmentManager.beginTransaction().replace(R.id.container, CheckOutFragment()).commit()

    }

}