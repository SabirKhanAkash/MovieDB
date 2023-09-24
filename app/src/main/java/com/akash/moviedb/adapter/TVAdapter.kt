package com.akash.moviedb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akash.moviedb.BuildConfig
import com.akash.moviedb.R
import com.akash.moviedb.model.TVShowDetails
import com.bumptech.glide.Glide

class TVAdapter(private var tv_shows: List<TVShowDetails>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun updateData(trendingTVShows: List<TVShowDetails>) {
        tv_shows = trendingTVShows
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.movie_item, parent, false)
        return TVShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TVShowViewHolder) {
            populateItemRows(holder, position)
        }
    }

    private fun populateItemRows(holder: TVShowViewHolder, position: Int) {
        val tv_show = tv_shows[position]
        holder.movieTitle.text = tv_show.name
        val posterUrl = BuildConfig.POSTER_BASE_URL + tv_show.poster_path
        Glide.with(holder.moviePoster)
            .load(posterUrl)
            .into(holder.moviePoster)
    }

    override fun getItemCount(): Int {
        return tv_shows.size
    }

    inner class TVShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var movieTitle: TextView
        var moviePoster: ImageView
        var movieLayout: LinearLayout

        init {
            movieTitle = itemView.findViewById(R.id.movieTitle)
            moviePoster = itemView.findViewById(R.id.moviePoster)
            movieLayout = itemView.findViewById(R.id.movieLayout)
        }
    }
}
