package cat.copernic.rgarrido.m08uf2multimedia

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
import android.widget.RadioGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import cat.copernic.rgarrido.m08uf2multimedia.databinding.FragmentEx2Binding

class Ex2Fragment : Fragment(), SensorEventListener {

    private lateinit var binding:FragmentEx2Binding
    private val sharedViewModel: SensorsViewModel by activityViewModels()
    lateinit var sensorManager: SensorManager
    lateinit var sensor: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEx2Binding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sensorManager = sharedViewModel.sensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.myViewModel = sharedViewModel

        binding.switch1.setOnCheckedChangeListener{ buttonView, isChecked ->
            if(isChecked){
                sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
                binding.switch1.text = "Encendido"
                binding.rgGroup.isEnabled = false
            }else{
                sensorManager.unregisterListener(this)
                binding.switch1.text = "Apagado"
                binding.rgGroup.isEnabled = true
            }
        }

        binding.rgGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                if(checkedId == binding.rbAccel.id){
                 sensorSoportado(Sensor.TYPE_ACCELEROMETER)
                }
                if(checkedId == binding.rbGrav.id){
                    sensorSoportado(Sensor.TYPE_GRAVITY)
                }
                if(checkedId == binding.rbGy.id){
                   sensorSoportado(Sensor.TYPE_GYROSCOPE)
                }
                if(checkedId == binding.rbLinearAcc.id){
                   sensorSoportado(Sensor.TYPE_LINEAR_ACCELERATION)
                }
                if(checkedId == binding.rbRota.id){
                    sensorSoportado(Sensor.TYPE_ROTATION_VECTOR)
                }
                if(checkedId == binding.rbMotion.id){
                    sensorSoportado(Sensor.TYPE_SIGNIFICANT_MOTION)
                }
                if(checkedId == binding.rbStepC.id){
                    sensorSoportado(Sensor.TYPE_STEP_COUNTER)
                }
                if(checkedId == binding.rbStepD.id){
                    sensorSoportado(Sensor.TYPE_STEP_DETECTOR)
                }
                if(checkedId == binding.rbMag.id){
                    sensorSoportado(Sensor.TYPE_MAGNETIC_FIELD)
                }
            })
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun sensorSoportado(id: Int){
        if( sharedViewModel.compruebaSensors(id) ) {
            sensor = sensorManager.getDefaultSensor(id)
            sharedViewModel.SensorName.value = sensor.name
            Toast.makeText(
                activity, "${sensor.stringType}",
                Toast.LENGTH_SHORT
            ).show()

        }else {
            Toast.makeText(
                activity, "Sensor no soportado!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
    if(event != null){
        binding.tvXValue.text = String.format("%.3f", event.values[0])
        binding.tvYValue.text = String.format("%.3f", event.values[1])
        binding.tvZValue.text = String.format("%.3f", event.values[2])
    }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
       return
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
        binding.switch1.isChecked = false
    }

}