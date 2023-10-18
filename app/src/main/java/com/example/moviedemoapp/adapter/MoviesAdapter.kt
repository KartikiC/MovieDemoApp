package com.example.moviedemoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedemoapp.databinding.MoviesLayoutAdapterBinding
import com.example.moviedemoapp.helper.Constants.IMAGE_PATH
import com.example.moviedemoapp.models.Result
import com.squareup.picasso.Picasso

/**
 * Movie Adapter to create ViewHolder and Binds View to RecyclerView
 */
class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    inner class MoviesViewHolder(val binding: MoviesLayoutAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)

    var onItemClick: ((Result) -> Unit)? = null

    private val diffCallback = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return newItem == oldItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var moviesList: List<Result>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            MoviesLayoutAdapterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = moviesList.size.coerceAtMost(10)

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val currentMovie = moviesList[position]

        holder.binding.apply {
            Picasso.get().load(IMAGE_PATH + currentMovie.poster_path)
                .into(imageView);
        }
        holder.binding.imageView.setOnClickListener {
            onItemClick?.invoke(currentMovie)
        }
    }
}