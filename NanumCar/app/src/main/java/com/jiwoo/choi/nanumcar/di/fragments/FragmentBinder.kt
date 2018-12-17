package com.jiwoo.choi.nanumcar.di.fragments

import com.jiwoo.choi.nanumcar.di.fragments.modules.*
import com.jiwoo.choi.nanumcar.ui.fragments.info.InfoFragment
import com.jiwoo.choi.nanumcar.ui.fragments.navi.NaviFragment
import com.jiwoo.choi.nanumcar.ui.fragments.park.ParkFragment
import com.jiwoo.choi.nanumcar.ui.fragments.theme.ThemeFragment
import com.jiwoo.choi.nanumcar.ui.fragments.tour.TourFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


// 프래그먼트를 대거에 올림
@Module
abstract class FragmentBinder{
    @ContributesAndroidInjector(modules = arrayOf(InfoModule::class))
    abstract fun bindInfoModule() : InfoFragment

    @ContributesAndroidInjector(modules = arrayOf(NaviModule::class))
    abstract fun bindNaviModule() : NaviFragment

    @ContributesAndroidInjector(modules = arrayOf(ParkModule::class))
    abstract fun bindParkModule() : ParkFragment

    @ContributesAndroidInjector(modules = arrayOf(ThemeModule::class))
    abstract fun bindThemeModule() : ThemeFragment

    @ContributesAndroidInjector(modules = arrayOf(TourModule::class))
    abstract fun bindTourModule() : TourFragment
}

