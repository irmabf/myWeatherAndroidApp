package io.keepcoding.guedrbootcamp6.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.keepcoding.guedrbootcamp6.R
import io.keepcoding.guedrbootcamp6.fragment.CityListFragment
import io.keepcoding.guedrbootcamp6.fragment.CityListFragment.OnCitySelectedListener
import io.keepcoding.guedrbootcamp6.model.City

class ForecastActivity : AppCompatActivity(), OnCitySelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        /*Averiguamos qué interfaz hemos cargado
        * **/

        //Comprobamos que no tenemos ya añadido el fragment a nuestra jerarquia
        //Hacemos esto para evitar que se superponga el mismo fragment varias veces
        if (supportFragmentManager.findFragmentById(R.id.city_list) == null) {
            //Añadiremos el fragment de forma dinamica
            val fragment = CityListFragment.newInstance()

            supportFragmentManager.beginTransaction()
                    .add( R.id.city_list_fragment, fragment)
                    .commit()
        }
    }
    override fun onCitySelected(city: City, position: Int) {
        //Lanzamos la pantalla del view pager con la lista de ciudades
        //el metodo intent() lo creamos en el CityPagerActivity
        //Recordamos que los intent siempre llevan como parametro un
        //Contexto que en el caso de las actividades es this
        val intent = CityPagerActivity.intent(this, position)
        startActivity(intent)
    }
}
