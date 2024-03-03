package com.appadore.ui.main.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.appadore.R
import com.appadore.data.model.ResponseItem
import com.appadore.ui.main.view.activity.BaseActivity
import com.appadore.ui.main.view.activity.MainActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

class AllGroupListAdapter(private val mContext:BaseActivity)
    : PagedListAdapter<ResponseItem,
        AllGroupListAdapter.ViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem: View = layoutInflater.inflate(R.layout.adapter_grouplist, parent, false)
        return ViewHolder(listItem)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = getItem(position)
        holder.bind(model!!)

    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mGrpFrm: LinearLayout = itemView.findViewById(R.id.group_frm)
        val mName: TextView = itemView.findViewById(R.id.name)
        val mRole: TextView = itemView.findViewById(R.id.role)
        val mBio: TextView = itemView.findViewById(R.id.bio)
        val mPartiCount: TextView = itemView.findViewById(R.id.particip)
        val mUnRead: TextView = itemView.findViewById(R.id.count)
        val mProPic: ImageView = itemView.findViewById(R.id.im_profile)

        fun bind(item: ResponseItem) {
            mGrpFrm.setOnClickListener {
                ((mContext as MainActivity).navigateData(item.id))
            }
            mName.text = item.name
            mRole.text = item.userStatus
            mBio.text = item.bio
            mPartiCount.text = item.participantCount.toString()
            mUnRead.text = item.unreadCount.toString()
            Glide.with(mContext)
                .load(item.groupPhoto)
                .centerCrop()
                .transform(CircleCrop())
                .into(mProPic)
        }
    }

}