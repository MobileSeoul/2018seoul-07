package com.jiwoo.choi.nanumcar.di.activities

import android.app.Activity
import com.jiwoo.choi.nanumcar.di.activities.modules.DetailModule
import com.jiwoo.choi.nanumcar.di.activities.modules.MainModule
import com.jiwoo.choi.nanumcar.di.activities.modules.SplashModule
import com.jiwoo.choi.nanumcar.ui.activities.detail.DetailActivity
import com.jiwoo.choi.nanumcar.ui.activities.main.MainActivity
import com.jiwoo.choi.nanumcar.ui.activities.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


// 액티비티를 대거 그래프에 올리는 클래스
@Module
abstract class ActivityBinder{

    @ContributesAndroidInjector(modules = arrayOf(MainModule::class))
    abstract fun bindMain() : MainActivity

    @ContributesAndroidInjector(modules = arrayOf(SplashModule::class))
    abstract fun bindSplashModule() : SplashActivity

    @ContributesAndroidInjector(modules = arrayOf(DetailModule::class))
    abstract fun bindDetailModule() : DetailActivity
}