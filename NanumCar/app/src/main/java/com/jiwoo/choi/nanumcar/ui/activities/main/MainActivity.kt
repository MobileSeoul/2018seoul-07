package com.jiwoo.choi.nanumcar.ui.activities.main

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.system.Os.bind
import com.jiwoo.choi.dobike.const.T_MAP_KEY
import com.jiwoo.choi.gwangju_contest.extension.plusAssign
import com.jiwoo.choi.gwangju_contest.rx.AutoClearedDisposable
import com.jiwoo.choi.nanumcar.R
import com.jiwoo.choi.nanumcar.R.id.mainViewpager
import com.jiwoo.choi.nanumcar.R.id.tab
import com.jiwoo.choi.nanumcar.adapter.pager.MainPagerAdapter
import com.jiwoo.choi.nanumcar.util.TMapUtils
import com.skt.Tmap.TMapTapi
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import android.graphics.PorterDuff
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v4.content.ContextCompat
import org.jetbrains.anko.info


var tempLocation: Location? = null

class MainActivity : DaggerAppCompatActivity() {

    var locationManager: LocationManager? = null
    var locationListener: LocationListener? = null
    @Inject
    lateinit var viewModelFactory: MainViewModelFactory
    lateinit var viewModel : MainViewModel
    val disposable = AutoClearedDisposable(this)
    val viewDisposables = AutoClearedDisposable(lifecycleOwner = this,alwaysClearOnStop = false)
    val tabNames = arrayOf("테마","주차장","안내","여행지","네비")
    val tabIcons = arrayOf(
            R.drawable.tab_theme,
            R.drawable.tab_park,
            R.drawable.tab_info,
            R.drawable.tab_tour,
            R.drawable.tab_navi
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycle += viewDisposables
        viewModel = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]
        setTmap()
        bindGps()
        bindViewPager(viewModel.fragements)
    }

    private fun bindViewPager(list : List<Fragment>) {
        mainViewpager.adapter = MainPagerAdapter(supportFragmentManager, list)
        for (i in 0..4) tab.addTab(tab.newTab().setText(tabNames[i]).setIcon(tabIcons[i]))
        mainViewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab))
        tab.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(mainViewpager))
        tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val tabIconColor = ContextCompat.getColor(this@MainActivity, R.color.icon_selected)
                tab.icon!!.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val tabIconColor = ContextCompat.getColor(this@MainActivity, R.color.icon_unselected)
                tab.icon!!.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    override fun onBackPressed() {}
    private fun setTmap(){
        TMapUtils.setTmap(this)
    }

    @SuppressLint("MissingPermission")
    fun bindGps() {
        locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location?) {
                location.let {
                    tempLocation = it
                }
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            }

            override fun onProviderEnabled(provider: String?) {}

            override fun onProviderDisabled(provider: String?) {}
        }

        locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1.0f, locationListener!!)
        locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1.0f, locationListener!!)
    }
}
