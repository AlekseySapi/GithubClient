package com.gb.poplib.githubclient.domain.repository

import com.gb.poplib.githubclient.domain.common.IItemView

interface IRepositoryItemView : IItemView {
    fun setName(name: String)
}