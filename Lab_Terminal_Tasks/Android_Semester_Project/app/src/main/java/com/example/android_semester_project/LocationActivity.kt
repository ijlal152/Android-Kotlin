package com.example.android_semester_project

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_location.*
import java.util.*

class LocationActivity : AppCompatActivity() {

    companion object {
        private val REQUEST_PERMISSION_REQUEST_CODE = 2021
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)




        currentlocationBtn.setOnClickListener {
            // check permission
            if(ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,  arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_PERMISSION_REQUEST_CODE)
            }else{
                tvAddress.text = ""
                tvAddress.text = ""
                tvAddress.text = ""
                loader.visibility = View.VISIBLE
                getCurentLocation()
            }

        }


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION_REQUEST_CODE && grantResults.size > 0){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getCurentLocation()
            }else{
                Toast.makeText(this@LocationActivity, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getCurentLocation() {
        val locationRequest = LocationRequest()
        locationRequest.interval = 10000
        locationRequest.fastestInterval = 5000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        val geocoder = Geocoder(this@LocationActivity, Locale.getDefault())
        var addresses: List<Address>

        LocationServices.getFusedLocationProviderClient(this@LocationActivity)
            .requestLocationUpdates(locationRequest, object : LocationCallback(){
                @SuppressLint("SetTextI18n")
                override fun onLocationResult(locationResult: LocationResult) {
                    super.onLocationResult(locationResult)
                    LocationServices.getFusedLocationProviderClient(this@LocationActivity)
                        .removeLocationUpdates(this)
                    if (locationResult !== null && locationResult.locations.size > 0){
                        val locIndex = locationResult.locations.size - 1

                        //longitude_tv.text = "Latitude: "+locationResult.locations.get(locIndex).latitude
                        //latitude_tv.text = "Latitude: "+locationResult.locations.get(locIndex).longitude
                        val latitude = locationResult.locations.get(locIndex).latitude
                        val longitude = locationResult.locations.get(locIndex).longitude
                        latitude_tv.text = "Longitude: "+longitude
                        longitude_tv.text = "Latitude: "+latitude

                        addresses = geocoder.getFromLocation(latitude, longitude, 1)
                        var address: String = addresses[0].getAddressLine(0)
                        tvAddress.text = address
                        if (tvAddress != null) {
                            loader.visibility = View.GONE
                        }
                    }
                }
            }, Looper.getMainLooper())
    }


    /*private fun getCurentLocation() {
        val locationRequest = LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY

            LocationServices.getFusedLocationProviderClient(this@LocationActivity)
                .requestLocationUpdates(locationRequest, object: LocationCallback(){
                    @SuppressLint("SetTextI18n")
                    override fun onLocationResult(locationResult: LocationResult) {
                        super.onLocationResult(locationResult)
                        LocationServices.getFusedLocationProviderClient(this@LocationActivity)
                            .removeLocationUpdates(this)
                        if (locationResult != null && locationResult.locations.size > 0){
                            val locIndex = locationResult.locations.size-1

                            longitude_tv.text = "Latitude: "+locationResult.locations.get(locIndex).latitude
                            latitude_tv.text = "Latitude: "+locationResult.locations.get(locIndex).longitude
                        }
                    }
                }, Looper.getMainLooper())

        }
    }*/




}