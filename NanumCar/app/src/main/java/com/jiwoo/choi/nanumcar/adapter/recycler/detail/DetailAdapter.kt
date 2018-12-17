package com.jiwoo.choi.nanumcar.adapter.recycler.detail

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.jiwoo.choi.nanumcar.GlideApp
import com.jiwoo.choi.nanumcar.R
import com.jiwoo.choi.nanumcar.api.model.branch.Result
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import android.support.v7.widget.RecyclerView.ViewHolder
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class DetailAdapter(val context: Context,
                    val items : List<Result>) : RecyclerView.Adapter<DetailAdapter.DetailViewHolder>(), AnkoLogger{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_detail,parent,false)
        return DetailViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onViewDetachedFromWindow(holder: DetailViewHolder) {
        super.onViewDetachedFromWindow(holder)

        if (holder.item != null) {
            // If error still occur unpredictably, it's best to remove fragment here
            // holder.removeMapFragment();
        }
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.apply {
            items[position].let {
                item = it
                txtTitle.text = it.name
                txtInfo.text = it.information

                GlideApp
                        .with(context)
                        .load(it.image)
                        .into(img)
            }
        }
    }

    inner class DetailViewHolder(view : View) : RecyclerView.ViewHolder(view), OnMapReadyCallback{
        val txtTitle = view.findViewById<TextView>(R.id.txtDeReTitle)
        val map = view.findViewById<MapView>(R.id.mapDeRe)
        val txtInfo  = view.findViewById<TextView>(R.id.txtDeReInfo)
        val img = view.findViewById<ImageView>(R.id.imgDeRe)
        var item : Result? = null

        init {
            if (map != null){
                map.onCreate(null)
                map.onResume()
                map.getMapAsync(this)
            }
        }

        override fun onMapReady(map: GoogleMap?) {
            val location = LatLng(item?.lat!!,item?.lon!!)
            map?.addMarker(MarkerOptions()
                    .position(location)
                    .title(item?.name)
                    .snippet(item?.location)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_charge)))
            map?.animateCamera(CameraUpdateFactory.newLatLngZoom(location,13f))
        }
//        fun getMapFragmentAndCallback(callback: OnMapReadyCallback): SupportMapFragment {
//            if (mapFragment == null) {
//                mapFragment = SupportMapFragment.newInstance()
//                mapFragment.getMapAsync(callback)
//            }
//
//            // for fragment
//            // FragmentManager fragmentManager = getChildFragmentManager();
//            // for activity
//            val fragmentManager = getSupportFragmentManager()
//            fragmentManager.beginTransaction().replace(R.id.map, mapFragment).commit()
//            return mapFragment
//        }

    }
}