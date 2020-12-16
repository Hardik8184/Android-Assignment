package com.hardik.androidassignment.ui.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hardik.androidassignment.R
import com.hardik.androidassignment.data.model.local.DataModel
import com.hardik.androidassignment.databinding.DataListItemBinding

open class HomeListAdapter(
  private val countryDataList: ArrayList<DataModel>
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

    fun bind(responseData: DataModel) {

      businessItemBinding.apply {
        businessItemBinding.llMain.visibility = View.VISIBLE
        businessItemBinding.mainLayout.visibility = View.VISIBLE
        this.responseData = responseData
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