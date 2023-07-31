package com.example.dietproapp.ui.editProfil

import android.app.Activity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.dietproapp.core.data.source.remote.network.State
import com.example.dietproapp.core.data.source.remote.request.UpdateRequest
import com.example.dietproapp.databinding.ActivityEditProfilBinding
import com.example.dietproapp.ui.base.MyActivity
import com.example.dietproapp.ui.login.AuthViewModel
import com.example.dietproapp.util.Constants
import com.example.dietproapp.util.SPrefs
import com.github.drjacky.imagepicker.ImagePicker
import com.inyongtisto.myhelper.extension.*
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class EditProfilActivity : MyActivity() {
    private val viewModel: AuthViewModel by viewModel()

    private var _binding: ActivityEditProfilBinding ?  =   null
    private val binding get()   =   _binding!!
    private var fileImage: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityEditProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setToolbar(binding.imgBack, "Edit Personal Info")

        mainButton()
        setData()

    }

    private fun setData()   {
        val user = SPrefs.getUser()
        if (user != null) {
            binding.apply {
                edtName.setText(user.nama)
                edtEmail.setText(user.email)
                edtUname.setText(user.username)
                edtWeight.setText(user.berat_badan)
                edtHeight.setText(user.tinggi_badan)
                tvInisialProfil.text    =   user.nama.getInitial()

                Picasso.get().load(Constants.USER_URL + user.foto_profil).into(binding.imgProfil)
            }
        }
    }

    private fun mainButton() {

        binding.btnSave.setOnClickListener {
            if (fileImage != null) {
                upload()
            } else {
                update()
            }
        }
        binding.btnEditpp.setOnClickListener {
            picImage()
        }

    }

    private fun picImage() {
        ImagePicker.with(this)
            .crop()
            .maxResultSize(1080, 1080, true)
            .createIntentFromDialog { launcher.launch(it) }
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val uri = it.data?.data!!

            //use the url to load the image
            fileImage   =   File(uri.path!!)
            Picasso.get().load(fileImage!!).into(binding.imgProfil)
        }
    }

    private fun update() {

        if (binding.edtName.isEmpty()) return
        if (binding.edtEmail.isEmpty()) return
        if (binding.edtUname.isEmpty()) return
        if (binding.edtWeight.isEmpty()) return
        if (binding.edtHeight.isEmpty()) return

        val idUser = SPrefs.getUser()?.id
        val body = UpdateRequest(
            idUser.int(), // jika tidak menggunakna library idUser ?: 0,
            binding.edtName.text.toString(),
            binding.edtEmail.text.toString(),
            binding.edtUname.text.toString(),
            binding.edtWeight.text.toString(),
            binding.edtHeight.text.toString()
        )

        viewModel.updateUser(body).observe(this) {

            when (it.state) {
                State.SUCCESS -> {
                    progress.dismiss()
                    showToast("Update Profil Sukses " + it.data?.nama)
                    onBackPressed()
                }
                State.ERROR -> {
                    progress.dismiss()
                    toastError(it.message ?: "Error")
                }
                State.LOADING -> {
                    progress.show()
                }
            }

//            showToast("Selamat datang  " + it?.data?.nama)
        }
    }

    private fun upload() {
        val idUser = SPrefs.getUser()?.id
        val file = fileImage.toMultipartBody("foto_profil") //ditambahkan foto_profil karena agar sesuai kolom dtbasenya

        viewModel.uploadUser(idUser, file).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    update()
                }
                State.ERROR -> {
                    toastError(it.message ?: "Error")
                }
                State.LOADING -> {
                    progress.show()
                }
            }

//            showToast("Selamat datang  " + it?.data?.nama)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}