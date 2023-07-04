package com.gb.poplib.githubclient.domain

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}