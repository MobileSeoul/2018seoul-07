package com.jiwoo.choi.nanumcar.adapter.recycler.info

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.jiwoo.choi.nanumcar.GlideApp
import com.jiwoo.choi.nanumcar.R
import com.jiwoo.choi.nanumcar.api.model.car.Result


class InfoDetailAdapter(val list : List<Result>,val context : Context) : RecyclerView.Adapter<InfoDetailAdapter.InfoDetailViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoDetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_info_detail,parent,false)
        return InfoDetailViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: InfoDetailViewHolder, position: Int) {
        holder.apply {
            list[position].let {
                txtName.text = it.model
                txtCarType.text = it.brand
                txtPrice.text = it.pricePerKm
                GlideApp
                        .with(context)
                        .load(it.image)
                        .into(img)

                if(it.brand == "쏘카"){
                    txtSubStitle.text = "하루당"
                    txtSubPrice.text = it.pricePerDay
                }else{
                    txtSubStitle.text = "1분당"
                    txtSubPrice.text =it.pricePerTenMinute
                }
            }
        }
    }

    inner class InfoDetailViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val txtName = view.findViewById<TextView>(R.id.txtInfoDetailName)
        val txtCarType = view.findViewById<TextView>(R.id.txtInDeCarType)
        val txtPrice = view.findViewById<TextView>(R.id.txtIndePrice)
        val txtSubPrice = view.findViewById<TextView>(R.id.txtInDeSubPrice)
        val txtSubStitle = view.findViewById<TextView>(R.id.txtInDeSubTitle)
        val img = view.findViewById<ImageView>(R.id.imgInfoDe)
    }
}