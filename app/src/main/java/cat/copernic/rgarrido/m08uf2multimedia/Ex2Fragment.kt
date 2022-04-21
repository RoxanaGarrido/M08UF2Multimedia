package cat.copernic.rgarrido.m08uf2multimedia

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import cat.copernic.rgarrido.m08uf2multimedia.databinding.FragmentEx2Binding

class Ex2Fragment : Fragment(), SensorEventListener {

    private lateinit var binding:FragmentEx2Binding
    private val sharedViewModel: SensorsViewModel by activityViewModels()
    lateinit var sensorManager: SensorManager
    lateinit var sensor: Sensor
    public var switchOn:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sensorManager = sharedViewModel.sensorManager
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEx2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.switch1.setOnCheckedChangeListener{ buttonView, isChecked ->
            if(isChecked){
                sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
                binding.switch1.text = "Encendido"
                switchOn = true
            }else{
                sensorManager.unregisterListener(this)
                binding.switch1.text = "Apagado"
                switchOn = false
            }
        }

    }
/*
     fun onRadioButtonClick(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                binding.rbAccel.id ->
                    if (checked) {
                        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
                        mostrarSensor(sensor, switchOn)
                    }
            }
        }
    }
    
    private fun seleccionarSensor() {
        if(binding.rbAccel.isChecked){
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            mostrarSensor(sensor)
        }
        if(binding.rbGrav.isChecked){
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)
            mostrarSensor(sensor)
        }
        if(binding.rbGy.isChecked){
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
            mostrarSensor(sensor)
        }
        if(binding.rbLinearAcc.isChecked){
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
            mostrarSensor(sensor)
        }
        if(binding.rbMag.isChecked){
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
            mostrarSensor(sensor)
        }
        if(binding.rbMotion.isChecked){
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MOTION_DETECT)
            mostrarSensor(sensor)
        }
        if(binding.rbRota.isChecked){
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
            mostrarSensor(sensor)
        }
        if(binding.rbStepC.isChecked){
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
            mostrarSensor(sensor)
        }
        if(binding.rbStepD.isChecked){
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
            mostrarSensor(sensor)
        }
    }
    */


    private fun mostrarSensor(sensor: Sensor, switch: Boolean){
        binding.tvSensorName.text = sensor.name
        //values
        if(switch){
            Toast.makeText(sharedViewModel.activityContext, "Aceleracion", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        TODO("Not yet implemented")
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        TODO("Not yet implemented")
    }

}