package com.example.todo.ui.tasks

import android.os.Bundle
import android.view.View
import com.example.todo.R
import com.example.todo.databinding.FragmentTasksBinding
import com.example.todo.ui.BaseFragment
import com.example.todo.ui.util.launchAndRepeatWithViewLifecycle
import com.example.todo.util.viewBinding
import timber.log.Timber

class TasksFragment : BaseFragment(R.layout.fragment_tasks) {

    private val viewModel by viewModels<TasksViewModel>()
    private val binding by viewBinding(FragmentTasksBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launchAndRepeatWithViewLifecycle {
            viewModel.taskDetails.collect {
                Timber.d("\n\n\n${it.data?.get(0)?.category}\n\n\n")
                Timber.d("\n\n\n${it}\n\n\n")
            }
        }
    }

}

