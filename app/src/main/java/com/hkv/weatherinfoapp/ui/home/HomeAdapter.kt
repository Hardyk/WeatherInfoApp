package com.hkv.weatherinfoapp.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hkv.weatherinfoapp.R
import com.hkv.weatherinfoapp.database.entityData.CityLocation
import com.hkv.weatherinfoapp.databinding.RowCityBinding
import com.hkv.weatherinfoapp.network.networkDto.CityWeather

class HomeAdapter(
    val context: Context?,
    val clickListener: CityItemClickListner
) : RecyclerView.Adapter<HomeAdapter.CityViewHolder>() {

    var cityList: ArrayList<CityLocation> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val viewBinding: RowCityBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_city, parent, false
        )
        return CityViewHolder(viewBinding)
    }


    override fun getItemCount(): Int {
        return cityList.size
    }


    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun setBookMarkedCity(countries: ArrayList<CityLocation>) {
        this.cityList = countries
        notifyDataSetChanged()
    }

    inner class CityViewHolder(private val viewBinding: RowCityBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun onBind(position: Int) {
            val row = cityList[position]
            viewBinding.city = row
            viewBinding.cityClickInterface = clickListener
        }
    }

    fun removeItem(position: Int): CityLocation {
        val cityLocation = this.cityList[position]
        this.cityList.removeAt(position)
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position)
        return cityLocation;
    }


}