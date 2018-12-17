package com.jiwoo.choi.nanumcar.ui.fragments.theme

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jiwoo.choi.gwangju_contest.extension.plusAssign

import com.jiwoo.choi.nanumcar.R
import com.jiwoo.choi.nanumcar.R.id.noticeRecyclerview
import com.jiwoo.choi.nanumcar.R.id.themeRecyclerview
import com.jiwoo.choi.nanumcar.adapter.recycler.theme.NoticeAdapter
import com.jiwoo.choi.nanumcar.adapter.recycler.theme.ThemeAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_theme.*
import javax.inject.Inject

class ThemeFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ThemeViewModelFactory
    lateinit var viewModel: ThemeViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[ThemeViewModel::class.java]
        return inflater.inflate(R.layout.fragment_theme, container, false)
    }

    override fun onResume() {
        super.onResume()

        viewModel
                .driver
                .apply {
                    noticeDriver
                            .subscribe({
                                noticeRecyclerview.apply {
                                    adapter = NoticeAdapter(it, activity!!)
                                    layoutManager = LinearLayoutManager(activity!!)
                                }
                            }, {
                                it.printStackTrace()
                            })

                    themeDriver
                            .subscribe({
                                themeRecyclerview.apply {
                                    adapter = ThemeAdapter(it, activity!!)
                                    layoutManager = LinearLayoutManager(activity!!)
                                }
                            },{
                                it.printStackTrace()
                            })

                }
    }
}
