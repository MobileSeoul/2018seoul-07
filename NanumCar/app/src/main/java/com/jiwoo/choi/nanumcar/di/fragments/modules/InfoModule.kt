package com.jiwoo.choi.nanumcar.di.fragments.modules

import com.jiwoo.choi.nanumcar.di.driver.DataDriver
import com.jiwoo.choi.nanumcar.ui.fragments.info.InfoViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class InfoModule{
    @Provides
    fun provideInfoViewModelFactory(driver: DataDriver) = InfoViewModelFactory(driver)
}