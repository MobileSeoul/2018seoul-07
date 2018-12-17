package com.jiwoo.choi.nanumcar.ui.fragments.tour

import android.arch.lifecycle.ViewModel
import com.jiwoo.choi.gwangju_contest.api.inter.TourApi
import com.jiwoo.choi.nanumcar.di.driver.DataDriver

class TourViewModel(val driver: DataDriver,
                    val tourApi: TourApi) : ViewModel(){
}