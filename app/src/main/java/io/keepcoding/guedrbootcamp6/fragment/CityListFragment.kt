package io.keepcoding.guedrbootcamp6.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import io.keepcoding.guedrbootcamp6.R
import io.keepcoding.guedrbootcamp6.model.Cities
import io.keepcoding.guedrbootcamp6.model.City
import kotlinx.android.synthetic.main.fragment_city_list.*

class CityListFragment : Fragment() {
    companion object {
        //Creamos una nueva instancia del city list fragment
        fun newInstance() = CityListFragment()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_city_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cities = Cities()
        val adapter = ArrayAdapter<City>(
                activity,
                android.R.layout.simple_list_item_1,
                cities.toArray())
        city_list.adapter = adapter

    }

}
