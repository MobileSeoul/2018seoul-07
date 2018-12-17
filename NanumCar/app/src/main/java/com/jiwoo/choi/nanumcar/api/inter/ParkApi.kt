package com.jiwoo.choi.nanumcar.api.inter

import com.jiwoo.choi.nanumcar.api.model.park.ParkModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ParkApi{
    @GET("park/")
    fun getPark(
            @Query("count") count : Int = 730
    ) : Observable<ParkModel>
}