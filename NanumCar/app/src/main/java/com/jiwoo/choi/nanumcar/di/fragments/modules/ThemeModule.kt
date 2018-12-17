package com.jiwoo.choi.nanumcar.di.fragments.modules

import com.jiwoo.choi.nanumcar.di.driver.DataDriver
import com.jiwoo.choi.nanumcar.ui.fragments.theme.ThemeViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ThemeModule{
    @Provides
    fun provideThemeViewModelFactory(driver: DataDriver) = ThemeViewModelFactory(driver)

}