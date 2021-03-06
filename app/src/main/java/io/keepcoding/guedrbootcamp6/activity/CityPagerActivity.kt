package io.keepcoding.guedrbootcamp6.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuItem
import io.keepcoding.guedrbootcamp6.R
import io.keepcoding.guedrbootcamp6.fragment.ForecastFragment
import io.keepcoding.guedrbootcamp6.model.Cities
import io.keepcoding.guedrbootcamp6.model.City
import kotlinx.android.synthetic.main.activity_city_pager.*
import java.text.FieldPosition

class CityPagerActivity : AppCompatActivity() {

    /**Metodo intent(city) en el que le paso una ciudad al city pager activity */
    companion object {
        val EXTRA_CITY = "EXTRA_CITY"

        fun intent(context: Context, cityIndex: Int): Intent {
            //Tenemos que crear un intent y guardarlo en la variable intent,
            //al intent le pasamos el contexto  y la clase que queremos arrancar
            val intent = Intent(context, CityPagerActivity::class.java)
            intent.putExtra(EXTRA_CITY, cityIndex)
            return intent
        }
    }
    //private val cities = Cities()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_pager)

        toolbar.setLogo(R.mipmap.ic_launcher)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = object: FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return ForecastFragment.newInstance(Cities[position])
            }

            override fun getCount() = Cities.count

            override fun getPageTitle(position: Int): CharSequence {
                return Cities[position].name
            }
        }

        view_pager.adapter = adapter
        view_pager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                updateCityInfo(position)
            }
        })

        val initialCityInfo = intent.getIntExtra(EXTRA_CITY, 0)
       moveToCity(initialCityInfo)
        updateCityInfo(initialCityInfo)
    }

    private fun updateCityInfo(position: Int){
       supportActionBar?.title = Cities[position].name
    }

    private fun moveToCity(position: Int){
        view_pager.currentItem = position
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.pager_navigation, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {

        R.id.previous -> {
            view_pager.currentItem = view_pager.currentItem - 1
            true
        }
        R.id.next -> {
            view_pager.currentItem = view_pager.currentItem + 1
            true
        }
        android.R.id.home -> { //Nos han llamado a la flech de back
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        super.onPrepareOptionsMenu(menu)

        val previousMenu = menu?.findItem(R.id.previous)
        val nextMenu = menu?.findItem(R.id.next)

        val adapter = view_pager.adapter!!
        previousMenu?.isEnabled = view_pager.currentItem > 0
        nextMenu?.isEnabled = view_pager.currentItem < adapter.count - 1
        return true
    }
}
