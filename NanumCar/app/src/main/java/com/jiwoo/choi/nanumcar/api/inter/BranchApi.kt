package com.jiwoo.choi.nanumcar.api.inter

import com.jiwoo.choi.nanumcar.api.model.branch.BranchModel
import io.reactivex.Observable
import retrofit2.http.GET

interface BranchApi{
    @GET("branch/")
    fun getBranchList() : Observable<BranchModel>
}