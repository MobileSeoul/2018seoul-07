package com.jiwoo.choi.nanumcar.ui.fragments.navi


import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.widget.textChanges
import com.jiwoo.choi.gwangju_contest.extension.plusAssign

import com.jiwoo.choi.nanumcar.R
import com.jiwoo.choi.nanumcar.R.id.*
import com.jiwoo.choi.nanumcar.adapter.recycler.navi.NaviAdapter
import com.jiwoo.choi.nanumcar.util.TMapUtils
import com.skt.Tmap.TMapData
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_navi.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import javax.inject.Inject

class NaviFragment : DaggerFragment() , AnkoLogger{

    @Inject
    lateinit var viewModelFactory: NaviViewModelFactory
    lateinit var viewModel: NaviViewModel
    var currentString = ""
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        viewModel = ViewModelProviders.of(this, viewModelFactory)[NaviViewModel::class.java]
        return inflater.inflate(R.layout.fragment_navi, container, false)
    }

    override fun onResume() {
        super.onResume()
        TMapUtils.getPoi().findAddressPOI("광화문"){
            activity!!.runOnUiThread {
                naviRecycler.apply {
                    adapter = NaviAdapter(activity!!, it)
                    layoutManager = LinearLayoutManager(activity)
                    adapter.notifyDataSetChanged()
                    addItemDecoration(DividerItemDecoration(activity, 1))
                }
            }
        }

        editNavi
                .textChanges()
                .subscribe({
                    currentString = it.toString()
                }, {
                    it.printStackTrace()
                })

        btnNavi.setOnClickListener {
            try {
                TMapUtils.getPoi().findAddressPOI(currentString){
                    activity!!.runOnUiThread {
                        naviRecycler.apply {
                            adapter = NaviAdapter(activity!!, it)
                            layoutManager = LinearLayoutManager(activity)
                            adapter.notifyDataSetChanged()
                            addItemDecoration(DividerItemDecoration(activity, 1))
                        }
                    }
                }
            }catch (e: Exception){
                e.printStackTrace()
                activity!!.toast("찾으시는 지점이 없거나, 잘못 입력하셨습니다.")
            }
        }


    }

}
