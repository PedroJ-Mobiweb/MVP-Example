package pt.mobiweb.mvp2.home.recycler_view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import pt.mobiweb.mvp2.R
import pt.mobiweb.mvp2.home.recycler_view.model.PostModel

class PostsAdapter(private val postsList: List<PostModel>): RecyclerView.Adapter<PostsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.item_post_layout, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = postsList.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val post = postsList[p1]

        with(post){
            p0.userId.text = userId.toString()
            p0.id.text = id.toString()
            p0.title.text = title
            p0.body.text = body
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val userId: TextView = itemView.findViewById(R.id.act_home_tv_userId)
        val id: TextView = itemView.findViewById(R.id.act_home_tv_id)
        val title: TextView = itemView.findViewById(R.id.act_home_tv_title)
        val body: TextView = itemView.findViewById(R.id.act_home_tv_body)
    }
}