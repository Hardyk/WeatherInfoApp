package com.hkv.weatherinfoapp.ui.cityweather

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.hkv.weatherinfoapp.R
import com.hkv.weatherinfoapp.database.entityData.CityLocation
import com.hkv.weatherinfoapp.databinding.FragmentCityWeatherBinding
import com.hkv.weatherinfoapp.databinding.FragmentHomeBinding
import com.hkv.weatherinfoapp.network.networkDto.CityWeather
import com.hkv.weatherinfoapp.ui.home.HomeViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class CityWeatherFragment : Fragment() {

    private val mViewmodel by viewModel<CityWeatherViewModel>()
    private lateinit var mDataBinding: FragmentCityWeatherBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mDataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_city_weather, container, false)
        return mDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val lat = arguments?.getString("latitude")
        val lon = arguments?.getString("longitude")

        Log.e("@@Cities", "Latitude     >  $lat")
        Log.e("@@Cities", "Longitude     >  $lon")
        mDataBinding.viewModel = mViewmodel



        if (lat != null && lon != null) {
            mViewmodel.getWeatherInfo(lat, lon)
        }

        mViewmodel.cityWeatherData.observe(viewLifecycleOwner, Observer {
            Log.d("@@Cities", it.toString())
            if (it != null) {
                val cityWeather = it
                mDataBinding.data = cityWeather
            }
        })


    }

}