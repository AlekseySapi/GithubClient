package com.gb.poplib.githubclient.domain

import com.gb.poplib.githubclient.domain.common.IItemView

interface IUserItemView : IItemView {
    fun setLogin(login: String)
    fun loadAvatar(url: String)
}