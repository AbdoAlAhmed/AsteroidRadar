package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.databinding.ItemListBinding

class MainAdapter (val onClickListener:Onclick) : ListAdapter<Asteroid, MainAdapter.ViewHolder>(DiffcallBack) {

    object DiffcallBack : DiffUtil.ItemCallback<Asteroid>() {
        override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem == newItem
        }

    }

    class ViewHolder(private val bind: ItemListBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(asteroid: Asteroid) {
            bind.asteroid = asteroid
            bind.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val asteroid = getItem(position)
        holder.binding(asteroid)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(asteroid)
        }

    }
    class Onclick(val OnClickListener :(asteroid:Asteroid)->Unit){
        fun onClick(asteroid: Asteroid) = OnClickListener(asteroid)
    }
}
