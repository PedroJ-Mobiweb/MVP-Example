package pt.mobiweb.mvp2.home

import pt.mobiweb.mvp2.home.recycler_view.model.PostModel

interface HomeContract {

    interface View{
        fun getPopulatedList(postsList: List<PostModel>)
        fun handleErrorCardView(show: Boolean)
        fun handleProgressbarView(show: Boolean)
        fun handleInternetToast(show: Boolean)
    }

    interface Presenter{
        fun showAllPosts()
    }
}