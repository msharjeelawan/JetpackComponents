package com.example.kotlintest.room

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kotlintest.R
import com.example.kotlintest.databinding.FragmentEditTaskBinding

class EditTaskFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEditTaskBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = viewLifecycleOwner
        val id = EditTaskFragmentArgs.fromBundle(requireArguments()).id

        val application = requireNotNull(this.activity).application

        val dao = TaskDatabase.getInstance(application).taskDao

        val factory = EditTaskModelFactory(id,dao)
        val viewModel = ViewModelProvider(this,factory).get(EditTaskViewModel::class.java)

        binding.viewModel=viewModel

        viewModel.navigate.observe(viewLifecycleOwner, Observer {
            if(it){
                findNavController().navigate(R.id.action_editTaskFragment_to_taskFragment)
                viewModel.navigationDone()
            }

        })

        return binding.root
    }

}