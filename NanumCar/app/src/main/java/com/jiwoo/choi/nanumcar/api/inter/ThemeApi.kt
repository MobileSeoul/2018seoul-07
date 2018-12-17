package com.jiwoo.choi.nanumcar.api.inter

import com.jiwoo.choi.nanumcar.api.model.park.ParkModel
import com.jiwoo.choi.nanumcar.api.model.theme.ThemeModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ThemeApi{
    @GET("theme/")
    fun getTheme() : Observable<ThemeModel>
}