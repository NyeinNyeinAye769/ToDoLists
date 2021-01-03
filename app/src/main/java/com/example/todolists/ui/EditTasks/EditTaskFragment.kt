package com.example.todolists.ui.EditTasks

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todolists.R
import com.example.todolists.data.model.Task
import com.example.todolists.databinding.FragmentEditTaskBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditTaskFragment : Fragment() {

    private val viewModel: EditTaskFragmentViewModel by viewModels()

    val args: EditTaskFragmentArgs by navArgs()

    private var _binding: FragmentEditTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditTaskBinding.inflate(inflater, container, false)
        binding.floatingActionButton.setOnClickListener { updateTask()}
        return binding.root
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val task = args.task
        val taskid = args.taskId
        binding.title.setText(task.title)
        binding.task.setText(task.tasks)
    }

    private fun updateTask() {

        var hasError = false

        if (TextUtils.isEmpty(binding.title.text)) {
            binding.title.error = "Title is required!"
            hasError = true
        }
        if (TextUtils.isEmpty(binding.task.text)) {
            binding.task.error = "Task is required!"
            hasError = true
        }

        if (hasError != true) {
            val task = Task(id = args.taskId,
            title = binding.title.text.toString(),
            tasks = binding.task.text.toString())
            viewModel.updateTask(task)
            Toast.makeText(requireContext(), "Update is successful!", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }
    }
}