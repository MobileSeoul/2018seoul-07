package com.jiwoo.choi.nanumcar.api.inter

import com.jiwoo.choi.nanumcar.api.model.car.CarModel
import io.reactivex.Observable
import retrofit2.http.GET

interface CarApi{
    @GET("car/")
    fun getCarList() : Observable<CarModel>
}