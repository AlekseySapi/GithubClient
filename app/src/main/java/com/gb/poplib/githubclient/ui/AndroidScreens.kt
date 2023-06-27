package com.gb.poplib.githubclient.ui

import com.gb.poplib.githubclient.ui.pages.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
}