package com.example.myteamspage.Activities.ScheduleGames

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.myteamspage.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class GameSchedule : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location
    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private lateinit var searchButton: Button

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_schedule)
        val teamName = intent.getStringExtra("gameTeam").toString().uppercase()
        val opponentName = intent.getStringExtra("gameOpponent").toString().uppercase()
        val dateGame = intent.getStringExtra("gameDate").toString()

        findViewById<TextView>(R.id.my_teams).setText("$teamName - $opponentName")
        findViewById<ImageView>(R.id.gameScheduleTeam1Img).setImageResource(R.drawable.manchestercity)
        findViewById<ImageView>(R.id.gameScheduleTeam2Img).setImageResource(R.drawable.arsenal)
        findViewById<TextView>(R.id.gameScheduleTeam1Tv).setText(teamName)
        findViewById<TextView>(R.id.gameScheduleTeam2Tv).setText(opponentName)
        findViewById<TextView>(R.id.gameScheduleDateTv).setText(dateGame)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)
        setUpMap()

        val gameAddress = intent.getStringExtra("gameAddress").toString()
        searchLocation(gameAddress)

        mMap.setOnMapLongClickListener { latLng ->
            mMap.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title("Your marker title")
                    .snippet("Your marker snippet")
            )
        }
    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }
        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            if (location != null) {
                lastLocation = location
            }
        }
    }

    private fun searchLocation(gameAddress: String) {
        if (gameAddress.isNotEmpty()) {
            val geocoder = Geocoder(this)
            val addressList = geocoder.getFromLocationName(gameAddress, 1)
            if (addressList != null && addressList.isNotEmpty()) {
                val address = addressList[0]
                val latLng = LatLng(address.latitude, address.longitude)
                mMap.addMarker(
                    MarkerOptions()
                        .position(latLng)
                        .title(address.getAddressLine(0))
                        .snippet("Your marker snippet")
                )
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12f))
            } else {
                Toast.makeText(this, "Location not found", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please enter a location", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onMarkerClick(marker: Marker): Boolean {
        // Handle marker click event here
        return false
    }
}
