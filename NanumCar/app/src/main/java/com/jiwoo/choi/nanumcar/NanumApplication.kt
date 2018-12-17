package com.jiwoo.choi.nanumcar

import com.jiwoo.choi.nanumcar.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class NanumApplication : DaggerApplication(){
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}