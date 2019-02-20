package pt.mobiweb.mvp2.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_home.*
import pt.mobiweb.mvp2.R
import pt.mobiweb.mvp2.home.recycler_view.adapter.PostsAdapter
import pt.mobiweb.mvp2.home.recycler_view.model.PostModel

class HomeActivity : AppCompatActivity(), HomeContract.View {

    //Variables
    private lateinit var homePresenter: HomePresenter
     //Overrides
    override fun getPopulatedList(postsList: List<PostModel>) {
         home_rv_posts?.setHasFixedSize(true)
         home_rv_posts?.layoutManager = LinearLayoutManager(this)
         home_rv_posts?.adapter = PostsAdapter(postsList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        homePresenter = HomePresenter(this)

        homePresenter.showAllPosts()
    }
}
