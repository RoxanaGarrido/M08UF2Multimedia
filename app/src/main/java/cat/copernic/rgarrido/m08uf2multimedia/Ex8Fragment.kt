package cat.copernic.rgarrido.m08uf2multimedia

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import cat.copernic.rgarrido.m08uf2multimedia.databinding.FragmentEx8Binding

class Ex8Fragment : Fragment() {

    private lateinit var binding: FragmentEx8Binding
    lateinit var email: String
    lateinit var subject: String
    lateinit var message: String
    lateinit var uri: Uri
    private val pickFromGallery:Int = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentEx8Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btSend.setOnClickListener {
            sendEmail()
            /*
            getData()
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_EMAIL, email)
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            intent.putExtra(Intent.EXTRA_TEXT, message)
            intent.type = "message/rfc822"
            startActivity(Intent.createChooser(intent, "Select email"))
            */

        }
        binding.btAttachment.setOnClickListener {
            openFolder()
        }
    }

    private fun openFolder() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        intent.putExtra("return-data", true)
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), pickFromGallery)
    }

    private fun sendEmail() {
        try {
            getData()
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.type = "plain/text"
            emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
            emailIntent.putExtra(Intent.EXTRA_STREAM, uri)
            emailIntent.putExtra(Intent.EXTRA_TEXT, message)
            this.startActivity(Intent.createChooser(emailIntent, "Sending email..."))
        }
        catch (t: Throwable) {
            Toast.makeText(context, "Request failed try again: $t", Toast.LENGTH_LONG).show()
        }
    }

    private fun getData() {
        email = binding.editTextMail.text.toString()
        subject = binding.editTextSubject.text.toString()
        message = binding.editTextMessage.text.toString()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == pickFromGallery && resultCode == RESULT_OK) {
            if (data != null) {
                uri = data.data!!
            }
            binding.tvAttachment.text = uri.lastPathSegment
            binding.tvAttachment.visibility = View.VISIBLE
        }
    }
}
