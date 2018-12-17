package com.jiwoo.choi.nanumcar.ui.dialogs

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import com.jiwoo.choi.gwangju_contest.extension.convertType
import com.jiwoo.choi.gwangju_contest.extension.getTourToDate
import com.jiwoo.choi.nanumcar.GlideApp
import com.jiwoo.choi.nanumcar.R
import com.jiwoo.choi.nanumcar.api.model.tour.TourModel2
import com.jiwoo.choi.nanumcar.util.KakaoNavi
import kotlinx.android.synthetic.main.dialog_tour.*

class TourDialog(var activity: Activity?, val item: TourModel2.BodyBean.ItemBean) : Dialog(activity), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_tour)
        txtDiaTourCreated.text = " 생성일 : ${item.createdtime.getTourToDate()}"
        txtDiaTourUpdate.text = " 수정일 : ${item.modifiedtime.getTourToDate()}"
        txtDiaTourLocation.text = " 주소 : ${item.addr1}"
        txtDiaTourType.text = " 타입 : ${item.contenttypeid.convertType()}"
        txtDiaTourTitle.text = item.title

        btnTourDialog.setOnClickListener {
            KakaoNavi.startNavi(
                    item.mapy.toDouble(),
                    item.mapx.toDouble(),
                    item.title,
                    activity!!
            )
        }
        imgDiaTour.setColorFilter(Color.argb(100, 0, 0, 0))

        if (item.firstimage != null && item.firstimage != "") {
            GlideApp
                    .with(context)
                    .load(item.firstimage)
                    .into(imgDiaTour)
        } else {
            GlideApp
                    .with(context)
                    .load(R.drawable.default_tour_img)
                    .into(imgDiaTour)
        }
    }

    override fun onClick(v: View) {
        dismiss()
    }
}