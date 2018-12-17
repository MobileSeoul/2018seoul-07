package com.jiwoo.choi.nanumcar.ui.activities.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.jiwoo.choi.nanumcar.di.driver.DataDriver

class MainViewModelFactory(val driver: DataDriver): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(driver) as T
    }
}