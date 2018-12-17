package com.jiwoo.choi.nanumcar.ui.activities.splash

import android.arch.lifecycle.ViewModel
import com.jiwoo.choi.nanumcar.api.inter.*
import com.jiwoo.choi.nanumcar.di.driver.DataDriver

class SplashViewModel(val driver: DataDriver,
                      val parkApi: ParkApi,
                      val noticeApi: NoticeApi,
                      val themeApi: ThemeApi,
                      val carApi: CarApi,
                      val branchApi: BranchApi ) : ViewModel() {
}