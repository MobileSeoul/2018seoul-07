package com.jiwoo.choi.nanumcar.ui.dialogs

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.Window
import com.jiwoo.choi.nanumcar.R
import com.jiwoo.choi.nanumcar.api.model.park.Result
import com.jiwoo.choi.nanumcar.util.KakaoNavi
import kotlinx.android.synthetic.main.dialog_park.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class ParkDialog(var activity: Activity?, val item : Result) : Dialog(activity), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_park)

        txtPaDiLocation.text = item.locationRoad
        txtPaDiLearing.text = item.learningDate
        txtPaDiOperation.text = item.operation
        txtPaDiOrg.text = item.org
        txtPaDiPriceType.text = item.priceType
        txtPaDiTitle.text = item.title
        txtPaDiType2.text = item.type2
        txtPaDiQunatity.text = item.quantity.toString()
        txtPaDiTel.text = item.tel ?: "전화번호 없음"

        btnPaDiNavi.setOnClickListener {
            KakaoNavi.startNavi(
                    item?.lat!!,
                    item?.lon!!,
                    item?.title!!,
                    activity!!
            )
        }

        btnPaDiTel.setOnClickListener {
            if(item.tel == null && item.tel == ""){
                activity!!.toast("전화번호가 없습니다").show()
            }else{
                activity!!.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:${item.tel}")))
            }
        }
    }

    override fun onClick(v: View) {
        dismiss()
    }
}