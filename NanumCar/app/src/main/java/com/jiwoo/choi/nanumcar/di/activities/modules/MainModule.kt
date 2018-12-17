package com.jiwoo.choi.nanumcar.di.activities.modules

import com.jiwoo.choi.nanumcar.di.driver.DataDriver
import com.jiwoo.choi.nanumcar.ui.activities.main.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MainModule{
    @Provides
    fun provideMainViewModelFactory(driver : DataDriver) = MainViewModelFactory(driver)
}
