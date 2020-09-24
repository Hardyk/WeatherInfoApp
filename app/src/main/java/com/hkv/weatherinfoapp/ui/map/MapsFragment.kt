package com.hkv.weatherinfoapp.ui.map

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.hkv.weatherinfoapp.R
import com.hkv.weatherinfoapp.databinding.FragmentMapsBinding
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class MapsFragment : Fragment(), GoogleMap.OnMapLongClickListener {

    private val mViewmodel by viewModel<MapsViewModel>()
    private lateinit var mDataBinding: FragmentMapsBinding
    private lateinit var mGoogleMap: GoogleMap

    private val callback = OnMapReadyCallback { googleMap ->

        mGoogleMap = googleMap;

        mGoogleMap.setOnMapLongClickListener(this);
        val abad = LatLng(23.0225, 72.5714)
//        googleMap.addMarker(MarkerOptions().position(abad).title("Ahmadabad"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(abad))


        mViewmodel.loadBookmarks()
        mViewmodel.cityList.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty() && it != null) {
                mGoogleMap.clear()
                it.forEach { cityLocation ->
                    val bookmarkLocation = LatLng(cityLocation.latitude, cityLocation.longitude)
                    googleMap.addMarker(
                        MarkerOptions().position(bookmarkLocation)
                            .title(getCityFromGeoCoder(bookmarkLocation))
                    )
                }
            }
        })

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mDataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_maps, container, false)
        return mDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    override fun onMapLongClick(latlng: LatLng) {
//        mGoogleMap.clear();
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLng(latlng));

        val location = LatLng(latlng.latitude, latlng.longitude)

        val cityName = getCityFromGeoCoder(location)
        mGoogleMap.addMarker(MarkerOptions().position(location).title(cityName))

        if (cityName != "No City found") {
            mViewmodel.bookMarkCity(
                cityName,
                lat = latlng.latitude,
                long = latlng.longitude
            )
        }

    }

    private fun getCityFromGeoCoder(latlng: LatLng): String {
        val geoCoder = Geocoder(context, Locale.getDefault())
        val addresses: List<Address>? =
            geoCoder.getFromLocation(latlng.latitude, latlng.longitude, 1);
        return if (addresses.isNullOrEmpty()) {
            "No City found"
        } else {
            addresses[0].locality ?: addresses[0].featureName ?: "No City found"
        }

    }


}