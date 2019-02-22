package pt.mobiweb.mvp2.home

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.View.GONE
import android.view.View.VISIBLE
import kotlinx.android.synthetic.main.activity_home.*
import pt.mobiweb.mvp2.R
import pt.mobiweb.mvp2.home.fragments.artists.ArtistsFragment
import pt.mobiweb.mvp2.utils.ActivityUtils

//This is an extension to the FragmentManager, to make it easier and cleaner to replace a fragment
inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

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
                ActivityUtils.addFragmentToActivity(supportFragmentManager, ArtistsFragment.newInstance(), R.id.act_home_fragContainer)
                //Replacing the fragment inside the lambda
                supportFragmentManager.inTransaction { replace(R.id.act_home_fragContainer, ArtistsFragment.newInstance()) }
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
