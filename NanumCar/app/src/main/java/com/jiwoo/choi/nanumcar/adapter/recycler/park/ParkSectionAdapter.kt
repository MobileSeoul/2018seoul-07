package com.jiwoo.choi.nanumcar.adapter.recycler.park

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jiwoo.choi.dobike.api.model.section.Item
import com.jiwoo.choi.dobike.const.sections
import com.jiwoo.choi.nanumcar.R
import io.reactivex.subjects.BehaviorSubject


class ParkSectionAdapter(val driver : BehaviorSubject<String>) : RecyclerView.Adapter<ParkSectionAdapter.ParkSectionViewHolder>(){
    val items = sections.items.sortedBy { it.section }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkSectionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_section,parent,false)
        return ParkSectionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return sections.items.size
    }

    override fun onBindViewHolder(holder: ParkSectionViewHolder, position: Int) {
        holder.apply {
            items[position].let {
                item = it
                txtTitle.text = it.section
            }
        }
    }

    inner class ParkSectionViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var item : Item? = null
        val txtTitle = view.findViewById<TextView>(R.id.txtSection)
        init {
            view.setOnClickListener {
                driver.onNext(item!!.section)
            }
        }
    }
}