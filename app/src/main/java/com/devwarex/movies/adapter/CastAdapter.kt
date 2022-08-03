package com.devwarex.movies.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devwarex.movies.R
import com.devwarex.movies.model.Cast
import com.devwarex.movies.util.EndPoint

class CastAdapter : RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    private var casts: List<Cast> = listOf()

    inner class CastViewHolder(
        item: View
    ): RecyclerView.ViewHolder(item){
        private val glide = Glide.with(item.context)
        private val name = item.findViewById<TextView>(R.id.cast_profile_name)
        private val image = item.findViewById<ImageView>(R.id.cast_profile_iv)
        fun onBind(cast: Cast){
            name.text = cast.name
            val url = EndPoint.IMAGE_BASE_URL + cast.profile_path
            glide.load(url)
                .placeholder(if (cast.gender == 2) R.drawable.male else R.drawable.fe)
                .error(if (cast.gender == 2) R.drawable.male else R.drawable.fe)
                .into(image)
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setCasts(casts: List<Cast>){
        this.casts = casts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder = CastViewHolder(
        item = LayoutInflater.from(parent.context).inflate(R.layout.cast_item,parent,false)
    )

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.onBind(casts[position])
    }

    override fun getItemCount(): Int = casts.size
}