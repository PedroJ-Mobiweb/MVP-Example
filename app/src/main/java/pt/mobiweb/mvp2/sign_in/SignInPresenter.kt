package pt.mobiweb.mvp2.sign_in

import android.text.TextUtils

class SignInPresenter(private var view: SignInContract.View): SignInContract.Presenter {

    override fun onSignIn(email: String, password: String) {
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
    }
}