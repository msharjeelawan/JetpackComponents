package com.example.kotlintest.room

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.example.kotlintest.R
import com.example.kotlintest.databinding.FragmentTaskBinding
import java.lang.reflect.Array.get

class TaskFragment : Fragment() {
    private var _binding:FragmentTaskBinding?=null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         _binding = FragmentTaskBinding.inflate(inflater,container,false)

        //initialize database
        var context= requireNotNull(this.activity).application
        val dao = TaskDatabase.getInstance(context).taskDao
        //initialize viewModel
        val viewModelFactory = TaskViewModelFactory(dao)
        var viewModel = ViewModelProvider(this,viewModelFactory).get(TaskViewModel::class.java)

        var navigate = { position:Long ->
            val direction = TaskFragmentDirections.actionTaskFragmentToEditTaskFragment(position)
            findNavController().navigate(direction)
        }

        val adapter = TaskRecyclerAdapter(navigate)
        binding.recyclerView.adapter = adapter
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.tasks.observe(viewLifecycleOwner, Observer {
           it?.let {
               adapter.submitList(it)
           }

        })


        return  binding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}