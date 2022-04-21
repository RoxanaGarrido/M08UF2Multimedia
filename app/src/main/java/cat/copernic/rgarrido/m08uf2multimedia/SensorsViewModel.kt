package cat.copernic.rgarrido.m08uf2multimedia

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SensorsViewModel: ViewModel() {

    lateinit var listSensors: List<Sensor>

    lateinit var sensorManager: SensorManager

    lateinit var activityContext: Context

    @JvmName("setSensorManager1")
    fun setSensorManager(manager: SensorManager) {
        sensorManager = manager
        listSensors = sensorManager.getSensorList(Sensor.TYPE_ALL)
    }
}