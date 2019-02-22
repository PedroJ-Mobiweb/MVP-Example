package pt.mobiweb.mvp2.home

class HomePresenter(private var view: HomeContract.View): HomeContract.Presenter {

    override fun inflateView() {
        view.showFragments()
    }

}