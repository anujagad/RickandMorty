package com.example.sdo_task.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sdo_task.R
import com.example.sdo_task.databinding.ItemCharacterBinding
import com.example.sdo_task.model.EpisodeList

class RvListAdapter(private val onClickListener: OnClickListener): RecyclerView.Adapter<RvListAdapter.RvListViewHolder>() {

    private var listData : List<EpisodeList>?=null
    private var binding : ItemCharacterBinding?=null

    fun setList(list :List<EpisodeList> ){
        this.listData = list
    }
    class RvListViewHolder(binding : ItemCharacterBinding?) :RecyclerView.ViewHolder(binding?.root!!){
        fun bind(data : EpisodeList,binding : ItemCharacterBinding?){
            binding?.tvName?.text = data.name
            binding?.tvStatus?.text = data.status
            binding?.ivCharacter?.let {
                Glide.with(itemView.context)
                    .load(data.image)
                    .placeholder(R.drawable.ic_placeholder_app_inbox_circle_image)
                    .into(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvListViewHolder {
        binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RvListViewHolder(binding)
    }

    override fun getItemCount(): Int {
      return  if(listData ==null) return 0 else listData?.size!!
    }

    override fun onBindViewHolder(holder: RvListViewHolder, position: Int) {
        listData?.get(position)?.let { holder.bind(it,binding) }
        binding?.root?.setOnClickListener {
            onClickListener.onClick(listData?.get(position))
        }
    }

    class OnClickListener(val clickListener: (data: EpisodeList?) -> Unit) {
        fun onClick(data: EpisodeList?) = clickListener(data)
    }
}