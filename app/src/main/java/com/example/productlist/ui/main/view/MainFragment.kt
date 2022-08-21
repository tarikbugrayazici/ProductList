package com.example.productlist.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.productlist.R
import com.example.productlist.data.entity.ProductList
import com.example.productlist.databinding.FragmentMainBinding
import com.example.productlist.di.factory.ViewModelFactory
import com.example.productlist.ui.detail.DetailFragment
import com.example.productlist.ui.main.adapter.ProductListAdapter
import com.example.productlist.ui.viewmodel.MainViewModel
import com.example.productlist.ui.viewmodel.PreferencesViewModel
import com.example.productlist.ui.viewmodel.ProductViewModel
import com.example.productlist.util.Constants
import com.example.productlist.util.extension.findLastCompletelyVisibleItemPosition
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainFragment : DaggerFragment() {

    private val binding by viewBinding(FragmentMainBinding::bind)
    private lateinit var mainViewModel: MainViewModel
    private lateinit var productViewModel: ProductViewModel
    private lateinit var preferencesViewModel: PreferencesViewModel
    private var rvPosition = 0

    private val adapter: ProductListAdapter by lazy {
        ProductListAdapter(arrayListOf(),
            { viewItem, position ->
                detailClicked(viewItem, position)
            }, { product ->
                favClicked(product)
            })
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().supportFragmentManager.setFragmentResultListener(
            Constants.REQUEST_KEY,
            this
        ) { key, bundle ->
            rvPosition = bundle.getInt(Constants.BUNDLE_KEY)
        }
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        productViewModel = ViewModelProvider(this, viewModelFactory)[ProductViewModel::class.java]
        preferencesViewModel =
            ViewModelProvider(this, viewModelFactory)[PreferencesViewModel::class.java]

        with(binding.mainRecyclerView) {
            adapter = this@MainFragment.adapter
            layoutManager =
                if (preferencesViewModel.getIsLayoutLinear()) LinearLayoutManager(context)
                else GridLayoutManager(
                    context,
                    2
                )
            scrollToPosition(rvPosition)
            addOnScrollListener(recyclerViewOnScrollListener)
        }
        listenListDesign()
        mainViewModel.getProducts()
        observeViewModel()
    }

    private fun observeViewModel() {
        mainViewModel.addListLiveData.observe(viewLifecycleOwner) { data ->
            binding.progressBar.isVisible = false
            adapter.addList(data.result.productList)
        }

        mainViewModel.errorLiveData.observe(viewLifecycleOwner) { message ->
            binding.progressBar.isVisible = false
            Toast.makeText(requireContext(), getString(R.string.toast_error), Toast.LENGTH_SHORT)
                .show()
        }

        mainViewModel.products.observe(viewLifecycleOwner) { data ->
            binding.progressBar.isVisible = false
            adapter.setList(data.result.productList)
        }
    }

    override fun onPause() {
        super.onPause()
        mainViewModel.clearData()
    }

    private fun listenListDesign() {
        with(binding.listDesign) {
            setOnClickListener {
                with(binding.mainRecyclerView) {
                    layoutManager =
                        if (layoutManager is GridLayoutManager) {
                            preferencesViewModel.setIsLayoutLinear(true)
                            LinearLayoutManager(context)
                        } else {
                            preferencesViewModel.setIsLayoutLinear(false)
                            GridLayoutManager(context, 2)
                        }
                    layoutManager = layoutManager
                }
            }
        }
    }

    private fun detailClicked(productId: Int, position: Int) {
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer,
                DetailFragment.newInstance(productId, position)
            )
            .addToBackStack(null)
            .commit()
    }

    private fun favClicked(product: ProductList) {
        viewLifecycleOwner.lifecycleScope.launch {
            productViewModel.favClicked(product)
        }
    }

    private val recyclerViewOnScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val lastVisibleItemPosition = recyclerView.findLastCompletelyVisibleItemPosition()
            mainViewModel.paginateCollection(lastVisibleItemPosition)
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}