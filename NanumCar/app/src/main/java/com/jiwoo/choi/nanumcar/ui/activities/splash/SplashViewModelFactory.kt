package com.jiwoo.choi.nanumcar.ui.activities.splash

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.jiwoo.choi.nanumcar.api.inter.*
import com.jiwoo.choi.nanumcar.di.driver.DataDriver

class SplashViewModelFactory(val driver: DataDriver,
                             val parkApi: ParkApi,
                             val noticeApi: NoticeApi,
                             val themeApi: ThemeApi,
                             val carApi: CarApi,
                             val branchApi: BranchApi
                             ) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SplashViewModel(driver, parkApi, noticeApi, themeApi,carApi,branchApi) as T
    }
}