package com.jiwoo.choi.nanumcar.ui.fragments.park

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.jiwoo.choi.dobike.const.sections
import com.jiwoo.choi.gwangju_contest.extension.getPriceIcon
import com.jiwoo.choi.gwangju_contest.extension.plusAssign

import com.jiwoo.choi.nanumcar.R
import com.jiwoo.choi.nanumcar.R.id.btnParkMyLocation
import com.jiwoo.choi.nanumcar.R.id.parkSectionRecyclerview
import com.jiwoo.choi.nanumcar.adapter.recycler.park.ParkSectionAdapter
import com.jiwoo.choi.nanumcar.api.model.park.ParkModel
import com.jiwoo.choi.nanumcar.di.fragments.modules.ParkModule
import com.jiwoo.choi.nanumcar.ui.dialogs.ParkDialog
import com.jiwoo.choi.nanumcar.ui.dialogs.TourDialog
import dagger.android.support.DaggerFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.fragment_park.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import javax.inject.Inject

class ParkFragment : DaggerFragment(), AnkoLogger, OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener {
    @Inject
    lateinit var viewModelFactory: ParkViewModelFactory
    lateinit var viewModel: ParkViewModel

    var mapView: MapView? = null
    var model: ParkModel? = null

    var googleMap : GoogleMap? = null

    var locationManager: LocationManager? = null
    var locationListener: LocationListener? = null
    var tempLocation: Location? = null
    val parkSectionDriver = BehaviorSubject.create<String>()
    var fragmentView : View? = null
    var flag = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_park, container, false)
        fragmentView = view
        viewModel = ViewModelProviders.of(this, viewModelFactory)[ParkViewModel::class.java]
        bindGps()
        viewModel
                .driver
                .parkDriver
                .subscribe({
                    model = it
                }, {
                    it.printStackTrace()
                })
        mapView = view.findViewById(R.id.mapView)
        mapView?.getMapAsync(this)
        mapView?.onCreate(savedInstanceState)
        return view
    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        if(!flag){
            flag = !flag
            Snackbar.make(fragmentView!!,"마커 위 정보창을 누르면 상세 페이지로 이동합니다",Snackbar.LENGTH_SHORT).show()
        }
        return false
    }

    override fun onInfoWindowClick(marker: Marker?) {
        val temp = model?.results?.filter { it?.title == marker?.title }?.first()
        val dialog = ParkDialog(activity!!,temp!!)
        dialog!!.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.show()
    }

    override fun onMapReady(map: GoogleMap?) {
        val temp = sections.items.filter { it.section == "강남구" }.first()// 강남구 주소로 초기세팅
        val location = LatLng(temp.lat.toDouble(), temp.long.toDouble())

        googleMap = map

        map?.setOnMarkerClickListener(this)
        map?.setOnInfoWindowClickListener(this)

        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 13.5f))
        for (i in model?.results!!) {
            try {
                val marker = MarkerOptions()
                        .position(LatLng(i?.lat!!, i?.lon!!))
                        .title(i.title)
                        .snippet(i.locationJibun)
                        .icon(BitmapDescriptorFactory.fromResource(i.priceType.getPriceIcon()))
                map?.addMarker(marker)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()

        parkSectionRecyclerview.apply {
            adapter = ParkSectionAdapter(parkSectionDriver)
            layoutManager = LinearLayoutManager(activity!!)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }

        parkSectionDriver
                .subscribe({ str ->
                    val item = sections.items.filter { it.section == str }.first()
                    googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(item.lat.toDouble(),item.long.toDouble()), 13.5f))
                }, {
                    it.printStackTrace()
                })

        btnParkMyLocation.setOnClickListener {
            if(tempLocation != null){
                googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(tempLocation?.latitude!!,tempLocation?.longitude!!),13.5f))
            }else{
                activity!!.toast("위치정보를 얻을수 없습니다. \n GPS를 확인해주세요.")
            }

        }
    }


    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    @SuppressLint("MissingPermission")
    fun bindGps() {
        locationManager = activity!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location?) {
                location.let {
                    tempLocation = it
//                    val temp = Geocoder(activity!!).getFromLocation(location?.latitude!!,location?.longitude!!,1) // 위도경도정보 받아오기
                }
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                info { "status : $status" }
            }

            override fun onProviderEnabled(provider: String?) {}

            override fun onProviderDisabled(provider: String?) {}
        }

        locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1.0f, locationListener!!)
        locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1.0f, locationListener!!)
    }
}
