package pt.mobiweb.mvp2.home

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import pt.mobiweb.mvp2.home.recycler_view.model.PostModel
import pt.mobiweb.mvp2.home.retrofit.ApiInterface
import pt.mobiweb.mvp2.home.retrofit.RetrofitClient

class HomePresenter(private var view: HomeContract.View): HomeContract.Presenter {

    private var postsList: List<PostModel> = arrayListOf()
    private lateinit var jsonApi: ApiInterface
    private var compositeDisposable = CompositeDisposable()

    override fun showAllPosts() {

        val retrofit = RetrofitClient.instance
        jsonApi = retrofit.create(ApiInterface::class.java)

        compositeDisposable.add(
            jsonApi.getPost()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { return@doOnError }
                .doOnNext {
                        t: List<PostModel> -> postsList = t
                        view.getPopulatedList(postsList)}
                .subscribe())
    }
}