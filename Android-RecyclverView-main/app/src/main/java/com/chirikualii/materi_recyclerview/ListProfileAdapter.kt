package com.chirikualii.materi_recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.chirikualii.materi_recyclerview.databinding.ItemProfileBinding
import com.chirikualii.materi_recyclerview.databinding.ItemProfileHeaderBinding

class ListProfileAdapter  : RecyclerView.Adapter<ViewHolder>(){

    var listData = mutableListOf<Profile>()
    var viewType = 0
    class Holder(val binding: ItemProfileBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(data : Profile){

            binding.tvName.text = data.name
            binding.tvPhone.text = data.phone

            Glide.with(binding.root)
                .load(data.image)
                .circleCrop()
                .into(binding.ivProfile)

        }
    }

    class HeaderHolder(val binding: ItemProfileHeaderBinding):RecyclerView.ViewHolder(binding.root){

        fun bindView(data: Profile){
            binding.tvName.text = data.name
            binding.tvPhone.text = data.phone

            Glide.with(binding.root)
                .load(data.image)
                .into(binding.ivProfile)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        when(viewType){
            0 -> {
                val binding = ItemProfileHeaderBinding.inflate(layoutInflater,parent,false)
                return HeaderHolder(binding)
            }

            1 -> {
                val binding = ItemProfileBinding.inflate(layoutInflater,parent,false)
                return Holder(binding)
            }

            else -> {
                return  null!!
            }

        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(viewType){

            0 ->{
                val headerProfileHolder = holder as HeaderHolder
                headerProfileHolder.bindView(listData[position])
            }

            1 ->{
                val holderProfile = holder as Holder
                holderProfile.bindView(listData[position])
            }

        }


    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0){
            viewType = 0
            return 0
        }
        else {
            viewType = 1
            return 1
        }
    }
    override fun getItemCount(): Int {
        return listData.size
    }

    fun addList(listProfile :List<Profile>){
        listData.clear()
        listData.addAll(listProfile)
        notifyDataSetChanged()
    }
}