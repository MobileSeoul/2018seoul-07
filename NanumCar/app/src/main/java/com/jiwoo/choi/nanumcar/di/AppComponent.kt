package com.jiwoo.choi.nanumcar.di

import android.content.Context
import com.jiwoo.choi.nanumcar.NanumApplication
import com.jiwoo.choi.nanumcar.di.activities.ActivityBinder
import com.jiwoo.choi.nanumcar.di.fragments.FragmentBinder
import com.jiwoo.choi.nanumcar.di.global.ApiModule
import com.jiwoo.choi.nanumcar.di.global.DriverModule
import com.jiwoo.choi.nanumcar.di.global.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


// 의존성 컴포넌트
@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        ActivityBinder::class,
        FragmentBinder::class,
        NetworkModule::class,
        DriverModule::class,
        ApiModule::class
        ))
interface AppComponent : AndroidInjector<NanumApplication> {
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(app : Context) : Builder
        fun build() : AppComponent
    }
}