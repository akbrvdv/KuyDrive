package com.kelompok6.kuydrive

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.location.Geocoder
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.preference.PreferenceManager
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import org.osmdroid.config.Configuration
import org.osmdroid.events.MapListener
import org.osmdroid.events.ScrollEvent
import org.osmdroid.events.ZoomEvent
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import java.io.IOException
import java.util.Locale

class OrderMapActivity : AppCompatActivity(), MapListener {

    private lateinit var tvStatusTitle: TextView
    private lateinit var tvStatusSubtitle: TextView
    private lateinit var tvDestination: TextView
    private lateinit var ivStatusIllustration: ImageView
    private lateinit var ivCenterMarker: ImageView
    private lateinit var btnAction: MaterialButton
    private lateinit var cardStatus: MaterialCardView
    private lateinit var etSearchLocation: EditText
    private lateinit var searchContainer: CardView
    private lateinit var mapView: MapView

    private var currentState = 0
    private var lastCenterLat = 0.0
    private var lastCenterLon = 0.0

    private val geocodeHandler = Handler(Looper.getMainLooper())
    private val geocodeRunnable = Runnable { updateAddressFromCenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this))
        setContentView(R.layout.activity_order_map)

        mapView = findViewById(R.id.mapView)
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setMultiTouchControls(true)
        val startPoint = GeoPoint(-7.6298, 111.5239)
        mapView.controller.setZoom(18.0)
        mapView.controller.setCenter(startPoint)
        mapView.addMapListener(this)

        tvStatusTitle = findViewById(R.id.tvStatusTitle)
        tvStatusSubtitle = findViewById(R.id.tvStatusSubtitle)
        tvDestination = findViewById(R.id.tvDestination)
        ivStatusIllustration = findViewById(R.id.ivStatusIllustration)
        ivCenterMarker = findViewById(R.id.ivCenterMarker)
        btnAction = findViewById(R.id.btnAction)
        cardStatus = findViewById(R.id.cardDriverStatus)
        etSearchLocation = findViewById(R.id.etSearchLocation)
        searchContainer = findViewById(R.id.searchContainer)

        findViewById<View>(R.id.toolbarOrderMap).setOnClickListener { finish() }

        etSearchLocation.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = v.text.toString()
                if (query.isNotEmpty()) searchLocation(query)
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
                true
            } else false
        }

        btnAction.setOnClickListener {
            when (currentState) {
                0 -> startBooking()
                1 -> cancelOrder()
                2 -> finishTrip()
            }
        }
    }

    private fun searchLocation(locationName: String) {
        Thread {
            try {
                val geocoder = Geocoder(this, Locale.getDefault())
                val list = geocoder.getFromLocationName(locationName, 1)
                runOnUiThread {
                    if (!list.isNullOrEmpty()) {
                        val address = list[0]
                        val point = GeoPoint(address.latitude, address.longitude)
                        mapView.controller.animateTo(point)
                        mapView.controller.setZoom(18.0)
                        tvDestination.text = locationName
                        etSearchLocation.setText(locationName)
                    } else Toast.makeText(this, "Lokasi tidak ditemukan", Toast.LENGTH_SHORT).show()
                }
            } catch (e: IOException) {}
        }.start()
    }

    override fun onScroll(event: ScrollEvent?): Boolean {
        if (currentState == 0) {
            cardStatus.visibility = View.GONE
        }

        geocodeHandler.removeCallbacks(geocodeRunnable)
        geocodeHandler.postDelayed(geocodeRunnable, 1000)
        return true
    }

    override fun onZoom(event: ZoomEvent?): Boolean = false

    private fun updateAddressFromCenter() {
        if (currentState != 0) return

        cardStatus.visibility = View.VISIBLE

        val center = mapView.mapCenter as GeoPoint
        if (lastCenterLat == center.latitude && lastCenterLon == center.longitude) return
        lastCenterLat = center.latitude
        lastCenterLon = center.longitude
        etSearchLocation.hint = "Memuat..."

        Thread {
            try {
                val geocoder = Geocoder(this, Locale.getDefault())
                val addresses = geocoder.getFromLocation(center.latitude, center.longitude, 1)
                runOnUiThread {
                    if (!addresses.isNullOrEmpty()) {
                        val addressText = addresses[0].getAddressLine(0) ?: "Lokasi"
                        etSearchLocation.setText(addressText)
                        tvDestination.text = addressText
                    }
                }
            } catch (e: Exception) {}
        }.start()
    }
    private fun startBooking() {
        currentState = 1

        ivCenterMarker.visibility = View.GONE
        searchContainer.visibility = View.GONE
        cardStatus.visibility = View.VISIBLE

        tvStatusTitle.text = "Mencari Driver..."
        tvStatusSubtitle.text = "Mohon tunggu sebentar..."
        btnAction.text = "Batalkan"
        btnAction.setBackgroundColor(Color.LTGRAY)
        btnAction.setTextColor(Color.WHITE)

        Handler(Looper.getMainLooper()).postDelayed({ driverFound() }, 3000)
    }

    private fun driverFound() {
        currentState = 2
        tvStatusTitle.text = "Driver Menjemput!"
        tvStatusSubtitle.text = "Budi (AE 1234 XX) • Honda Beat"

        ivStatusIllustration.setImageResource(R.drawable.icon_motor_baru)
        ivStatusIllustration.setColorFilter(getColor(R.color.kuy_yellow))

        btnAction.text = "Sampai Tujuan"
        btnAction.setBackgroundColor(Color.parseColor("#00C853"))
        btnAction.setTextColor(Color.WHITE)

        Toast.makeText(this, "Driver Ditemukan!", Toast.LENGTH_SHORT).show()
    }

    private fun cancelOrder() {
        Toast.makeText(this, "Pesanan Dibatalkan", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun finishTrip() {
        showRatingDialog()
    }

    private fun showRatingDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_rating, null)
        val btnSubmit = dialogView.findViewById<Button>(R.id.btnSubmitRating)
        val ivDialogLogo = dialogView.findViewById<ImageView>(R.id.ivDialogLogo)
        ivDialogLogo.setColorFilter(getColor(R.color.kuy_yellow))

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        btnSubmit.setOnClickListener {
            dialog.dismiss()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        dialog.show()
    }

    override fun onResume() { super.onResume(); mapView.onResume() }
    override fun onPause() { super.onPause(); mapView.onPause() }
}