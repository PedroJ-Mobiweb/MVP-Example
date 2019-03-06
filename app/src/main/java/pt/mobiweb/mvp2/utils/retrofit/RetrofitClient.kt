package pt.mobiweb.mvp2.utils.retrofit

import okhttp3.OkHttpClient
import pt.mobiweb.mvp2.utils.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private var mInstance: Retrofit?= null
    private val okHttpClient = OkHttpClient()

    val instance: Retrofit
    get() {
        okHttpClient.newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        if (mInstance == null){
            mInstance = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_HTTPS)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        return mInstance!!
    }
}