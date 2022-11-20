package com.example.kotlintest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.kotlintest.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private var _binding:FragmentGameBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: GameFragmentModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentGameBinding.inflate(inflater,container,false)

       viewModel = ViewModelProvider(this).get(GameFragmentModel::class.java)

        binding.viewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner
//       viewModel.secrotWordDisplay.observe(viewLifecycleOwner, Observer { newValue ->
//           binding.word.text = newValue
//       })
//
//        viewModel.incorrectLetters.observe(viewLifecycleOwner, Observer {
//            binding.incorrectTextView.text = it
//        })
//
//        viewModel.life.observe(viewLifecycleOwner, Observer {
//            binding.lifeLeftTextView.text = it.toString()
//        })
//
        viewModel.message.observe(viewLifecycleOwner, Observer {
            if(it.isNotEmpty()){
                var navDirection = GameFragmentDirections.actionGameFragmentToResultFragment(it)
                binding.root.findNavController().navigate(navDirection)
            }
        })
//        binding.guessBtn.setOnClickListener {
//            viewModel.guessLetter(binding.guessEditText.text.toString().uppercase())
//            binding.guessEditText.text = null
//        }

        return  binding.root
    }

//    fun updateViews(){
//        binding.word.text = viewModel.secrotWordDisplay
//        binding.incorrectTextView.text = viewModel.incorrectLetters
//        binding.lifeLeftTextView.text = "${viewModel.life}"
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}