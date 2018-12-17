package com.jiwoo.choi.nanumcar.di.activities.modules

import com.jiwoo.choi.nanumcar.di.driver.DataDriver
import com.jiwoo.choi.nanumcar.ui.activities.detail.DetailViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class DetailModule{
    @Provides
    fun provideDetailViewModelFactory(driver: DataDriver) = DetailViewModelFactory(driver)
}