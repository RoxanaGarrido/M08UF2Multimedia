package cat.copernic.rgarrido.m08uf2multimedia

import android.app.Application
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class SensorsViewModel(application: Application): AndroidViewModel(application) {

    var sensorManager: SensorManager = application.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    set(value) {
        field = value
    }

    var listSensors:List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)

    var SensorName = MutableLiveData<String>("ACCELEROMETER")

    var textColorError = "#B31F1F"

    @RequiresApi(Build.VERSION_CODES.N)
    fun compruebaSensors(id:Int):Boolean{
        return listSensors.any{ it.type == id}
    }


}