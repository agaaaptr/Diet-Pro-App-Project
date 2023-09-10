package com.example.dietproapp.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dietproapp.R
import com.example.dietproapp.databinding.FragmentChatBinding

class ChatFragment : Fragment() {

//    private lateinit var chatViewModel: ChatViewModel
    private var _binding: FragmentChatBinding? = null

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
        val rootView = inflater.inflate(R.layout.fragment_chat, container, false)

        // Set the status bar icons to black (light status bar)
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        val chatViewModel =
            ViewModelProvider(this).get(ChatViewModel::class.java)

        _binding = FragmentChatBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textChat
        chatViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

//        mainButton()
        return root
    }

//    override fun onResume() {
//        setUser()
//        super.onResume()
//    }
//
//    private fun mainButton() {
//        binding.btnKeluar.setOnClickListener {
//            SPrefs.isLogin  =   false
//            pushActivity(LoginActivity::class.java)
//        }
//
//        binding.imgEdit.setOnClickListener {
//            intentActivity(EditProfilActivity::class.java)
//        }
//
//    }
//
//    private fun setUser()   {
//        val user = SPrefs.getUser()
//        if (user != null) {
//            binding.apply {
//                tvNamaProfil.text = user.nama
//                tvTipeakun.text  = user.email
//                edtEmail.setText(user.email)
//                edtUsername.setText(user.username)
//                edtGender.setText(user.usia)
//                edtHeight.setText(user.tinggi_badan)
//                edtWeight.setText(user.berat_badan)
//                tvInisialProfil.text    =   user.nama.getInitial()
//
//                Picasso.get().load(USER_URL+user.foto_profil).into(binding.imgProfil)
//            }
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}