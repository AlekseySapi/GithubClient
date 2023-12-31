package com.gb.poplib.githubclient.domain

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}