package com.jiwoo.choi.nanumcar.di.fragments.modules

import com.jiwoo.choi.nanumcar.di.driver.DataDriver
import com.jiwoo.choi.nanumcar.ui.fragments.park.ParkViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ParkModule{
    @Provides
    fun provideParkViewModelFactory(driver: DataDriver) = ParkViewModelFactory(driver)
}
