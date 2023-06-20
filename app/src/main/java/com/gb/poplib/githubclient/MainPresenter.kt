package com.gb.poplib.githubclient

class MainPresenter(val view: MainView) {
    val counters = CountersModel()

    fun onViewClick(id: Int, countersViewId: List<Int>) {
        countersViewId.indexOf(id).let {
            view.setButtonText(it, counters.increase(it).toString())
        }
    }
}