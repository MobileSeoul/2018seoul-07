package com.jiwoo.choi.nanumcar.adapter.recycler.theme

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jiwoo.choi.nanumcar.R
import com.jiwoo.choi.nanumcar.api.model.notice.NoticeModel
import com.jiwoo.choi.nanumcar.api.model.notice.Result
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivity


class NoticeAdapter(noticeModel: NoticeModel,
                    val context: Context) : RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>(), AnkoLogger{
    val items = noticeModel.results
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notice,parent,false)
        return NoticeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.apply {
            items[position].let {
                item = it
                txtNotice.text = it.notice
                txtNoticeBrand.text = it.company
                info { it }
                if(it.company == "쏘카") txtNoticeBrand.setTextColor(Color.argb(255, 58, 179, 231))
                else  txtNoticeBrand.setTextColor(Color.argb(255, 93, 193, 117))

            }
        }
    }

    inner class NoticeViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var item : Result? = null
        val txtNotice = view.findViewById<TextView>(R.id.txtNotice)
        val txtNoticeBrand = view.findViewById<TextView>(R.id.txtNoticeBrand)
        init {
            view.setOnClickListener {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item?.link)))
            }
        }
    }
}