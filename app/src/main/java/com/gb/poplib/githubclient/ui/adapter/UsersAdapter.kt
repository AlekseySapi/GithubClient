package com.gb.poplib.githubclient.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.gb.poplib.githubclient.domain.IImageLoader
import com.gb.poplib.githubclient.domain.IUserItemView
import com.gb.poplib.githubclient.domain.IUserListPresenter
import com.gb.poplib.githubclient.databinding.UserItemBinding

class UsersAdapter(
    val presenter: IUserListPresenter,
    val imageLoader: IImageLoader<ImageView>
) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    inner class ViewHolder(
        val binding: UserItemBinding
    ) : RecyclerView.ViewHolder(binding.root), IUserItemView {

        override fun setLogin(login: String) = with(binding) {
            userLogin.text = login
        }

        override fun loadAvatar(url: String) = with(binding) {
            imageLoader.loadInto(url, binding.userAvatar)
        }

        override var itemPosition = -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }
    }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return presenter.bindView(
            holder.apply {
                itemPosition = position
            }
        )
    }
}