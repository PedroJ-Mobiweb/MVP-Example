package pt.mobiweb.mvp2.home

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import pt.mobiweb.mvp2.home.recycler_view.model.PostModel
import pt.mobiweb.mvp2.utils.InternetCheck
import pt.mobiweb.mvp2.utils.dropBreadcrumb
import pt.mobiweb.mvp2.utils.retrofit.ApiInterface
import pt.mobiweb.mvp2.utils.retrofit.RetrofitClient

class HomePresenter(private var view: HomeContract.View): HomeContract.Presenter {

    private var postsList: List<PostModel> = arrayListOf()
    private lateinit var jsonApi: ApiInterface
    private var compositeDisposable = CompositeDisposable()

    override fun showAllPosts() {

        //Initial view functions handling
        view.handleProgressbarView(true)
        view.handleErrorCardView(false)

        //Init
        val retrofit = RetrofitClient.instance
        jsonApi = retrofit.create(ApiInterface::class.java)

        //Lambda function to check if there is a connection to the internet
        InternetCheck{ internet->
            if (internet){
                //Try to fetch data from API
                getPosts()
            }else {
                //Handle view functions if there is no internet connection
                view.handleInternetToast(false)
                view.handleProgressbarView(false)
                view.handleErrorCardView(true)
            }
        }
    }

    //Try to fetch data from API
    private fun getPosts(){
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
                        view.getPopulatedList(postsList) },

                    //OnError
                    {
                        view.handleProgressbarView(false)
                        view.handleErrorCardView(true) }))
    }
}