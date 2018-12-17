package com.jiwoo.choi.nanumcar.di.global

import com.jiwoo.choi.nanumcar.di.driver.DataDriver
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DriverModule{
    @Provides
    @Singleton
    fun provideDriver() = DataDriver()
}