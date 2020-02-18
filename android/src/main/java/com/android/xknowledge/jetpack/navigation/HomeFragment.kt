package com.android.xknowledge.jetpack.navigation

import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.android.xknowledge.R

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val options = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }

        view.findViewById<Button>(R.id.navigation_destination_button)?.setOnClickListener {
            findNavController().navigate(R.id.flow_step_one_dest, null, options)
        }

        view.findViewById<Button>(R.id.navigation_action_button)?.setOnClickListener {
            val flowStepNumberArg = 1
            val action = HomeFragmentDirections.flowStepOneDest(flowStepNumberArg)
            findNavController().navigate(action)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.navigation_main_menu, menu)
    }
}
