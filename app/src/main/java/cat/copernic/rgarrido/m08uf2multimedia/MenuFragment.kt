package cat.copernic.rgarrido.m08uf2multimedia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        binding.btAct3.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_ex3Fragment)
        }

        binding.btAct4.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_ex4Fragment2)
        }

        binding.btAct5.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_ex5Fragment)
        }

        binding.btAct6.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_ex6Fragment)
        }

        binding.btAct7.setOnClickListener{
          Toast.makeText(context, "Está en otra app!", Toast.LENGTH_SHORT).show()
        }

        binding.btAct8.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_ex8Fragment)
        }

        binding.btAct9.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_ex9Fragment)
        }
    }

}