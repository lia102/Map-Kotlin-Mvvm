package com.hamidreza.maptask.ui

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.hamidreza.maptask.R
import com.hamidreza.maptask.databinding.ActivityMapBinding
import com.hamidreza.maptask.utils.Conts.DEFAULT_INTERVAL_IN_MILLISECONDS
import com.hamidreza.maptask.utils.Conts.DEFAULT_MAX_WAIT_TIME
import com.mapbox.android.core.location.*
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.mapbox.mapboxsdk.location.LocationComponentOptions
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.location.modes.RenderMode
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import ir.map.sdk_map.MapirStyle
import ir.map.sdk_map.maps.MapView
import java.lang.Exception
import java.lang.ref.WeakReference

class MapActivity : AppCompatActivity() {
    lateinit var binding: ActivityMapBinding
    lateinit var map: MapboxMap
    lateinit var mapStyle: Style
    lateinit var mapView: MapView
    private lateinit var lastKnowLatLng: LatLng
    private lateinit var locationEngine: LocationEngine
    private val callback: MyLocationCallback = MyLocationCallback(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mapView = binding.mapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync { mapbox ->
            map = mapbox
            map.setStyle(Style.Builder().fromUri(MapirStyle.MAIN_MOBILE_VECTOR_STYLE)) { style ->
                mapStyle = style
                enableLocationComponent()
            }

        }
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    private class MyLocationCallback(val activity: MapActivity) :
        LocationEngineCallback<LocationEngineResult> {
        private val activityWeakReference: WeakReference<MapActivity> = WeakReference(activity)
        override fun onSuccess(result: LocationEngineResult?) {
            val activity: MapActivity = activityWeakReference?.get()!!
            if (activity != null) {
                val location = result?.lastLocation
                if (location == null) return
                activity.lastKnowLatLng = LatLng(location.latitude, location.longitude)
                //Toast.makeText(activity, "${activity.lastKnowLatLng.latitude},${activity.lastKnowLatLng.longitude}", Toast.LENGTH_SHORT).show()
                if (activity.map != null && result.lastLocation != null)
                    activity.map.locationComponent.forceLocationUpdate(result.lastLocation)
            }
        }

        override fun onFailure(exception: Exception) {
            val activity: MapActivity = activityWeakReference?.get()!!
            if (activity != null)
                Toast.makeText(activity, " موقیعت مکانی خود را فعال کنید", Toast.LENGTH_LONG).show()
        }

    }

    @SuppressLint("MissingPermission")
    private fun enableLocationComponent() {
        if (PermissionsManager.areLocationPermissionsGranted(this)) {
            val customLocationComponentOptions = LocationComponentOptions.builder(this)
                .elevation(5f)
                .accuracyAlpha(.6f)
                .accuracyColor(Color.RED)
                .build()

            val locationComponent = map.locationComponent
            val locationComponentActivationOptions =
                LocationComponentActivationOptions.builder(this, mapStyle)
                    .useDefaultLocationEngine(false)
                    .locationComponentOptions(customLocationComponentOptions)
                    .build()

            locationComponent.activateLocationComponent(locationComponentActivationOptions);
            locationComponent.isLocationComponentEnabled = true
            locationComponent.setCameraMode(CameraMode.TRACKING)
            locationComponent.setRenderMode(RenderMode.COMPASS)

            initLocationEngine()

            locationComponent.addOnLocationClickListener {

            }
        } else {
            val permissionsManager = PermissionsManager(object : PermissionsListener {
                override fun onExplanationNeeded(permissionsToExplain: MutableList<String>?) {

                }

                override fun onPermissionResult(granted: Boolean) {
                    if (granted)
                        enableLocationComponent()
                    else
                        Toast.makeText(this@MapActivity, "Permission Denied", Toast.LENGTH_LONG)
                            .show()
                }
            })
            permissionsManager.requestLocationPermissions(this)
        }
    }

    @SuppressLint("MissingPermission")
    private fun initLocationEngine() {
        locationEngine = LocationEngineProvider.getBestLocationEngine(this)
        val request = LocationEngineRequest.Builder(DEFAULT_INTERVAL_IN_MILLISECONDS)
            .setPriority(LocationEngineRequest.PRIORITY_HIGH_ACCURACY)
            .setMaxWaitTime(DEFAULT_MAX_WAIT_TIME).build();
        locationEngine.requestLocationUpdates(request, callback, getMainLooper());
        locationEngine.getLastLocation(callback)
    }
}