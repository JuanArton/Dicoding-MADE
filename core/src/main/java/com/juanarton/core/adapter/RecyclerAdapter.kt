package com.juanarton.core.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.juanarton.core.BuildConfig
import com.juanarton.core.R
import com.juanarton.core.databinding.ItemViewBinding
import com.juanarton.core.domain.DataParcel

class RecyclerAdapter(private val listData: List<DataParcel>) : RecyclerView.Adapter<RecyclerAdapter.GridViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val movieData = listData[position]
        holder.bind(movieData)
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(movieData) }
    }

    override fun getItemCount(): Int = listData.size

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemViewBinding.bind(itemView)
        fun bind(movies: DataParcel) {
            with(itemView) {
                val imageLink = buildString{
                    append(BuildConfig.BASE_IMAGE_URL)
                    append(movies.imageLink)
                }
                binding.apply{
                    binding.tvItemTitle.text = movies.title

                    Glide.with(context)
                        .load(imageLink)
                        .centerCrop()
                        .into(imgPoster)
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: DataParcel)
    }
}