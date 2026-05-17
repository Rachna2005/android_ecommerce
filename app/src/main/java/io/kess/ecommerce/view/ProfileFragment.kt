package io.kess.ecommerce.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import io.kess.ecommerce.R


class ProfileFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        view.findViewById<ImageView>(R.id.order).setOnClickListener {
//            val intent  = Intent(requireContext(), OrderHistoryActivity::class.java)
//            startActivity(intent)
//        }
//        view.findViewById<ImageView>(R.id.favorite).setOnClickListener {
//            val intent  = Intent(requireContext(), SplashSaleActivity::class.java)
//            startActivity(intent)
//        }
    }

}