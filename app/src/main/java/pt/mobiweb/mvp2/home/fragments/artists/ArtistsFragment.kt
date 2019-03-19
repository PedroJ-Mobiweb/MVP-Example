package pt.mobiweb.mvp2.home.fragments.artists

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_artists.*
import pt.mobiweb.mvp2.R
import pt.mobiweb.mvp2.home.recycler_view.adapter.PostsAdapter
import pt.mobiweb.mvp2.home.recycler_view.model.PostModel

class ArtistsFragment : Fragment(), ArtistsContract.View {

    //Provides a method for creating new instances of the fragment, a factory method
    companion object {
        fun newInstance(): ArtistsFragment {
            return ArtistsFragment()
        }
    }

    //Variables
    private lateinit var artistsPresenter: ArtistsPresenter
    private var compositeDisposable = CompositeDisposable()

    //Overrides
    override fun populateRecyclerView(postsList: List<PostModel>) {
        frag_artists_recyclerView?.setHasFixedSize(true)
        frag_artists_recyclerView?.layoutManager = LinearLayoutManager(context)
        frag_artists_recyclerView?.adapter = PostsAdapter(postsList)
    }

    override fun handleProgressbarView(show: Boolean) {
        when (show) {
            true -> frag_artists_include_progressbar?.visibility = VISIBLE
            false -> frag_artists_include_progressbar?.visibility = GONE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_artists, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        artistsPresenter = ArtistsPresenter(this, compositeDisposable)
        artistsPresenter.showAllPosts()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

}
