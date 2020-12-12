package com.android.androidassignment.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.androidassignment.databinding.FragmentHomeBinding
import com.android.androidassignment.ui.BaseActivity
import com.android.androidassignment.ui.BaseFragment
import com.hardik.mvvmapp.data.model.response.ApiDataResponse
import com.hardik.mvvmapp.utils.helper.CommonDataUtility
import javax.inject.Inject

class HomeFragment : BaseFragment() {

  private lateinit var binding: FragmentHomeBinding

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  private val homeViewModel: HomeFragmentViewModel by viewModels {
    viewModelFactory
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    (activity as BaseActivity).getActivityComponent().inject(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
    initUI()

    return binding.root
  }

  private fun initUI() {

    binding.rvData.layoutManager =
        LinearLayoutManager((activity as BaseActivity), RecyclerView.VERTICAL, false)

    observeLiveData()
    homeViewModel.getData()
  }

  private fun observeLiveData() {

    homeViewModel.countryResponseLiveData.observe(viewLifecycleOwner, { value ->

      if (value.size > 0) {
        setAdapter(value)
      }
    })

    homeViewModel.showProgressBar.observe(viewLifecycleOwner, { value ->

      if (value) {
        showProgressBar()
      } else {
        hideProgressBar()
      }

    })

    homeViewModel.errorResponse.observe(viewLifecycleOwner, { value ->
      CommonDataUtility.showErrorSnackBar((activity as BaseActivity), binding.llRoot, value)
    })

  }

  /* Set Data to Adapter */
  private fun setAdapter(data: ArrayList<ApiDataResponse.RowsResponseData>) {

    val adapter = HomeListAdapter(countryDataList = data)
    binding.rvData.adapter = adapter
  }
}

//    setUpAdapter()
//    binding.cardUserInfo.data = mainActivityViewModel.getUserInfo()
//    binding.cardUserInfo.qrCode = fastAmySharedPreferences.qrCode
//    binding.cardUserInfo.tvName.text =
//        fastAmySharedPreferences.userName!!.capitalizeSentence().trim()
//    observeLiveData()
//    binding.cardUserInfo.icShare.setOnClickListener {
//      shareHelper.share()
//    }
