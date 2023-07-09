package com.gb.poplib.githubclient.ui.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.gb.poplib.githubclient.data.ApiHolder
import com.gb.poplib.githubclient.data.GithubUser
import com.gb.poplib.githubclient.data.RetrofitGithubRepositoriesRepo
import com.gb.poplib.githubclient.domain.user.UserPresenter
import com.gb.poplib.githubclient.domain.user.UserView
import com.gb.poplib.githubclient.ui.AndroidScreens
import com.gb.poplib.githubclient.ui.App
import com.gb.poplib.githubclient.ui.adapter.RepositoryAdapter
import com.gb.poplib.githubclient.databinding.FragmentUserBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment() : MvpAppCompatFragment(), UserView {

    companion object {
        private const val CURRENT_USER = "current_user"

        fun newInstance(user: GithubUser?): UserFragment {
            return UserFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(CURRENT_USER, user)
                }
            }
        }
    }

    private val presenter: UserPresenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(CURRENT_USER)
        val reposUrl = user?.reposUrl ?: ""

        UserPresenter(
            AndroidSchedulers.mainThread(),
            RetrofitGithubRepositoriesRepo(ApiHolder.api, reposUrl),
            App.instance.router,
            AndroidScreens()
        )
    }

    private var adapter: RepositoryAdapter? = null

    private var _binding: FragmentUserBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<GithubUser>(CURRENT_USER)?.let {
            binding.userLogin.setText(it.login)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {
        adapter = RepositoryAdapter(presenter.repositoryListPresenter)

        binding.repositories.layoutManager = LinearLayoutManager(context)
        binding.repositories.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }
}