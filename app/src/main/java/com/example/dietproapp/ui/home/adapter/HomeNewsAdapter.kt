package com.example.dietproapp.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.dietproapp.R
import com.example.dietproapp.core.data.source.remote.response.Article
import com.example.dietproapp.databinding.ListHomenewsBinding

@SuppressLint("NotifyDataSetChanged")
class HomeNewsAdapter : RecyclerView.Adapter<HomeNewsAdapter.ViewHolder>() {

    var data = ArrayList<Article>()

    inner class ViewHolder(val itemBinding: ListHomenewsBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Article, position: Int) {
            itemBinding.apply {
                tvTitleInfo.text = item.title
                tvTimeAgoInfo.text = item.author
                tvNameSourceInfo.text = item.source!!.name
                tvDateTimeInfo.text = item.publishedAt

//                Glide.with(root)
//                    .load(item.urlToImage)
//                    .into(imgThumbnailInfo)
                if (item.urlToImage == null) {
                    imgThumbnailInfo.setImageResource(R.drawable.maintenance)
                } else {
                    Glide.with(root)
                        .load(item.urlToImage)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imgThumbnailInfo)
                }
            }
        }
    }

    fun addItems(items: List<Article>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListHomenewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
