package com.jiwoo.choi.nanumcar.adapter.recycler.tour

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.jiwoo.choi.dobike.api.model.tour.TourModel
import com.jiwoo.choi.gwangju_contest.extension.convertType
import com.jiwoo.choi.nanumcar.GlideApp
import com.jiwoo.choi.nanumcar.R
import com.jiwoo.choi.nanumcar.api.model.tour.TourModel2
import com.jiwoo.choi.nanumcar.ui.dialogs.TourDialog


class TourAdapter(tourModel: TourModel2, val context : Activity) : RecyclerView.Adapter<TourAdapter.TourViewHolder>(){
     var items = tourModel.body.items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tour,parent,false)
        return TourViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: TourViewHolder, position: Int) {
        holder.apply {
            items[position].let{
                item = it
                txtTitle.text = it.title
                txtLocation.text = it.tel ?: "전화번호 없음"
                txtType.text = it.addr1 ?: "제공주소 없음"

                if(it.firstimage != null && it.firstimage != ""){
                    GlideApp
                            .with(context)
                            .load(it.firstimage)
                            .into(img)
                }else{
                    GlideApp
                            .with(context)
                            .load(R.drawable.default_tour_img)
                            .into(img)
                }
            }
        }
    }

    inner class TourViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var item : TourModel2.BodyBean.ItemBean? = null
        val txtTitle = view.findViewById<TextView>(R.id.txtTourTitle)
        val txtLocation = view.findViewById<TextView>(R.id.txtTourLocation)
        val txtType = view.findViewById<TextView>(R.id.txtTourType)
        val img = view.findViewById<ImageView>(R.id.imgTour)
        init {
            img.setColorFilter(Color.argb(100, 0, 0, 0))

            view.setOnClickListener {
                val dialog = TourDialog(context,item!!)
                dialog!!.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog!!.show()
            }
        }
    }
}