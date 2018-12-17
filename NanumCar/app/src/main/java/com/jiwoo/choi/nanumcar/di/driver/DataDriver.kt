package com.jiwoo.choi.nanumcar.di.driver

import com.jiwoo.choi.nanumcar.api.model.branch.BranchModel
import com.jiwoo.choi.nanumcar.api.model.car.CarModel
import com.jiwoo.choi.nanumcar.api.model.notice.NoticeModel
import com.jiwoo.choi.nanumcar.api.model.park.ParkModel
import com.jiwoo.choi.nanumcar.api.model.theme.Result
import com.jiwoo.choi.nanumcar.api.model.theme.ThemeModel
import com.jiwoo.choi.nanumcar.di.fragments.modules.ThemeModule
import io.reactivex.subjects.BehaviorSubject

class DataDriver{
    val noticeDriver = BehaviorSubject.create<NoticeModel>()
    val parkDriver = BehaviorSubject.create<ParkModel>()
    val themeDriver = BehaviorSubject.create<ThemeModel>()
    val carDriver = BehaviorSubject.create<CarModel>()
    val branchDriver = BehaviorSubject.create<BranchModel>()
    val detailDriver = BehaviorSubject.create<Result>()
}