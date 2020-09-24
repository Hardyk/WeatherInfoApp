package com.hkv.weatherinfoapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hkv.weatherinfoapp.R
import com.hkv.weatherinfoapp.database.entityData.CityLocation
import com.hkv.weatherinfoapp.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment(), CityItemClickListner {


    private val mViewmodel by viewModel<HomeViewModel>()
    private lateinit var mDataBinding: FragmentHomeBinding
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mDataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return mDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mDataBinding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_to_MapFragment)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setView()
        mDataBinding.viewModel = mViewmodel
        mViewmodel.getAllCity()
        mViewmodel.cityList.observe(viewLifecycleOwner, Observer {
            Log.d("@@Cities", it.size.toString())
            if (it.isNotEmpty() && it != null) {
                mDataBinding.homeTvEmptyView.visibility = View.GONE
                homeAdapter.setBookMarkedCity(it as ArrayList<CityLocation>)
            } else {
                mDataBinding.homeTvEmptyView.visibility = View.VISIBLE
            }
        })
    }

    private fun setView() {
        homeAdapter = HomeAdapter(context, this)
        home_recyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        home_recyclerView.adapter = homeAdapter
        home_recyclerView.isNestedScrollingEnabled = false
        home_recyclerView.setHasFixedSize(true)
        val swipeHandler = object : SwipeToDeleteCallback(this.context) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = home_recyclerView.adapter as HomeAdapter
                mViewmodel.deleteCity(adapter.removeItem(viewHolder.adapterPosition))
                if (adapter.cityList.isEmpty()) {
                    mDataBinding.homeTvEmptyView.visibility = View.VISIBLE
                }

            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(home_recyclerView)
    }

    override fun onItemClick(city: CityLocation) {
        val bundle = Bundle()
        bundle.putString("latitude", city.latitude.toString())
        bundle.putString("longitude", city.longitude.toString())
        findNavController().navigate(R.id.action_HomeFragment_to_CityWeatherFragment, bundle)
    }
}