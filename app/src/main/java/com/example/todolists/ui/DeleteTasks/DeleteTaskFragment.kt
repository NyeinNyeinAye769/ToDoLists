package com.example.todolists.ui.DeleteTasks

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolists.R
import com.example.todolists.data.model.Task
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeleteTaskFragment : Fragment() {

    private val viewModelTask: DeleteTaskFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_add_tasks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = view.findViewById<EditText>(R.id.title)
        val tasktodo = view.findViewById<EditText>(R.id.task)

        view.findViewById<FloatingActionButton>(R.id.floatingSaveButton)?.setOnClickListener{

            var hasError = false

            if (TextUtils.isEmpty(title.text)){
                title.error = "Title is required"
                hasError = true
            }
            if (TextUtils.isEmpty(tasktodo.text)){
                tasktodo.error = "Task is required"
                hasError = true
            }
            if (hasError != true){
                val task = Task(
                    id = null,
                    title = title.text.toString(),
                    tasks = tasktodo.text.toString())

                viewModelTask.addTask(task)

                title.setText("")
                tasktodo.setText("")

                findNavController().navigate(R.id.home_fragment,null,null)
            }
        }
    }
}