package com.example.newsfeed


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter(private val listener: NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {
    private val items: ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): NewsViewHolder {
        val view= LayoutInflater.from(viewGroup.context).inflate(R.layout.item_news,viewGroup,false)
        val viewHolder=NewsViewHolder(view)
        view.setOnClickListener { listener.onItemClicked(items[viewHolder.adapterPosition]) }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem=items[position]a
        holder.titleView.text=currentItem.title
        holder.authorView.text=currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image)
    }
    fun updateNews(updatedNews:ArrayList<News>){
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()
    }
}

class NewsViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    val titleView: TextView =itemView.findViewById(R.id.title)
    val image: ImageView =itemView.findViewById(R.id.image)
    val authorView: TextView =itemView.findViewById(R.id.author)
}

interface NewsItemClicked{
    fun onItemClicked(item:News)
}