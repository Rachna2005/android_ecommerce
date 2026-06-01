package io.kess.ecommerce.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import io.kess.ecommerce.R
import io.kess.ecommerce.databinding.FragmentProfileBinding
import io.kess.ecommerce.util.UserSession
import io.kess.ecommerce.view_model.AuthViewModel

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    val user = UserSession.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

       if(user != null){
           binding.uName.text = user.name
       }else{
           binding.uName.text = "Guest"
       }
        setupOnClickListener()

    }

    private fun setupOnClickListener(){
        binding.favorite.setOnClickListener {
            val fragment = ProductListFragment().apply {
                arguments = Bundle().apply {
                    putString("TYPE", "FAVORITE")
                }
            }
            (activity as MainActivity).navigation(fragment)
        }

        binding.btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            UserSession.currentUser =  null
            startActivity(
                Intent(requireContext(), Onboarding1Activity::class.java)
            )
            requireActivity().finish()
        }
    }

}