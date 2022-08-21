package com.example.productlist.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.productlist.R
import com.example.productlist.data.entity.detail.ResultDetail
import com.example.productlist.databinding.FragmentDetailBinding
import com.example.productlist.di.factory.ViewModelFactory
import com.example.productlist.ui.viewmodel.DetailViewModel
import com.example.productlist.ui.viewmodel.ProductViewModel
import com.example.productlist.util.Constants
import com.example.productlist.util.extension.fromHtml
import com.example.productlist.util.extension.loadImage
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.properties.Delegates


class DetailFragment : DaggerFragment() {

    private val binding by viewBinding(FragmentDetailBinding::bind)
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var productViewModel: ProductViewModel
    private var productId by Delegates.notNull<Int>()
    private var position by Delegates.notNull<Int>()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productId = arguments?.getInt(PRODUCT_ID)!!
        position = arguments?.getInt(POSITION)!!
        listenBack()
        initViewModels()
        getProductDetail()
        observeViewModel()
    }

    private fun observeViewModel() {
        detailViewModel.productDetail.observe(viewLifecycleOwner) {
            setData(it)
        }
        detailViewModel.errorLiveData.observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), getString(R.string.toast_error), Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun initViewModels() {
        detailViewModel =
            ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]
        productViewModel =
            ViewModelProvider(this, viewModelFactory)[ProductViewModel::class.java]
    }

    private fun listenFavImage(product: ResultDetail) {
        binding.favImage.setOnClickListener {
            product.isFav = !product.isFav
            setData(product)
            viewLifecycleOwner.lifecycleScope.launch {
                productViewModel.favClickedFromDetail(product)
            }
        }
    }

    private fun listenBack() {
        binding.backImage.setOnClickListener {
            requireActivity().supportFragmentManager.setFragmentResult(
                Constants.REQUEST_KEY,
                bundleOf(Constants.BUNDLE_KEY to position)
            )
            parentFragmentManager.popBackStack()
        }
    }

    private fun setData(product: ResultDetail) {
        with(binding) {
            twBrandName.text = product.brandName
            twProductName.text = product.displayName
            twPrice.text = product.actualPriceText
            twDescription.text = product.description.property.toString().fromHtml()
            productDetailImage.loadImage(product.images[1].images[0].image)
            val favIcon = if (product.isFav) R.drawable.fav else R.drawable.un_fav
            favImage.setImageResource(favIcon)
            listenFavImage(product)
        }
    }

    private fun getProductDetail() {
        detailViewModel.getProductDetail(productId)
    }

    companion object {
        private const val PRODUCT_ID = "productId"
        private const val POSITION = "position"
        fun newInstance(productId: Int, position: Int) = DetailFragment().apply {
            arguments = Bundle().apply {
                putInt(PRODUCT_ID, productId)
                putInt(POSITION, position)
            }
        }
    }
}