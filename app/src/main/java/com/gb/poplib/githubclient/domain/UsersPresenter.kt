package com.gb.poplib.githubclient.domain

import com.gb.poplib.githubclient.data.GithubUser
import com.gb.poplib.githubclient.data.GithubUserRepo
import com.gb.poplib.githubclient.ui.adapter.IUserItemView
import com.gb.poplib.githubclient.ui.adapter.IUserListPresenter
import com.gb.poplib.githubclient.data.GithubUser
import com.gb.poplib.githubclient.data.GithubUserRepo
import com.gb.poplib.githubclient.ui.adapter.IUserItemView
import com.gb.poplib.githubclient.ui.adapter.IUserListPresenter
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UsersPresenter(
    val usersRepo: GithubUserRepo, val router: Router
) : MvpPresenter<UsersView>() {

    class UserListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun bindView(view: IUserItemView) {
            val user = users[view.itemPosition]
            view.setLogin(user.login)
        }

        override fun getCount() = users.size
    }

    val userListPresenter = UserListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData()

        userListPresenter.itemClickListener = { itemView ->
            // TODO: переход на экран пользователя
        }
    }

    fun loadData() {
        val users = usersRepo.getUsers()
        userListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}