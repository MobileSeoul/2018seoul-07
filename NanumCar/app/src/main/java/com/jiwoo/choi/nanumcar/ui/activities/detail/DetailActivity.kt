package com.jiwoo.choi.nanumcar.ui.activities.detail

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.graphics.Color
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.jiwoo.choi.gwangju_contest.extension.plusAssign
import com.jiwoo.choi.gwangju_contest.extension.runOnIoScheduler
import com.jiwoo.choi.gwangju_contest.rx.AutoClearedDisposable
import com.jiwoo.choi.nanumcar.GlideApp
import com.jiwoo.choi.nanumcar.R
import com.jiwoo.choi.nanumcar.R.id.*
import com.jiwoo.choi.nanumcar.adapter.recycler.detail.DetailAdapter
import com.jiwoo.choi.nanumcar.api.model.theme.Result
import com.jiwoo.choi.nanumcar.ui.activities.main.tempLocation
import com.jiwoo.choi.nanumcar.util.KakaoNavi
import com.jiwoo.choi.nanumcar.util.TMapUtils
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import javax.inject.Inject
import com.skt.Tmap.TMapData
import com.skt.Tmap.TMapPolyLine
import com.skt.Tmap.TMapPoint
import org.jetbrains.anko.toast


class DetailActivity : DaggerAppCompatActivity(), AnkoLogger {


    @Inject
    lateinit var viewModelFactory: DetailViewModelFactory
    lateinit var viewModel: DetailViewModel
    val disposable = AutoClearedDisposable(this)
    val viewDisposables = AutoClearedDisposable(lifecycleOwner = this, alwaysClearOnStop = false)
    var theme: Result? = null
    var tMapPolyLine: TMapPolyLine? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        lifecycle += viewDisposables
        viewModel = ViewModelProviders.of(this, viewModelFactory)[DetailViewModel::class.java]
        disposable += viewModel
                .driver
                .themeDriver
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    theme = it.results?.first { it?.number == intent.extras["name"] }!!
                    setThemeView(theme!!)
                    disposable += viewModel
                            .driver
                            .branchDriver
                            .subscribe({
                                val branchs = it.results.filter { it.titleNumber == theme?.number }
                                info { branchs }
                                setBranchView(branchs)
                            }, {
                                it.printStackTrace()
                            })
                }, {
                    it.printStackTrace()
                })

    }

    fun setThemeView(item: Result) {
        GlideApp
                .with(this)
                .load(item?.image)
                .into(imgDe)
        txtDeInfo.text = item?.information
        txtDeTitle.text = item?.dcName
        txtDeSub.text = item.location
    }

    fun setBranchView(item: List<com.jiwoo.choi.nanumcar.api.model.branch.Result>) {
        detailrecyclerView.apply {
            info { "실행" }
            adapter = DetailAdapter(this@DetailActivity, item)
            layoutManager = LinearLayoutManager(this@DetailActivity)
        }

        btnDetail.setOnClickListener {
            KakaoNavi.startNavi(item.first().lat, item.first().lon,item.first().name,this)
        }

        if (tempLocation == null) toast("현 위치를 불러올 수 없습니다").show()
        else {
            runOnIoScheduler {
                val tMapPointStart = TMapPoint(tempLocation?.latitude!!, tempLocation?.longitude!!) // SKT타워(출발지)
                val tMapPointEnd = TMapPoint(item.first().lat,
                        item.first().lon) // N서울타워(목적지)
                try {
                    val centerPolyLine = arrayOf(
                            ((tMapPointStart.latitude + item.first().lat) / 2),
                            ((tMapPointStart.longitude + item.first().lon) / 2)
                    )
                    tMapPolyLine = TMapData().findPathData(tMapPointStart, tMapPointEnd)
                    tMapPolyLine?.lineColor = Color.BLUE
                    tMapPolyLine?.lineWidth = 2f
                    runOnUiThread {
                        detailMapview.addTMapPolyLine("Line1", tMapPolyLine)
                        detailMapview.apply {
                            setCenterPoint(
                                    centerPolyLine[1],centerPolyLine[0]
                            )
                            addTMapPolyLine("Line1", tMapPolyLine)
                            zoomLevel = 12
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }


}
