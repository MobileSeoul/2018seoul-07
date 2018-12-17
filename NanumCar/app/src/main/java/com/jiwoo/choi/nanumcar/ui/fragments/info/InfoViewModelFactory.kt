package com.jiwoo.choi.nanumcar.ui.fragments.info

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.jiwoo.choi.nanumcar.di.driver.DataDriver

class InfoViewModelFactory(val driver: DataDriver) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return InfoViewModel(driver) as T
    }
}