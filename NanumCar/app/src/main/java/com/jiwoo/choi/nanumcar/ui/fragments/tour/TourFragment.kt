package com.jiwoo.choi.nanumcar.ui.fragments.tour


import android.arch.lifecycle.ViewModelProviders
import android.media.tv.TvContract.Programs.Genres.SHOPPING
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.jakewharton.rxbinding2.widget.itemSelections
import com.jiwoo.choi.dobike.api.model.section.Item
import com.jiwoo.choi.dobike.const.sections
import com.jiwoo.choi.gwangju_contest.extension.convertTypeToInt
import com.jiwoo.choi.gwangju_contest.extension.plusAssign

import com.jiwoo.choi.nanumcar.R
import com.jiwoo.choi.nanumcar.R.id.*
import com.jiwoo.choi.nanumcar.adapter.recycler.tour.TourAdapter
import com.jiwoo.choi.nanumcar.adapter.recycler.tour.TourSectionAdapter
import com.jiwoo.choi.nanumcar.api.model.tour.TourModel2
import dagger.android.support.DaggerFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.fragment_tour.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import javax.inject.Inject

class TourFragment : DaggerFragment(), AnkoLogger {
    @Inject
    lateinit var viewModelFactory: TourViewModelFactory
    lateinit var viewModel: TourViewModel

    var tourModel: TourModel2? = null
    val tourSectionDriver = BehaviorSubject.createDefault<String>("강남구")
    var temp: Item = sections.items.filter { it.section == "강남구" }.first()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProviders.of(this, viewModelFactory)[TourViewModel::class.java]
        return inflater.inflate(R.layout.fragment_tour, container, false)
    }

    override fun onResume() {
        super.onResume()
        tourProgressBar.visibility = View.INVISIBLE

        val spinnerArray = arrayListOf("전체", "관광", "문화시설", "행사/공연", "레포츠", "숙박", "쇼핑", "음식점", "여행코스", "문화")
        tourspinner.adapter = ArrayAdapter<String>(activity!!, android.R.layout.simple_list_item_1, spinnerArray)

        tourspinner
                .itemSelections()
                .subscribe({
                    info { it }
                    viewModel
                            .tourApi
                            .getTourDataWithContenttype(lat = temp!!.lat.toDouble(),
                                    lon = temp!!.long.toDouble(),
                                    contentTypeId = spinnerArray[it].convertTypeToInt())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnSubscribe { tourProgressBar.visibility = View.VISIBLE }
                            .doOnComplete { tourProgressBar.visibility = View.INVISIBLE }
                            .subscribe({
                                info { it }
                                tourRecycler.apply {
                                    adapter = TourAdapter(it, activity!!)
                                    layoutManager = LinearLayoutManager(activity!!)
                                    adapter.notifyDataSetChanged()
                                }
                            }, {
                                it.printStackTrace()
                            })
                }, {
                    it.printStackTrace()
                })
        tourSectionRecycler.apply {
            adapter = TourSectionAdapter(tourSectionDriver)
            layoutManager = LinearLayoutManager(activity!!)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }

        tourSectionDriver
                .subscribe({ str ->
                    temp = sections.items.filter { it.section == str }.first()
                    viewModel
                            .tourApi
                            .getTourData(lat = temp.lat.toDouble(),
                                    lon = temp.long.toDouble())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnSubscribe { tourProgressBar.visibility = View.VISIBLE }
                            .doOnComplete { tourProgressBar.visibility = View.INVISIBLE }
                            .subscribe({
                                tourRecycler.apply {
                                    adapter = TourAdapter(it, activity!!)
                                    layoutManager = LinearLayoutManager(activity!!)
                                    adapter.notifyDataSetChanged()
                                }
                            }, {
                                it.printStackTrace()
                            })
                }, {
                    it.printStackTrace()
                })
    }


}
