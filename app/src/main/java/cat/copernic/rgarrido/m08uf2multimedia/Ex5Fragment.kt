package cat.copernic.rgarrido.m08uf2multimedia

import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cat.copernic.rgarrido.m08uf2multimedia.databinding.FragmentEx5Binding
import java.util.*

class Ex5Fragment : Fragment(), TextToSpeech.OnInitListener {

    private lateinit var binding:FragmentEx5Binding
    lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentEx5Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txTextoAVoz.setText("tanto monta, monta tanto")
        tts = TextToSpeech(activity, this)

        binding.bt1.setOnClickListener {
            enableDisableButtons(false)
            val mp = MediaPlayer.create(activity, R.raw.guitarra)

            mp.setOnCompletionListener {
               enableDisableButtons(true)
            }

            mp.start()

            var duracion: Long = mp.duration.toLong()

            object : CountDownTimer( duracion, 200) {
                override fun onTick(millisUntilFinished: Long) {
                    binding.tvMensajes.setText("seconds remaining: " + millisUntilFinished / 1000)
                }

                override fun onFinish() {
                    binding.tvMensajes.setText("done!")
                }
            }.start()
        }

        binding.bt2.setOnClickListener {
            enableDisableButtons(false)
            val mp = MediaPlayer.create(activity, R.raw.synth)

            mp.setOnCompletionListener {
                enableDisableButtons(true)
            }

            mp.start()

            var duracion: Long = mp.duration.toLong()

            object : CountDownTimer( duracion, 200) {
                override fun onTick(millisUntilFinished: Long) {
                    binding.tvMensajes.setText("seconds remaining: " + millisUntilFinished / 1000)
                }

                override fun onFinish() {
                    binding.tvMensajes.setText("done!")
                }
            }.start()
        }

        binding.bt3.setOnClickListener {
            enableDisableButtons(false)
            val mp = MediaPlayer.create(activity, R.raw.synth2)

            mp.setOnCompletionListener {
                enableDisableButtons(true)
            }

            mp.start()

            var duracion: Long = mp.duration.toLong()

            object : CountDownTimer( duracion, 200) {
                override fun onTick(millisUntilFinished: Long) {
                    binding.tvMensajes.setText("seconds remaining: " + millisUntilFinished / 1000)
                }

                override fun onFinish() {
                    binding.tvMensajes.setText("done!")
                }
            }.start()
        }
        binding.bt4.setOnClickListener {
            enableDisableButtons(false)
            val mp = MediaPlayer.create(activity, R.raw.synth3)

            mp.setOnCompletionListener {
                enableDisableButtons(true)
            }

            mp.start()

            var duracion: Long = mp.duration.toLong()

            object : CountDownTimer( duracion, 200) {
                override fun onTick(millisUntilFinished: Long) {
                    binding.tvMensajes.setText("seconds remaining: " + millisUntilFinished / 1000)
                }

                override fun onFinish() {
                    binding.tvMensajes.setText("done!")
                }
            }.start()
        }


        val speechListener = object : UtteranceProgressListener() {
            override fun onStart(p: String?) {

                binding.btParla.post {
                    if( p != null ) binding.tvMissatges.text = "Iniciat: $p"
                    else binding.tvMissatges.text = "Iniciat"
                    binding.btParla.isEnabled = false //no caldria, s'ha fet abans
                }
            }

            override fun onDone(p: String?) {
                binding.btParla.post {
                    if( p != null ) binding.tvMissatges.text = "Fet: $p"
                    else binding.tvMissatges.text = "Fet"
                    binding.btParla.isEnabled = true
                }
            }

            override fun onError(p: String?) {
                binding.btParla.post {
                    if (p != null) binding.tvMissatges.text = "Error: $p"
                    else binding.tvMissatges.text = "Error"
                    binding.btParla.isEnabled = true
                }
            }
        }

        tts.setOnUtteranceProgressListener(speechListener)

        binding.btParla.setOnClickListener {
            binding.btParla.isEnabled = false
            parlar()
        }
    }

    private fun parlar() {
        val text = binding.txTextoAVoz.text
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    public override fun onDestroy() {
        // Shutdown TTS
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

    fun enableDisableButtons(bool:Boolean){
        binding.bt1.isEnabled = bool
        binding.bt2.isEnabled = bool
        binding.bt3.isEnabled = bool
        binding.bt4.isEnabled = bool
    }
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.getDefault())

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS","The Language specified is not supported!")

            } else {
                binding.btParla.isEnabled = true
            }

        } else {
            Log.e("TTS", "Initilization Failed!")
        }
    }


}