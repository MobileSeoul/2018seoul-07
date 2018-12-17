package com.jiwoo.choi.nanumcar.adapter.recycler.info

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jiwoo.choi.nanumcar.R
import com.jiwoo.choi.nanumcar.api.model.car.CarModel
import com.jiwoo.choi.nanumcar.api.model.car.Result
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


class InfoAdapter(model : CarModel,
                  val context : Context,
                  brand : String,
                  text : String? = null) : RecyclerView.Adapter<InfoAdapter.InfoViewHolder>(), AnkoLogger{

    var items : Map<String, List<Result>>
    init {
        if(text ==null) items = model.results.filter { it.brand == brand }.groupBy { it.carType }
        else items = model
                .results
                .filter { it.brand == brand }
                .filter { it.model.contains(text) }
                .groupBy { it.carType }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_info,parent,false)
        return InfoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.keys.size
    }

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        holder.apply {
            items.keys.toList()[position].let {
                txtCarType.text = "#${it}"
            }
            items.values.toList()[position].let {
                recyclerView.adapter = InfoDetailAdapter(it,context)
                recyclerView.layoutManager = LinearLayoutManager(context)
            }
        }
    }

    inner class InfoViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val txtCarType = view.findViewById<TextView>(R.id.txtInfoCarBrand)
        val recyclerView = view.findViewById<RecyclerView>(R.id.infoDetailRecyclerview)
    }
}