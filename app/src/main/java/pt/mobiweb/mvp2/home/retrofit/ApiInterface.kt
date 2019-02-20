package pt.mobiweb.mvp2.home.retrofit

import io.reactivex.Observable
import pt.mobiweb.mvp2.home.recycler_view.model.PostModel
import retrofit2.http.GET

interface ApiInterface {
    @get:GET("posts")
    val posts: Observable<ArrayList<PostModel>>

    @GET("posts")
    fun getPost(): Observable<List<PostModel>>
}