package pt.mobiweb.mvp2.sign_in

import android.text.TextUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import pt.mobiweb.mvp2.utils.retrofit.models.UserCredentials
import pt.mobiweb.mvp2.utils.retrofit.ApiInterface
import pt.mobiweb.mvp2.utils.retrofit.RetrofitClient
import pt.mobiweb.mvp2.utils.dropBreadcrumb

class SignInPresenter(private var view: SignInContract.View): SignInContract.Presenter {

    private lateinit var jsonApi: ApiInterface
    private var compositeDisposable = CompositeDisposable()

    override fun onSignIn(email: String, password: String) {
        logInValidator(email, password)
    }

    //Log In Validations
    private fun logInValidator(email: String, password: String){
        try {
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6){
                //All good
                view.onSuccess("Success")
                view.navigateToSuccessfulSignIn()
            }else if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
                //Email and password empty
                view.onError("Email And Password Fields Are Required")
            }else if (TextUtils.isEmpty(email) && password.length < 6) {
                //Email empty and password not long enough
                view.onError("Email Is Empty And Password Length Must Be Higher Than 6")
            }else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length < 6) {
                //Invalid email and password not long enough
                view.onError("Email Is Invalid And Password Length Must Be Higher Than 6")
            }else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6) {
                //Invalid email
                view.onError("Email Is Invalid")
            }else if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length < 6) {
                //Invalid email
                view.onError("Password Length Must Be Higher Than 6")
            }
        } catch (e: Exception) {
            view.onError(e.message.toString())
        }
    }

    //Log In Example
    private fun logInApiRequest(email: String, password: String){
        val retrofit = RetrofitClient.instance
        jsonApi = retrofit.create(ApiInterface::class.java)

        compositeDisposable.add(
            jsonApi.logIn(
                UserCredentials(email, password)
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .dropBreadcrumb()
                .subscribe(
                    //OnNext
                    { view.navigateToSuccessfulSignIn() },

                    //OnError
                    {  }))
    }
}