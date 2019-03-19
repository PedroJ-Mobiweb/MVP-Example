package pt.mobiweb.mvp2.home

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.View.VISIBLE
import kotlinx.android.synthetic.main.activity_home.*
import pt.mobiweb.mvp2.R
import pt.mobiweb.mvp2.home.fragments.artists.ArtistsFragment
import pt.mobiweb.mvp2.utils.BaseActivity
import pt.mobiweb.mvp2.utils.GlobalVariables
import pt.mobiweb.mvp2.utils.Utils



class HomeActivity : BaseActivity(), HomeContract.View {

    //Variables
    private lateinit var homePresenter: HomePresenter
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_songs -> {

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_albums -> {

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_artists -> {
                Utils.replaceFragment(supportFragmentManager, ArtistsFragment.newInstance(), R.id.act_home_fragContainer)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    //Overrides
    override fun showFragments() {
        act_home_bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        homePresenter = HomePresenter(this)

        homePresenter.inflateView()
    }

    override fun onResume() {
        super.onResume()
        if (!GlobalVariables.connectionStatus)
            include_connection_snackbar.visibility = VISIBLE
    }
}
