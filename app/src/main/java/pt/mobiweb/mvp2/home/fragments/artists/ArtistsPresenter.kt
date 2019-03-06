package pt.mobiweb.mvp2.home.fragments.artists

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import pt.mobiweb.mvp2.home.recycler_view.model.PostModel
import pt.mobiweb.mvp2.utils.retrofit.ApiInterface
import pt.mobiweb.mvp2.utils.retrofit.RetrofitClient

class ArtistsPresenter(
    private var view: ArtistsContract.View,
    private val compositeDisposable: CompositeDisposable): ArtistsContract.Presenter {

    private var postsList: List<PostModel> = arrayListOf()
    private lateinit var jsonApi: ApiInterface

    override fun showAllPosts() {

        //Initial view functions handling
        view.handleProgressbarView(true)

        //Init
        val retrofit = RetrofitClient.instance
        jsonApi = retrofit.create(ApiInterface::class.java)

        getPostsFromAPI()
    }

    private fun getPostsFromAPI() {
        compositeDisposable.add(
            jsonApi.getPost()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    //OnNext
                    { t: List<PostModel> ->
                        postsList = t
                        view.handleProgressbarView(false)
                        view.getPopulatedList(postsList)
                    },

                    //OnError
                    {
                        view.handleProgressbarView(false)
                    })
        )
    }
}