package com.jiwoo.choi.nanumcar.di.fragments.modules

import com.jiwoo.choi.nanumcar.di.driver.DataDriver
import com.jiwoo.choi.nanumcar.ui.fragments.navi.NaviViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class NaviModule{
    @Provides
    fun provideNaviViewModelFactory(driver: DataDriver) = NaviViewModelFactory(driver)
}
