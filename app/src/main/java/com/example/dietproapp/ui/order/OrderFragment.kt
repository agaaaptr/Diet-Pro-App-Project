package com.example.dietproapp.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dietproapp.R
import com.example.dietproapp.databinding.FragmentOrderBinding

class OrderFragment : Fragment() {

    private lateinit var orderViewModel: OrderViewModel
    private var _binding: FragmentOrderBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val statusBarColor = ContextCompat.getColor(requireContext(), R.color.background)
        activity?.window?.statusBarColor = statusBarColor
        val rootView = inflater.inflate(R.layout.fragment_order, container, false)

        // Set the status bar icons to black (light status bar)
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        val orderViewModel =
            ViewModelProvider(this).get(OrderViewModel::class.java)

        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textOrder
        orderViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}