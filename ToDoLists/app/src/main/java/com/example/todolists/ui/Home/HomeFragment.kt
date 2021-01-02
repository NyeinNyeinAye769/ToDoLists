package com.example.todolists.ui.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolists.R
import com.example.todolists.data.model.Task
import com.example.todolists.databinding.ActivityHomeBinding
import com.example.todolists.databinding.FragmentHomeTaskBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(), TaskRecyclerViewAdapter.TaskItemClickListener {

    private var _binding: FragmentHomeTaskBinding? = null
    private val binding get() = _binding!!

    private var _binding1: ActivityHomeBinding? = null
    private val binding1 get() = _binding1!!

    private val viewModel: HomeFragmentViewModel by viewModels()

    lateinit var adapter: TaskRecyclerViewAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeTaskBinding.inflate(inflater, container, false)

        setUpRecyclerView()
        subscribeUI()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val t = findViewById(R.id.startofweek) as TextView
//        var name1 = view.findViewById(R.id.user_name) as? TextView
//        val name_temp = arguments?.getString("name")
//        if (name_temp != null) {
////            name1?.setText(name_temp)
//            name1?.text = name_temp
//        }
//        var name = view.findViewById<TextView>(R.id.user_name)
//        name.setText(arguments?.getString("name"))
//        val email = view.findViewById<TextView>(R.id.user_email)
//        email.text = arguments?.getString("email").toString()
//        email.setTextKeepState(arguments?.getCharSequence("email"))

//        view.findViewById<ImageButton>(R.id.menu_drawer)?.setOnClickListener {
//            val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
//            val drawer = view.findViewById<DrawerLayout>(R.id.drawer_layout)
//            val toggle = ActionBarDrawerToggle(Activity(),drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
//
//            toggle.isDrawerIndicatorEnabled = false
//
//            toggle.setToolbarNavigationClickListener {
//                if (drawer .isDrawerVisible(GravityCompat.START)) {
//                    drawer .closeDrawer(GravityCompat.START)
//                } else {
//                    drawer .openDrawer(GravityCompat.START)
//                }
//            }
//            drawer .addDrawerListener(toggle)
//            toggle.syncState()
//        }

        view.findViewById<ImageButton>(R.id.edit_task)?.setOnClickListener {
            findNavController().navigate(R.id.edit_task_fragment,null,null)
        }

        view.findViewById<FloatingActionButton>(R.id.floating_add)?.setOnClickListener {
            findNavController().navigate(R.id.add_tasks_fragment,null,null)
        }
    }

    private fun setUpRecyclerView() {
        // Set the adapter
        adapter = TaskRecyclerViewAdapter(this)
        binding.recyclerTaskList.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerTaskList.adapter = adapter
    }

    private fun subscribeUI() {
        viewModel.taskList.observe(viewLifecycleOwner, Observer<List<Task>> {
            adapter.setItems(it as ArrayList<Task>)
        })
    }

    override fun onClickedTask(task: Task) {

        val direction = HomeFragmentDirections.actionHomeFragmentToEditTaskFragment(task.id!!, task)
        findNavController().navigate(direction)

    }
}