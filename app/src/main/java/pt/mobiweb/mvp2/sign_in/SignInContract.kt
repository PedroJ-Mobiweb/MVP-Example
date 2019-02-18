package pt.mobiweb.mvp2.sign_in

interface SignInContract {
    interface View{
        fun onSuccess(message: String)
        fun onError(message: String)
        fun navigateToSuccessfulSignIn()
    }

    interface Presenter{
        fun onSignIn(email: String, password: String)
    }
}