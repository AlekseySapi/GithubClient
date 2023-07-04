package com.gb.poplib.githubclient.domain

import com.gb.poplib.githubclient.domain.IItemView

interface IUserItemView : IItemView {
    fun setLogin(login: String)
    fun loadAvatar(url: String)
}