package com.jiwoo.choi.nanumcar.util

import android.support.v7.app.AppCompatActivity
import com.jiwoo.choi.dobike.const.T_MAP_KEY
import com.skt.Tmap.TMapData
import com.skt.Tmap.TMapTapi

class TMapUtils{
    companion object {
        var tmapApi : TMapTapi? = null
        fun setTmap(activity : AppCompatActivity){
            tmapApi = TMapTapi(activity)
            tmapApi!!.setSKTMapAuthentication(T_MAP_KEY)
        }

        fun getPoi() = TMapData()


    }
}