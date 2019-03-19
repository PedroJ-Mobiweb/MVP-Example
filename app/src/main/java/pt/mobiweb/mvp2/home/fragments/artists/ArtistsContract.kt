package pt.mobiweb.mvp2.home.fragments.artists

import pt.mobiweb.mvp2.home.recycler_view.model.PostModel

interface ArtistsContract {

    interface View {
        fun populateRecyclerView(postsList: List<PostModel>)
        fun handleProgressbarView(show: Boolean)
    }

    interface Presenter {
        fun showAllPosts()
    }
}