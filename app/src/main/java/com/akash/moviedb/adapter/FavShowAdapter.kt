package com.akash.moviedb.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akash.moviedb.BuildConfig
import com.akash.moviedb.R
import com.akash.moviedb.data.local.roomdb.entity.Show
import com.akash.moviedb.utils.SharedPref
import com.akash.moviedb.view.activity.SingleMovieActivity
import com.akash.moviedb.view.activity.SingleTVActivity
import com.bumptech.glide.Glide

class FavShowAdapter(private val context: Context, private var show: List<Show>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val sharedPref: SharedPref = SharedPref()
    fun updateData(favShow: List<Show>) {
        show = favShow
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MovieViewHolder) {
            populateItemRows(holder, position)
        }
    }

    private fun populateItemRows(holder: MovieViewHolder, position: Int) {
        val show = show[position]

        if (show.name.isNullOrEmpty()) {
            holder.movieTitle.text = show.title
        } else {
            holder.movieTitle.text = show.name
        }

        val posterUrl = BuildConfig.POSTER_BASE_URL + show.poster_path
        Glide.with(holder.moviePoster)
            .load(posterUrl)
            .into(holder.moviePoster)

        holder.movieLayout.setOnClickListener {
            val intent: Intent
            val selectedMovieId = show.id
            if (show.name.isNullOrEmpty()) {
                intent = Intent(context, SingleMovieActivity::class.java)
                sharedPref.setInt(context, "selectedMovieId", selectedMovieId!!)
            } else {
                intent = Intent(context, SingleTVActivity::class.java)
                sharedPref.setInt(context, "selectedTVId", selectedMovieId!!)
            }

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return show.size
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
