package com.example.todolists.ui.Home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolists.data.model.Task
import com.example.todolists.databinding.ActivityHomeBinding
import com.example.todolists.databinding.ActivityListviewBinding

class TaskRecyclerViewAdapter(private val taskItemClickListener: TaskItemClickListener
) : RecyclerView.Adapter<TaskRecyclerViewAdapter.TaskListViewHolder>() {

    interface TaskItemClickListener {
        fun onClickedTask(task: Task)
    }

    private val items = ArrayList<Task>()

    fun setItems(items: ArrayList<Task>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
        return TaskListViewHolder(

            ActivityListviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ,taskItemClickListener)
    }

    override fun onBindViewHolder(holderTaskList: TaskListViewHolder, position: Int) {
        val item = items[position]
        holderTaskList.bind(item)
    }

    override fun getItemCount(): Int = items.size

    inner class TaskListViewHolder(private val binding: ActivityListviewBinding,
                                   private val itemClickListener: TaskRecyclerViewAdapter.TaskItemClickListener) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        lateinit var task: Task
        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(item: Task) {
            task = item
            binding.taskId.text = task.id.toString()
            binding.titleTask.text = task.title
        }

        override fun onClick(p0: View?) {
            itemClickListener.onClickedTask(task)
        }
    }
}