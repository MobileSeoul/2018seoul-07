package com.jiwoo.choi.nanumcar.ui.activities.main

import android.arch.lifecycle.ViewModel
import com.jiwoo.choi.nanumcar.di.driver.DataDriver
import com.jiwoo.choi.nanumcar.ui.fragments.info.InfoFragment
import com.jiwoo.choi.nanumcar.ui.fragments.navi.NaviFragment
import com.jiwoo.choi.nanumcar.ui.fragments.park.ParkFragment
import com.jiwoo.choi.nanumcar.ui.fragments.theme.ThemeFragment
import com.jiwoo.choi.nanumcar.ui.fragments.tour.TourFragment

class MainViewModel(val driver: DataDriver) : ViewModel(){

    val fragements = listOf(
            ThemeFragment(),
            ParkFragment(),
            InfoFragment(),
            TourFragment(),
            NaviFragment())
}