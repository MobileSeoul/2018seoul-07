package com.jiwoo.choi.nanumcar.adapter.recycler.theme

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jiwoo.choi.nanumcar.R
import com.jiwoo.choi.nanumcar.api.model.theme.ThemeModel
import org.jetbrains.anko.find


class ThemeAdapter(themeModel: ThemeModel ,val context : Context) : RecyclerView.Adapter<ThemeAdapter.ThemeViewHolder>(){
    val items = themeModel.results?.groupBy { it?.title }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_theme,parent,false)
        return ThemeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items?.keys?.size!!
    }

    override fun onBindViewHolder(holder: ThemeViewHolder, position: Int) {
        holder.apply {
            items?.keys?.toList()!![position].let {
                txtTitle.text = "#${it}"
            }
            items?.values?.toList()!![position].let {
                recyclerView.adapter = ThemeDetailAdapter(it,context)
                recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            }
        }
    }

    inner class ThemeViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val txtTitle = view.findViewById<TextView>(R.id.txtThemeTitle)
        val recyclerView = view.findViewById<RecyclerView>(R.id.themeDetailRecycler)
    }
}