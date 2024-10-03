package com.example.postsapp.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postsapp.models.PostModelX
import com.example.postsapp.databinding.ItemPostBinding

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.Holder>() {

    var posts :ArrayList<PostModelX>?=null
    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemPostBinding.inflate(android.view.LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return if (posts==null) 0 else posts!!.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
//        holder.bind(posts!!.get(position))
        // or
        posts?.let {
            holder.bind(it.get(position))
        }
    }

inner class Holder(val binding: ItemPostBinding)
    :RecyclerView.ViewHolder(binding.root){

        init {
            binding.root.setOnClickListener {
                onItemClickListener?.onItemClick(posts!!.get(adapterPosition).id!!)
            }
        }
    fun bind(PostModelX: PostModelX) {
        binding.title.text = PostModelX.title
        binding.body.text = PostModelX.body

    }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}