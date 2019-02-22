package pt.mobiweb.mvp2.home.fragments.artists

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import pt.mobiweb.mvp2.home.HomeContract
import pt.mobiweb.mvp2.home.recycler_view.model.PostModel
import pt.mobiweb.mvp2.utils.InternetCheck
import pt.mobiweb.mvp2.utils.dropBreadcrumb
import pt.mobiweb.mvp2.utils.retrofit.ApiInterface
import pt.mobiweb.mvp2.utils.retrofit.RetrofitClient

class ArtistsPresenter(private var view: ArtistsContract.View): ArtistsContract.Presenter {

    private var postsList: List<PostModel> = arrayListOf()
    private lateinit var jsonApi: ApiInterface
    private var compositeDisposable = CompositeDisposable()

    override fun showAllPosts() {

        //Initial view functions handling
        view.handleProgressbarView(true)

        //Init
        val retrofit = RetrofitClient.instance
        jsonApi = retrofit.create(ApiInterface::class.java)

        //Lambda function to check if there is a connection to the internet
        InternetCheck { internet ->
            if (internet) {
                //Try to fetch data from API
                getPosts()
            } else {
                //Handle view functions if there is no internet connection
                view.handleProgressbarView(false)
            }
        }
    }

    //Try to fetch data from API
    private fun getPosts() {
        compositeDisposable.add(
            jsonApi.getPost()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .dropBreadcrumb()
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