package pt.mobiweb.mvp2.home

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import pt.mobiweb.mvp2.R
import pt.mobiweb.mvp2.home.fragments.artists.ArtistsFragment
import pt.mobiweb.mvp2.utils.Utils

class HomeActivity : AppCompatActivity(), HomeContract.View {

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
        act_home_bottomNavigationView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        homePresenter = HomePresenter(this)

        homePresenter.inflateView()

    }
}
