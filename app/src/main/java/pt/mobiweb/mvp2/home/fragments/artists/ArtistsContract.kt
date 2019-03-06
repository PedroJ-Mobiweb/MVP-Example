package pt.mobiweb.mvp2.home.fragments.artists

import pt.mobiweb.mvp2.home.recycler_view.model.PostModel

interface ArtistsContract {

    interface View {
        fun getPopulatedList(postsList: List<PostModel>)
        fun handleProgressbarView(show: Boolean)
        fun showConnectionStatus()
    }

    interface Presenter {
        fun showAllPosts()
    }
}