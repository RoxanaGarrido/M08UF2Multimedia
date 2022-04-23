package cat.copernic.rgarrido.m08uf2multimedia

import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import cat.copernic.rgarrido.m08uf2multimedia.databinding.FragmentEx1Binding


class Ex1Fragment : Fragment() {

    private lateinit var binding: FragmentEx1Binding
    private val sharedViewModel: SensorsViewModel by activityViewModels()
    lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Obtener el SensorManager de la activity
        sensorManager = sharedViewModel.sensorManager
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate
        binding = FragmentEx1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener lista de sensores de todo tipo
        val sensorList = sharedViewModel.listSensors

        // Iterar list de sensors para obtener name
        val sensorText = StringBuilder()

        var count = 1
        for (currentSensor in sensorList) {
            //Control de versiones de android
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                sensorText.append("${count} ${currentSensor.name} - ${currentSensor.stringType}\n")
                    .append(System.getProperty("line.separator")
                )
            }
            count++
        }
        // Update the sensor list text view to display the list of sensors.
        binding.sensorList.text = sensorText
    }

}