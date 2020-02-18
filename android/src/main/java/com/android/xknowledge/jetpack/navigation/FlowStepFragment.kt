package com.android.xknowledge.jetpack.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs

import com.android.xknowledge.R

class FlowStepFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val safeVarargs: FlowStepFragmentArgs by navArgs()
        val flowStepNumber = safeVarargs.flowStepNumber
        return when (flowStepNumber) {
            2 -> inflater.inflate(R.layout.fragment_steptwo, container, false)
            else -> inflater.inflate(R.layout.fragment_stepone, container, false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.navigation_next_button)
            .setOnClickListener(Navigation.createNavigateOnClickListener(R.id.next_action))
    }
}
