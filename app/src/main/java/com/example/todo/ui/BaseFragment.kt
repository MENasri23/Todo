package com.example.todo.ui

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.todo.di.Injectable
import javax.inject.Inject

abstract class BaseFragment(
    @LayoutRes layoutId: Int = 0
) : Fragment(layoutId), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    inline fun <reified VM : ViewModel> viewModels(
        noinline ownerProducer: () -> ViewModelStoreOwner = { this }
    ) = viewModels<VM>(ownerProducer) { viewModelFactory }


}