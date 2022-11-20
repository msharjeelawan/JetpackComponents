package com.example.kotlintest.room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintest.R
import com.example.kotlintest.databinding.RecyclerItemBinding
import com.example.kotlintest.generated.callback.OnClickListener

class TaskRecyclerAdapter(var clickListener: (position:Long) -> Unit ):ListAdapter<Task,TaskRecyclerAdapter.TaskViewHolder>(TaskDiffItemCallback()) {

//    var data = listOf<Task>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TaskViewHolder.initViewHolder(parent)


    override fun onBindViewHolder(holder: TaskViewHolder, position: Int){
//        holder.bind(data[position])
        holder.bind(getItem(position),clickListener)
    }

//    override fun getItemCount() = get.size


    class TaskViewHolder(val binding:RecyclerItemBinding):RecyclerView.ViewHolder(binding.root){


        fun bind(task:Task,clickListener: (position: Long) -> Unit){
//            view.findViewById<TextView>(R.id.taskName).text = task.name
//            view.findViewById<CheckBox>(R.id.checkbox).isChecked = task.isCompleted
            binding.task = task
            binding.root.setOnClickListener(View.OnClickListener {
                clickListener(task.id)
               // Toast.makeText(binding.root.context,"${task.id}",Toast.LENGTH_SHORT).show()
            })
        }


        companion object {
            fun initViewHolder(parent:ViewGroup):TaskViewHolder{
                val inflator = LayoutInflater.from(parent.context)
//                val view = inflator.inflate(R.layout.recycler_item,parent,false) as CardView
                val binding  = RecyclerItemBinding.inflate(inflator,parent,false)
                return  TaskViewHolder(binding)
            }
        }
    }
}