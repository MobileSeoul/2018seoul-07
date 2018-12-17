package com.jiwoo.choi.nanumcar.ui.activities.splash

import android.Manifest
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.google.gson.Gson
import com.jiwoo.choi.dobike.util.PermissionController
import com.jiwoo.choi.gwangju_contest.extension.plusAssign
import com.jiwoo.choi.gwangju_contest.rx.AutoClearedDisposable
import com.jiwoo.choi.nanumcar.R
import com.jiwoo.choi.nanumcar.api.model.branch.BranchModel
import com.jiwoo.choi.nanumcar.api.model.car.CarModel
import com.jiwoo.choi.nanumcar.api.model.notice.NoticeModel
import com.jiwoo.choi.nanumcar.api.model.park.ParkModel
import com.jiwoo.choi.nanumcar.api.model.theme.ThemeModel
import com.jiwoo.choi.nanumcar.ui.activities.main.MainActivity
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.rxkotlin.Observables
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class SplashActivity : DaggerAppCompatActivity() , PermissionController.CallBack,AnkoLogger {

    val hd = Handler()
    @Inject
    lateinit var viewModelFactory: SplashViewModelFactory
    lateinit var viewModel : SplashViewModel
    val disposable = AutoClearedDisposable(this)
    val viewDisposables = AutoClearedDisposable(lifecycleOwner = this,alwaysClearOnStop = false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        PermissionController(this, arrayOf(
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_FINE_LOCATION
        )).checkVersion()
//        splashView.setAnimation("esplash.json")
//        splashView.playAnimation()
//        splashView.loop(true)
//        splashView.apply {
//            enableMergePathsForKitKatAndAbove(true)
//            loop(true)
//            setAnimation("esplash.json")
//        }

    }

    override fun init() {
        info { "init 실행" }
        lifecycle += viewDisposables
        viewModel = ViewModelProviders.of(this, viewModelFactory)[SplashViewModel::class.java]

        info { "start" }
        disposable += Observables.combineLatest(
                viewModel.noticeApi.getNotice(),
                viewModel.parkApi.getPark(),
                viewModel.themeApi.getTheme(),
                viewModel.carApi.getCarList(),
                viewModel.branchApi.getBranchList()
        ){ a1,a2,a3,a4,a5 -> arrayOf(a1,a2,a3,a4,a5) }.subscribe({
            info { it }
            viewModel.driver.apply {
                noticeDriver.onNext(it[0] as NoticeModel)
                parkDriver.onNext(it[1] as ParkModel)
                themeDriver.onNext(it[2] as ThemeModel)
                carDriver.onNext(it[3] as CarModel)
                branchDriver.onNext(it[4] as BranchModel)
            }
            hd.postDelayed({
                startActivity<MainActivity>()
                finish()
            },3000)
        },{
            it.printStackTrace()
        })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == PermissionController.REQ_FLAG) {
            if (PermissionController.onCheckResult(grantResults)) {
                info { "사용자 확정" }
                init()
            } else {
                Toast.makeText(this, "권한을 허용하지 않으시면 프로그램을 실행할 수 없습니다.", Toast.LENGTH_LONG).show()
            }
        }
    }
}
