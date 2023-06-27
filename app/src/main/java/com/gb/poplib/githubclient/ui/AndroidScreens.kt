package com.gb.poplib.githubclient.ui

import com.gb.poplib.githubclient.data.GithubUser
import com.gb.poplib.githubclient.ui.pages.UserFragment
import com.gb.poplib.githubclient.ui.pages.UsersFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens(private val user: GithubUser? = null) : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }

    override fun user(user: GithubUser?): Screen {
        return FragmentScreen { UserFragment.newInstance(user) }
    }
}