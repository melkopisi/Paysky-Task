package me.melkopisi.payskytask.art_core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import me.melkopisi.payskytask.core.Action

abstract class BaseFragment<BINDING : ViewBinding>(private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> BINDING) : Fragment() {

    private var _binding: BINDING? = null
    protected val binding: BINDING get() = _binding!!

    abstract fun setupViews()
    open fun onDestroyViewInvoked(): Action = {}
    open fun onBackPressInvoked(): Action = { findNavController().popBackStack() }


    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // Handle back press
            onBackPressInvoked().invoke()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        onDestroyViewInvoked().invoke()
    }
}