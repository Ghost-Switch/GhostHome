package com.example.ghosthome.addroom

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.widget.Toast

class OrientationEventListener(private val context: Context) : SensorEventListener {

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Ignoring accuracy changes
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            if (it.sensor.type == Sensor.TYPE_ACCELEROMETER) {
                val gX = -it.values[0]
                val gY = it.values[1]
                val gZ = it.values[2]

                val inclination = Math.atan2(Math.sqrt((gX * gX + gY * gY).toDouble()).toDouble(), gZ.toDouble()) * (180 / Math.PI)
                if (inclination < 25 || inclination > 155) {
                    showToast("Portrait mode")
                } else {
                    showToast("Landscape mode")
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}