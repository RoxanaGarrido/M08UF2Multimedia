package cat.copernic.rgarrido.m08uf2multimedia

import android.content.Context
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {

    private val sensorsViewModel: SensorsViewModel by viewModels()

    lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Obtener Sensor manager de la activity
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensorsViewModel.sensorManager = sensorManager
    }
}