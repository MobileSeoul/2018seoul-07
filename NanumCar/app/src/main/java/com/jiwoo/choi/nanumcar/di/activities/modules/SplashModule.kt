package com.jiwoo.choi.nanumcar.di.activities.modules

import com.jiwoo.choi.nanumcar.api.inter.*
import com.jiwoo.choi.nanumcar.di.driver.DataDriver
import com.jiwoo.choi.nanumcar.ui.activities.splash.SplashViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class SplashModule{
    @Provides
    fun provideSplashViewModelFactory(driver: DataDriver,
                                      parkApi: ParkApi,
                                      noticeApi: NoticeApi,
                                      themeApi: ThemeApi,
                                      carApi: CarApi,
                                      branchApi: BranchApi) = SplashViewModelFactory(driver,parkApi,noticeApi,themeApi,carApi,branchApi)
}
