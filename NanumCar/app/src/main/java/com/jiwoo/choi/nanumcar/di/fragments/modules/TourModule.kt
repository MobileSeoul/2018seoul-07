package com.jiwoo.choi.nanumcar.di.fragments.modules

import com.jiwoo.choi.gwangju_contest.api.inter.TourApi
import com.jiwoo.choi.nanumcar.di.driver.DataDriver
import com.jiwoo.choi.nanumcar.ui.fragments.tour.TourViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class TourModule{
    @Provides
    fun provideTourViewModelFactory(driver: DataDriver, tourApi: TourApi) = TourViewModelFactory(driver,tourApi)
}
