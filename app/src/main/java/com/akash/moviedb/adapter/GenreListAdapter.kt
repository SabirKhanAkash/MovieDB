package com.akash.moviedb.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akash.moviedb.R
import com.akash.moviedb.model.Genre

class GenreListAdapter(private var context: Context, private var genreList: List<Genre>) :
    RecyclerView.Adapter<GenreListAdapter.ViewHolder>() {

    fun updateData(genre: List<Genre>) {
        genreList = genre
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.genre_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = genreList[position]
        holder.genreName.text = data.name
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val genreName: TextView = itemView.findViewById(R.id.genreName)
    }
}
