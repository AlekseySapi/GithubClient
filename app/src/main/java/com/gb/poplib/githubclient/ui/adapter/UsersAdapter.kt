package com.gb.poplib.githubclient.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gb.poplib.githubclient.databinding.UserItemBinding
import com.gb.poplib.githubclient.domain.IUserItemView
import com.gb.poplib.githubclient.domain.IUserListPresenter

class UsersAdapter(
    val presenter: IUserListPresenter
) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    inner class ViewHolder(
        val binding: UserItemBinding
    ) : RecyclerView.ViewHolder(binding.root), IUserItemView {

        override fun setLogin(login: String) = with(binding) {
            userLogin.text = login
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