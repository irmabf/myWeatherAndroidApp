package io.keepcoding.guedrbootcamp6.fragment

import android.app.Activity
import android.content.Context
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

    private var onCitySelectedListener: OnCitySelectedListener? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_city_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val cities = Cities()
        val adapter = ArrayAdapter<City>(
                activity,
                android.R.layout.simple_list_item_1,
                Cities.toArray())
        city_list.adapter = adapter
        city_list.setOnItemClickListener { _, _, index, _ ->
            //Avisamos al listener que una ciudad ha sido seleccionada
            //pasamos la ciudad y la posicion que ocupa en la lista o array
            //. Nos vamos a forecast activity para que al actividad implemente la interfaz
            onCitySelectedListener?.onCitySelected(Cities[index], index)
        }
    }
    //2. Este fragment va a tener un atributo que va a ser el listener
    // de lo que se ha pasado

    //3. Implementar los metodos onAttach y on Dettach

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        commonAttach(context as Activity)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        commonAttach(context as Activity)
    }
    private fun commonAttach(activity: Activity?) {
        if (activity is OnCitySelectedListener){
            onCitySelectedListener = activity
        }else{
            onCitySelectedListener = null
        }
    }
    override fun onDetach() {
        super.onDetach()
        onCitySelectedListener = null
    }
    //1. Interfaz con la que digo que se ha seleccionado una ciudad
    /*Esta interfaz sera la que implemente la actividad que se quiere relaconar con el fragmen*/

    interface  OnCitySelectedListener {
        //Paso la ciudad que se ha seleccionado y la posicion que ocupa esa ciudad dentro de la lista
        fun onCitySelected(city: City, position: Int)
    }

}
