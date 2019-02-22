package pt.mobiweb.mvp2.utils.retrofit

import io.reactivex.Observable
import pt.mobiweb.mvp2.home.recycler_view.model.PostModel
import pt.mobiweb.mvp2.utils.retrofit.models.UserCredentials
import retrofit2.http.*

interface ApiInterface {

    //Log-In Example
    @Headers(value = ["authorization: fTxmf4-7lGU:APA91bGgaw_-Lqg6vjqqd9ayHm5D45CxsdrGB55t1KC9BP7gugGA8I_A1peSvRu0HN5dvl7Di9apedgoJK8h451mBHxIhQwK_TA93dV0i-zyfMOxRUXGCSR5drjNp1thMnuij20Kr4qX"])
    @POST("auth")
    fun logIn(@Body userCredentials: UserCredentials): Observable<UserCredentials>

    @GET("posts")
    fun getPost(): Observable<List<PostModel>>
}