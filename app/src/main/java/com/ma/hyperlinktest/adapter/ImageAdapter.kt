package com.ma.hyperlinktest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ma.hyperlinktest.R
import kotlinx.android.synthetic.main.lay_image.view.*

class ImageAdapter(val context:Context) : RecyclerView.Adapter<ImageAdapter.VHolder>()
{
    private var alList=ArrayList<String>()
    class VHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    fun addData(list:ArrayList<String>)
    {
        alList=list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        val v=LayoutInflater.from(context).inflate(R.layout.lay_image,parent,false)
        return VHolder(v)
    }

    override fun getItemCount(): Int {
        return alList.size
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        val image=alList.get(position)
        Glide.with(context).load(image).into(holder.itemView.ivPicture)
       // holder.itemView.ivPicture.setImageResource()
    }

}