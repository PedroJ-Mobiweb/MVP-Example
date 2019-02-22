package pt.mobiweb.mvp2.home

interface HomeContract {

    interface View{
        fun showFragments()
    }

    interface Presenter{
        fun inflateView()
    }
}