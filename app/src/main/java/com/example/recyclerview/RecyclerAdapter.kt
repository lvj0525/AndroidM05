package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerview.data.PopularMovie

class RecyclerAdapter (private val movies: List<PopularMovie>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        private val imageView: ImageView = view.findViewById(R.id.image_view)
        private val title: TextView = view.findViewById(R.id.title)
        private val overview: TextView = view.findViewById(R.id.overview)
        private val rate: TextView = view.findViewById(R.id.rate)
        private val date: TextView = view.findViewById(R.id.date)

        fun bind(popularMovie: PopularMovie) {
            Glide.with(view.context).load("https://image.tmdb.org/t/p/w500${popularMovie.poster_path}").into(imageView)
            title.text = popularMovie.title
            overview.text = popularMovie.overview
            rate.text = popularMovie.vote_average.toString()
            date.text = popularMovie.release_date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val textView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(textView)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }
}