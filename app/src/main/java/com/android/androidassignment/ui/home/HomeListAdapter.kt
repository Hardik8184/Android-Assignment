package com.android.androidassignment.ui.home

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.androidassignment.R
import com.android.androidassignment.databinding.DataListItemBinding
import com.hardik.mvvmapp.data.model.response.ApiDataResponse

open class HomeListAdapter(
  private val countryDataList: ArrayList<ApiDataResponse.RowsResponseData>
) :
    RecyclerView.Adapter<HomeListAdapter.HomeViewHolder>() {

  override fun onCreateViewHolder(
    viewGroup: ViewGroup,
    i: Int
  ): HomeViewHolder {

    val binding = DataBindingUtil.inflate<DataListItemBinding>(
        LayoutInflater.from(viewGroup.context),
        R.layout.data_list_item, viewGroup, false
    )

    return HomeViewHolder(binding)
  }

  inner class HomeViewHolder(binding: DataListItemBinding) : RecyclerView.ViewHolder(
      binding.mainLayout
  ) {

    private val businessItemBinding: DataListItemBinding = binding

    fun bind(responseData: ApiDataResponse.RowsResponseData) {

      businessItemBinding.apply {

        if (responseData.title.isNullOrEmpty()) {
          businessItemBinding.mainLayout.visibility = View.GONE
          businessItemBinding.llMain.visibility = View.GONE
        } else {
          businessItemBinding.llMain.visibility = View.VISIBLE
          businessItemBinding.mainLayout.visibility = View.VISIBLE
          this.responseData = responseData
        }
      }
    }
  }

  override fun onBindViewHolder(
    holder: HomeViewHolder,
    position: Int
  ) {
    holder.bind(countryDataList[position])
  }

  override fun getItemId(position: Int): Long {
    return position.toLong()
  }

  override fun getItemCount(): Int {
    return countryDataList.size
  }
}