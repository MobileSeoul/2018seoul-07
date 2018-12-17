package com.jiwoo.choi.nanumcar.ui.fragments.info

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.widget.textChanges
import com.jiwoo.choi.gwangju_contest.extension.plusAssign

import com.jiwoo.choi.nanumcar.R
import com.jiwoo.choi.nanumcar.adapter.recycler.info.InfoAdapter
import com.jiwoo.choi.nanumcar.api.model.car.CarModel
import com.jiwoo.choi.nanumcar.api.model.car.Result
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_info.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import javax.inject.Inject


class InfoFragment : DaggerFragment(), AnkoLogger {

    @Inject
    lateinit var viewModelFactory: InfoViewModelFactory
    lateinit var viewModel: InfoViewModel
    var model: CarModel? = null
    var currentCompany: String = "쏘카"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProviders.of(this, viewModelFactory)[InfoViewModel::class.java]
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onResume() {
        super.onResume()
        bind()
    }

    fun bind() {
        editInfo.clearFocus()

        viewModel
                .driver
                .carDriver
                .subscribe({

                    model = it
                    info { model }
                    infoRecyclerView.apply {
                        adapter = InfoAdapter(model!!, activity!!, "쏘카")
                        layoutManager = LinearLayoutManager(activity!!)
                        adapter.notifyDataSetChanged()
                    }
                }, {
                    it.printStackTrace()
                })
        btnInfoGreenCar.setOnClickListener {
            currentCompany = "그린카"
            btnInfoGreenCar.setTypeface(null, Typeface.BOLD)
            btnInfoSocar.setTypeface(null, Typeface.NORMAL)
            infoRecyclerView.apply {
                adapter = InfoAdapter(model!!, activity!!, currentCompany)
                layoutManager = LinearLayoutManager(activity!!)
                adapter.notifyDataSetChanged()
            }
        }

        btnInfoSocar.setOnClickListener {
            currentCompany = "쏘카"
            btnInfoSocar.setTypeface(null, Typeface.BOLD)
            btnInfoGreenCar.setTypeface(null, Typeface.NORMAL)
            infoRecyclerView.apply {
                adapter = InfoAdapter(model!!, activity!!, currentCompany)
                layoutManager = LinearLayoutManager(activity!!)
                adapter.notifyDataSetChanged()
            }
        }

        editInfo.textChanges().subscribe({ text ->
            infoRecyclerView.apply {
                adapter = InfoAdapter(model!!, activity!!, currentCompany, text.toString())
                layoutManager = LinearLayoutManager(activity!!)
                adapter.notifyDataSetChanged()

            }
        }, {
            it.printStackTrace()
        })
    }
}
