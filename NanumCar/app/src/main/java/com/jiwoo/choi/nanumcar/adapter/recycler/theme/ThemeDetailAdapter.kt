package com.jiwoo.choi.nanumcar.adapter.recycler.theme

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.jiwoo.choi.nanumcar.GlideApp
import com.jiwoo.choi.nanumcar.R
import com.jiwoo.choi.nanumcar.api.model.theme.Result
import com.jiwoo.choi.nanumcar.ui.activities.detail.DetailActivity
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.w3c.dom.Text


class ThemeDetailAdapter(val list: List<Result?>, val context: Context) : RecyclerView.Adapter<ThemeDetailAdapter.ThemeDetailViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeDetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_theme_detail,parent,false)
        return ThemeDetailViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ThemeDetailViewHolder, position: Int) {
        holder.apply {
            list[position]!!.let {
                item = it
                title.text = it.dcName
                subscription.text = it.location
                GlideApp
                        .with(context)
                        .load(it.image)
                        .into(img)
            }
        }
    }

    inner class ThemeDetailViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val title = view.findViewById<TextView>(R.id.txtThDeTitle)
        val subscription = view.findViewById<TextView>(R.id.txtThDeSubscription)
        val img = view.findViewById<ImageView>(R.id.imgThDe)
        var item : Result? = null
        init {
            img.setColorFilter(Color.argb(100, 0, 0, 0))
            view.setOnClickListener {
                context.startActivity<DetailActivity>(
                        "name" to item?.number
                )
            }
        }
    }
}