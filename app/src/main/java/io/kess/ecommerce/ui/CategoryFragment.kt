package io.kess.ecommerce.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.kess.ecommerce.R
import io.kess.ecommerce.model.Category
import io.kess.ecommerce.ui.adapter.CategoryAdapter
import io.kess.ecommerce.util.UserSession
import io.kess.ecommerce.view_model.CategoryViewModel
import io.kess.ecommerce.view_model.ProductViewModel

//import io.kess.ecommerce.adapter.ProductAdapter

class CategoryFragment : Fragment() {
    private lateinit var categoryViewModel: CategoryViewModel
    private var categoryList: List<Category> = emptyList()
    private lateinit var categoryAdapter: CategoryAdapter
    val user = UserSession.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        val userName = view.findViewById<TextView>(R.id.tvGreeting)
        if (user != null) {
            userName.text = "Hi, ${user.name}"
        } else {
            userName.text = "Guest"
        }

        val search = view.findViewById<ImageView>(R.id.search)

        search.setOnClickListener {
            (activity as MainActivity).navigation(SearchFragment())
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerCategory)
        categoryAdapter = CategoryAdapter() { category -> openProductByCategory(category) }
        recyclerView.adapter = categoryAdapter
        recyclerView.layoutManager =
            GridLayoutManager(requireContext(), 1)
        observeData()
    }

    private fun openProductByCategory(category: Category) {
        val fragment = ProductListFragment().apply {
            arguments = Bundle().apply {
                putString("TYPE", "CATEGORY")
                putString("CATEGORY_ID", category.id)
                putString("CATEGORY_NAME", category.name)
            }
        }
        (activity as MainActivity).navigation(fragment)
    }

    private fun observeData() {
        categoryViewModel.categories.observe(viewLifecycleOwner) { result ->
            categoryList = result.sortedBy { it.createAt }
            categoryAdapter.submitList(categoryList)
        }
    }
}