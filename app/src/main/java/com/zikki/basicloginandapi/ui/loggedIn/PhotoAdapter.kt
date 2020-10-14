package com.zikki.basicloginandapi.ui.loggedIn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.zikki.basicloginandapi.R
import com.zikki.basicloginandapi.model.Photo
import kotlinx.android.synthetic.main.list_item_for_adapter.view.*

/**
 * Adapter to contain list of items retrieved from API call
 */
class PhotoAdapter : ListAdapter<Photo, PhotoAdapter.ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_for_adapter, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(photo: Photo) = with(itemView) {
            val tv: TextView = itemView.findViewById(R.id.photo_tv)
            tv.text = photo.title
            val iv: ImageView = itemView.photo_iv
            iv.load(photo.thumbnailUrl) {
                placeholder(R.drawable.ic_launcher_foreground)
                error(R.drawable.ic_launcher_background)
                transformations(RoundedCornersTransformation(12f, 12f, 12f, 12f))
            }
            setOnClickListener {
                // TODO: Handle on click
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }
    }
}