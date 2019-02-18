package pt.mobiweb.mvp2.main

/**
 * Responsible for handling actions from the View
 */

class MainPresenter(private var view: MainContract.View): MainContract.Presenter {

    override fun signInButtonClick() {
        view.navigateToSignInScreen()
    }
}