package com.gb.poplib.githubclient.ui.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gb.poplib.githubclient.data.GithubUser
import com.gb.poplib.githubclient.domain.UserPresenter
import com.gb.poplib.githubclient.domain.UserView
import com.gb.poplib.githubclient.databinding.FragmentUserBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment() : MvpAppCompatFragment(), UserView {

    companion object {
        private const val CURRENT_USER = "current_data"

        fun newInstance(user: GithubUser?) : UserFragment {
            val bundle = Bundle().apply {
                putParcelable(CURRENT_USER, user)
            }

            return UserFragment().apply {
                arguments = bundle
            }
        }
    }

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter()
    }

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
}