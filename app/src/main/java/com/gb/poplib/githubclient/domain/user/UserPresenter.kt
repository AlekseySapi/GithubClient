package com.gb.poplib.githubclient.domain.user

import android.text.method.TextKeyListener.clear
import com.gb.poplib.githubclient.data.GithubRepository
import com.gb.poplib.githubclient.data.IGithubRepositoriesRepo
import com.gb.poplib.githubclient.domain.repository.IRepositoryItemView
import com.gb.poplib.githubclient.domain.repository.IRepositoryListPresenter
import com.gb.poplib.githubclient.ui.IScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import java.util.Collections.addAll

class UserPresenter(
    val uiScheduler: Scheduler,
    val repositoriesRepo: IGithubRepositoriesRepo,
    val router: Router,
    val screens: IScreens
) : MvpPresenter<UserView>() {

    class RepositoryListPresenter : IRepositoryListPresenter {
        val repositories = mutableListOf<GithubRepository>()
        override var itemClickListener: ((IRepositoryItemView) -> Unit)? = null

        override fun bindView(view: IRepositoryItemView) {
            val repository = repositories[view.itemPosition]

            repository.name?.let { view.setName(it) }
        }

        override fun getCount() = repositories.size
    }

    val repositoryListPresenter = RepositoryListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData()

        repositoryListPresenter.itemClickListener = {itemView ->
            val repository = repositoryListPresenter.repositories[itemView.itemPosition]
            router.navigateTo(screens.repository(repository))
        }
    }

    fun loadData() {
        repositoriesRepo.getRepositories()
            .observeOn(uiScheduler)
            .subscribe({ githubRepositories ->
                repositoryListPresenter.repositories.apply {
                    clear()
                    addAll(githubRepositories)
                }
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}