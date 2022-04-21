package cat.copernic.rgarrido.m08uf2multimedia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import cat.copernic.rgarrido.m08uf2multimedia.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    private lateinit var binding:FragmentMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btAct1.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_ex1Fragment)
        }

        binding.btAct2.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_ex2Fragment)
        }
    }

}