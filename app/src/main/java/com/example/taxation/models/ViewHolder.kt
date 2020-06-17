package com.example.taxation.models

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.sti.taxation.models.Model
import kotlinx.android.synthetic.main.pending_layout.view.*
import android.content.Intent

class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    fun bind(model: Model?){

        itemView.txt_owner.text = model?.owner
        itemView.tax_pending.text = model?.status
        itemView.Arp.text = model?.arpNumber
        Picasso.get().load(model?.image).fit().into(itemView.image_pending)

    }
}