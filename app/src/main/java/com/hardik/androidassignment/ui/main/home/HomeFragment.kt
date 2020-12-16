package com.hardik.androidassignment.ui.main.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hardik.androidassignment.R
import com.hardik.androidassignment.data.model.local.DataModel
import com.hardik.androidassignment.databinding.FragmentHomeBinding
import com.hardik.androidassignment.ui.BaseActivity
import com.hardik.androidassignment.ui.BaseFragment
import com.hardik.androidassignment.utils.CommonDataUtility
import com.hardik.androidassignment.utils.kprogresshud.KProgressHUD
import javax.inject.Inject

class HomeFragment : BaseFragment() {

  private lateinit var binding: FragmentHomeBinding

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  private var dataList = ArrayList<DataModel>()

  private val homeFragmentViewModel: HomeViewModel by viewModels {
    viewModelFactory
  }

  private lateinit var progressHUD: KProgressHUD

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
    binding.lifecycleOwner = this


    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initUI()
  }

  private fun initUI() {

    progressHUD = KProgressHUD.create(activity)
        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
        .setCancellable(false)

    binding.rvData.layoutManager =
        LinearLayoutManager((activity as BaseActivity), RecyclerView.VERTICAL, false)

    binding.swipeContainer.setOnRefreshListener { // Your code to refresh the list here.
      getData()
    }

    observeLiveData()
    getData()
  }

  private fun getData() {

    if (CommonDataUtility.checkConnection(requireActivity())) {
      homeFragmentViewModel.getData()
    } else {
      CommonDataUtility.showErrorSnackBar(
          requireActivity(), binding.llHomeRoot,
          getString(R.string.str_no_internet)
      )

      getDataFromDB()
    }
  }

  private fun observeLiveData() {

    homeFragmentViewModel.titleData.observe(viewLifecycleOwner) { value ->

      if (value.isNotEmpty())
        mainActivity.getToolBarTitle().text = value

    }

    homeFragmentViewModel.dataList.observe(viewLifecycleOwner) { value ->

      if (value.size > 0) {
        mainActivity.dataBaseHelper.insertData(value)
        getDataFromDB()
      }
    }

    homeFragmentViewModel.showProgressBar.observe(viewLifecycleOwner) { value ->

      if (value) {
        showProgressBar()
      } else {
        hideProgressBar()
      }

    }

    homeFragmentViewModel.errorResponse.observe(viewLifecycleOwner) { value ->

      if (value.isNotEmpty())
        CommonDataUtility.showErrorSnackBar(requireActivity(), binding.llHomeRoot, value)

    }
  }

  private fun getDataFromDB() {

    println("Hardik getDataFromDB")

    dataList.clear()
    dataList = mainActivity.dataBaseHelper.getData()
    setAdapter(dataList)
  }

  /* Set Data to Adapter */
  private fun setAdapter(data: ArrayList<DataModel>) {

    println("Hardik setAdapter")

    val adapter = HomeListAdapter(countryDataList = data)
    binding.rvData.adapter = adapter
  }

  private fun hideProgressBar() {
    progressHUD.let { if (it.isShowing) it.dismiss() }
  }

  fun showProgressBar() {
    hideProgressBar()
    progressHUD.show()
  }
}
