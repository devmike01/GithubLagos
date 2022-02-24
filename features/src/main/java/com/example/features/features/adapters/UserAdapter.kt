package com.example.features.features.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.common.custom_texts.TitleTextView
import com.example.core.repository.models.user.Item
import com.example.features.R
import com.example.features.utils.interfaces.OnClickUserListener
import javax.inject.Inject

class UserAdapter @Inject constructor() : PagingDataAdapter<Item, UserAdapter.UserViewHolder>(diffCallback) {

    var onClickUser : OnClickUserListener? = null

    fun addOnClickUserListener(callback: OnClickUserListener){
        this.onClickUser = callback
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position)?.run {
            holder.bindTo(this){
                onClickUser?.onClickUser(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder{
        Log.d("onCreateViewHolder", "onCreateViewHolder")
        return LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item,
            parent, false).let {
            UserViewHolder(it)
        }
    }

    companion object {
        /**
         * This diff callback informs the PagedListAdapter how to compute list differences when new
         * PagedLists arrive.
         * <p>
         * When you add a Cheese with the 'Add' button, the PagedListAdapter uses diffCallback to
         * detect there's only a single item difference from before, so it only needs to animate and
         * rebind a single view.
         *
         * @see DiffUtil
         */
        private val diffCallback = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
                oldItem.itemId == newItem.itemId

            /**
             * Note that in kotlin, == checking on data classes compares all contents, but in Java,
             * typically you'll implement Object#equals, and use it to compare object contents.
             */
            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
                oldItem == newItem
        }
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val userImageView = itemView.findViewById<ImageView>(R.id.avatar_iv)
        private val scoreTv = itemView.findViewById<TextView>(R.id.score_tv)
        private val nameTv = itemView.findViewById<TitleTextView>(R.id.name_tv)

        fun bindTo(item: Item, onClickUser: (String) -> Unit){
            scoreTv.text = itemView.context.getString(R.string.score_title, item.score.toString())
            nameTv.setTitleText(item.login)
            Glide.with(itemView.context).load(item.avatarUrl).into(userImageView)

            itemView.setOnClickListener { item.login?.run{onClickUser.invoke(this)} }
        }
    }
}