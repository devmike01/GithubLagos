package com.example.features.features.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.common.custom_texts.TitleTextView
import com.example.core.repository.models.favorite.FavoriteUser
import com.example.features.R
import com.example.features.utils.interfaces.OnClickUserListener
import com.example.features.utils.interfaces.OnDeleteFavoriteUserListener
import com.example.features.utils.interfaces.OnFavoriteClickListener
import javax.inject.Inject

class FavoriteUserAdapter @Inject constructor() : PagingDataAdapter<FavoriteUser, FavoriteUserAdapter.UserViewHolder>(diffCallback) {

    private var onClickUser : OnClickUserListener? = null

    private var onDeleteFavoriteUser : OnDeleteFavoriteUserListener? = null

    fun addOnClickUserListener(callback: OnClickUserListener){
        this.onClickUser = callback
    }

    fun addOnDeleteFavoriteUserListener(callback: OnDeleteFavoriteUserListener){
        this.onDeleteFavoriteUser = callback
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position)?.run {
            holder.bindTo(this, {
                onClickUser?.onClickUser(it)
            }, {
                it.run {
                    onDeleteFavoriteUser?.onDeleteFavoriteUser(this, position)
                }
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder{
        Log.d("onCreateViewHolder", "onCreateViewHolder")
        return LayoutInflater.from(parent.context).inflate(
            R.layout.recyclerview_item,
            parent, false).let {
            UserViewHolder(it)
        }
    }


    companion  object {
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
        private  val diffCallback = object : DiffUtil.ItemCallback<FavoriteUser>() {
            override fun areItemsTheSame(oldItem: FavoriteUser, newItem: FavoriteUser): Boolean{

                return oldItem.itemId == newItem.itemId
            }

            /**
             * Note that in kotlin, == checking on data classes compares all contents, but in Java,
             * typically you'll implement Object#equals, and use it to compare object contents.
             */
            override fun areContentsTheSame(oldItem: FavoriteUser, newItem: FavoriteUser): Boolean =
                oldItem == newItem
        }
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val userImageView = itemView.findViewById<ImageView>(R.id.avatar_iv)
        private val scoreTv = itemView.findViewById<TextView>(R.id.score_tv)
        private val nameTv = itemView.findViewById<TitleTextView>(R.id.name_tv)
        private val favoriteBtn = itemView.findViewById<ImageButton>(R.id.favorite_btn)

        fun bindTo(item: FavoriteUser, onClickUser: (String) -> Unit, onDeleteFavorite: (Int) -> Unit){
            scoreTv.text = itemView.context.getString(R.string.score_title, item.score.toString())
            nameTv.setTitleText(item.login)
            favoriteBtn.setImageResource(android.R.drawable.ic_delete)
            Glide.with(itemView.context).load(item.avatarUrl).into(userImageView)
            favoriteBtn.setOnClickListener { onDeleteFavorite.invoke(
                item.itemId!!
            )
                Log.d("bindTo", "bindTo ${item.itemId}")
            }
            itemView.setOnClickListener { item.login?.run{onClickUser.invoke(this)} }
        }

    }
}