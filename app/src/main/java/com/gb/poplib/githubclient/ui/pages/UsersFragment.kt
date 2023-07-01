package com.gb.poplib.githubclient.ui.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.gb.poplib.githubclient.data.GithubUserRepo
import com.gb.poplib.githubclient.domain.UsersPresenter
import com.gb.poplib.githubclient.domain.UsersView
import com.gb.poplib.githubclient.ui.App
import com.gb.poplib.githubclient.ui.adapter.UsersAdapter
import com.gb.poplib.githubclient.ui.AndroidScreens
import com.gb.poplib.githubclient.databinding.FragmentUsersBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(GithubUserRepo(), App.instance.router, AndroidScreens())
    }

    private var adapter: UsersAdapter? = null

    private var _binding: FragmentUsersBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {
        adapter = UsersAdapter(presenter.userListPresenter)

        binding.users.layoutManager = LinearLayoutManager(context)
        binding.users.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}