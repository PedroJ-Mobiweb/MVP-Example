package pt.mobiweb.mvp2.home.retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val baseUrl = "https://jsonplaceholder.typicode.com/"
    private var mInstance: Retrofit?= null

    val instance: Retrofit
    get() {
        if (mInstance == null){
            mInstance = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        return mInstance!!
    }
}