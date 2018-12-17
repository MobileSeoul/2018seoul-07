package com.jiwoo.choi.nanumcar.adapter.recycler.navi

import android.app.Activity
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kakao.kakaonavi.KakaoNaviParams
import com.kakao.kakaonavi.KakaoNaviService
import com.kakao.kakaonavi.Location
import com.kakao.kakaonavi.NaviOptions
import com.kakao.kakaonavi.options.CoordType
import com.kakao.kakaonavi.options.RpOption
import com.kakao.kakaonavi.options.VehicleType
import com.jiwoo.choi.nanumcar.R
import com.jiwoo.choi.nanumcar.util.KakaoNavi
import com.skt.Tmap.TMapPOIItem
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


class NaviAdapter(val activity : Activity, val list : ArrayList<TMapPOIItem>) : RecyclerView.Adapter<NaviAdapter.NaviViewHolder>(), AnkoLogger{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NaviViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_navi,parent,false)
        return NaviViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NaviViewHolder, position: Int) {
        holder.apply {
            list[position].let {
                info { "$it" }
                txtTitle.text = it.name
                txtLoaction.text = "${it.upperAddrName} ${it.middleAddrName} ${it.lowerAddrName}"
                item = it
            }
        }
    }

    inner class NaviViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var item : TMapPOIItem? = null
        val txtLoaction = view.findViewById<TextView>(R.id.txtNaviLocation)
        val txtTitle = view.findViewById<TextView>(R.id.txtNaviTitle)
        val container = view.findViewById<ConstraintLayout>(R.id.naviContainer)
        init {
            container.setOnClickListener {
                info { "${item!!.noorLat} ${item!!.noorLon}" }
                KakaoNavi.startNavi( item!!.noorLat.toDouble(),item!!.noorLon.toDouble(),item!!.name,activity!!)
//                val destination = Location.newBuilder(item!!.name, item!!.noorLon.toDouble(),item!!.noorLat.toDouble()).build()
//                val options = NaviOptions
//                        .newBuilder()
//                        .setCoordType(CoordType.WGS84)
//                        .setVehicleType(VehicleType.FIRST)
//                        .setRpOption(RpOption.FAST)
//                        .build()
//                val builder = KakaoNaviParams.newBuilder(destination).setNaviOptions(options)
//                KakaoNaviService.getInstance().shareDestination(activity!!,builder.build())
            }
        }
    }
}