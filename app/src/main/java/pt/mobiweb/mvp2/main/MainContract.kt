package pt.mobiweb.mvp2.main

/**
 * Defines the Contract between the View and the Presenter
 */

interface MainContract {

    interface View{
        fun navigateToSignInScreen()
    }
    interface Presenter{
        fun signInButtonClick()
    }
}