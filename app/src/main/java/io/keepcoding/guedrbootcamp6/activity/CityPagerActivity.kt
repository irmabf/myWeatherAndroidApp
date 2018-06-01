package io.keepcoding.guedrbootcamp6.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.view.Menu
import android.view.MenuItem
import io.keepcoding.guedrbootcamp6.R
import io.keepcoding.guedrbootcamp6.fragment.ForecastFragment
import io.keepcoding.guedrbootcamp6.model.Cities
import kotlinx.android.synthetic.main.activity_city_pager.*

class CityPagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_pager)

        toolbar.setLogo(R.mipmap.ic_launcher)
        setSupportActionBar(toolbar)


        val cities = Cities()

        val adapter = object: FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return ForecastFragment.newInstance(cities.getCity(position))
            }

            override fun getCount() = cities.count

            override fun getPageTitle(position: Int): CharSequence {
                return cities.getCity(position).name
            }

        }

        view_pager.adapter = adapter
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
        else -> super.onOptionsItemSelected(item)
    }
}
