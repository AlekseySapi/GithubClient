package com.gb.poplib.githubclient.domain.common

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}