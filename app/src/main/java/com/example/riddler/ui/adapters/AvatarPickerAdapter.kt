package com.example.riddler.ui.adapters

import android.content.Context
import android.content.res.TypedArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.riddler.R

class AvatarPickerAdapter(val avatars : List<Int>, val setItem : (Int) -> Unit)
    : RecyclerView.Adapter<AvatarViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvatarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.avatarpicker_item, parent, false)
        return AvatarViewHolder(view)
    }

    override fun onBindViewHolder(holder: AvatarViewHolder, position: Int) {
        holder.image.setImageResource(avatars.get(position))
        holder.image.setOnClickListener {
            setItem(position)
        }
    }

    override fun getItemCount(): Int {
        return avatars.size
    }
}
class AvatarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    lateinit var image: ImageView
    init {
        image = itemView.findViewById(R.id.avPicker_avatarImageView)
    }

}