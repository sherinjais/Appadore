package com.appadore.ui.main.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.appadore.data.api.ApiHelper
import com.appadore.data.api.RetrofitBuilder
import com.appadore.data.model.Group
import com.appadore.data.model.ResponseItem
import com.appadore.databinding.ActivityMainBinding
import com.appadore.ui.base.ViewModelFactory
import com.appadore.ui.main.view.adapter.AllGroupListAdapter
import com.appadore.ui.main.viewmodel.MainActivityViewModel

class MainActivity : BaseActivity() {
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var mAllGroupListAdapter: AllGroupListAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setUpDatabaseObserver()
    }

    private fun setUpDatabaseObserver() {
        mAllGroupListAdapter = AllGroupListAdapter( this)
        binding.rvData.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL,false)
            adapter = mAllGroupListAdapter
        }
        viewModel.getAllPost()
            .observe(this, Observer { it->
                if (it.size!=0){
                    mAllGroupListAdapter.submitList(it)
                    binding.rvData.adapter = mAllGroupListAdapter
                    mAllGroupListAdapter.notifyDataSetChanged()
                }
                else{
                    setUpApiCallObserver()
                }
            })
    }


    private fun setUpApiCallObserver() {
        viewModel.getPost()
                .observe(this, Observer {
                    if(it.message.contentEquals("OK"))
                    {
                        insertDataToDb(it.result.groups)

                    }
                })
    }

    private fun insertDataToDb(postAPIResponse: List<Group>) {
        for(i in postAPIResponse.indices){
            val model=ResponseItem()
            model.id=postAPIResponse[i].id
            model.bio=postAPIResponse[i].bio
            model.groupPhoto=postAPIResponse[i].group_photo
            model.name=postAPIResponse[i].name
            model.participantCount=postAPIResponse[i].participant_count
            model.private=postAPIResponse[i].private
            model.userStatus=postAPIResponse[i].user_status
            model.unreadCount=postAPIResponse[i].unread_count

            viewModel.insertData(model)
        }

    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService,this),this)
        ).get(MainActivityViewModel::class.java)

    }

    fun navigateData(id: Int?) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("id",id)
        startActivity(intent)
    }
}