package com.jiwoo.choi.nanumcar.ui.activities.nanum

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class NanumViewModelFactory : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NanumViewModel() as T
    }
}
