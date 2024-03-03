package com.appadore.ui.main.view.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.appadore.R
import com.appadore.data.api.ApiHelper
import com.appadore.data.api.RetrofitBuilder
import com.appadore.databinding.ActivityDetailBinding
import com.appadore.databinding.ActivityMainBinding
import com.appadore.ui.base.DetailViewModelFactory
import com.appadore.ui.main.viewmodel.DetailViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

class DetailActivity : BaseActivity(),View.OnClickListener {
    private var id: Int = 0
    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.hasExtra("id"))
            id=intent.getIntExtra("id",0)
        setupViewModel()
        setUpUI()
        if(id!=0)
            fetchDataObserver()
    }

    private fun fetchDataObserver() {
        viewModel.getParticularData(id)
            .observe(this, Observer {
                if(it.isNotEmpty()){
                    setData(it[0].name,it[0].bio)
                    binding.name.text=it[0].name
                    binding.role.text=it[0].userStatus
                    binding.particip.text=it[0].participantCount.toString()
                    binding.etNoteHeader.setText(it[0].bio.toString())
                    Glide.with(this)
                        .load(it[0].groupPhoto)
                        .centerCrop()
                        .transform(CircleCrop())
                        .into(binding.imProfile)
                }
            })

    }

    private fun setData(title: String?, body: String?) {
        binding.etNoteHeader.setText(body)
    }

    private fun setUpUI() {
//        binding.imgUpdate.setOnClickListener(this)
//        binding.imgDeleteData.setOnClickListener(this)

        binding.back.setOnClickListener(this)
        binding.tvEdit.setOnClickListener(this)

    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            DetailViewModelFactory(ApiHelper(RetrofitBuilder.apiService,this),this)
        )[DetailViewModel::class.java]

    }

    override fun onClick(v: View?) {
        val id =v!!.id
        when(id){
            R.id.back->{
                finish()
            }
            R.id.tv_edit->{
//todo
            }
//            R.id.img_update->{
//                viewModel.updateData(binding.etNoteHeader.text.toString(),binding.etNote.text.toString(),this.id )
//                finish()
//
//            }
//            R.id.img_delete_data->{
//                viewModel.deleteData(this.id)
//                finish()
//            }
        }
    }
}