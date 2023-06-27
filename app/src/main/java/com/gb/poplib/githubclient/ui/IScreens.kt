package com.gb.poplib.githubclient.ui

import com.gb.poplib.githubclient.data.GithubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun user(user: GithubUser?): Screen
}