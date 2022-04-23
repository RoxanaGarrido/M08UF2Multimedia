package cat.copernic.rgarrido.m08uf2multimedia

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import cat.copernic.rgarrido.m08uf2multimedia.databinding.FragmentEx3Binding

class Ex3Fragment : Fragment(), SensorEventListener {

    private lateinit var binding: FragmentEx3Binding
    lateinit var sensorManager: SensorManager
    private val sharedViewModel: SensorsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEx3Binding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sensorManager = sharedViewModel.sensorManager
        SensorSoportado(Sensor.TYPE_PROXIMITY, binding.tvProximity)
        SensorSoportado(Sensor.TYPE_ORIENTATION, binding.tvOrientation)
        SensorSoportado(Sensor.TYPE_GAME_ROTATION_VECTOR, binding.tvRotation)
        SensorSoportado(Sensor.TYPE_LIGHT, binding.tvLight)
        SensorSoportado(Sensor.TYPE_AMBIENT_TEMPERATURE, binding.tvATemp)
        SensorSoportado(Sensor.TYPE_TEMPERATURE, binding.tvDTempe)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun SensorSoportado(type: Int, text:TextView){
        var sensor = sensorManager.getDefaultSensor(type)
        if(!sharedViewModel.compruebaSensors(type)){
            text.setTextColor(Color.parseColor(sharedViewModel.textColorError))
        }else{
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(event != null){
            when(event.sensor.type){
                Sensor.TYPE_PROXIMITY -> {
                    binding.tvProximityValue.text = String.format("%.3f", event.values[0])
                }
                Sensor.TYPE_ORIENTATION -> {
                    binding.tvOrientationX.text = String.format("%.3f", event.values[0])
                    binding.tvOrientationY.text = String.format("%.3f", event.values[1])
                    binding.tvOrientationZ.text = String.format("%.3f", event.values[2])
                }
                Sensor.TYPE_GAME_ROTATION_VECTOR -> {
                    binding.tvRotationX.text = String.format("%.3f", event.values[0])
                    binding.tvRotationY.text = String.format("%.3f", event.values[1])
                    binding.tvRotationZ.text = String.format("%.3f", event.values[2])
                }
                Sensor.TYPE_LIGHT -> {
                    binding.tvLightValue.text = String.format("%.3f", event.values[0])
                }
                Sensor.TYPE_AMBIENT_TEMPERATURE -> {
                    binding.tvATValue.text = String.format("%.3f", event.values[0])
                }
                Sensor.TYPE_TEMPERATURE -> {
                    binding.tvTemValue.text = String.format("%.3f", event.values[0])
                }
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        return
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}

