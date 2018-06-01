package io.keepcoding.guedrbootcamp6.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.keepcoding.guedrbootcamp6.R
import io.keepcoding.guedrbootcamp6.fragment.CityListFragment


class ForecastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

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

}
