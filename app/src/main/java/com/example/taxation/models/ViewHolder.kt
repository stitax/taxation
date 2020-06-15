package com.example.taxation.models

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.sti.taxation.models.Model
import kotlinx.android.synthetic.main.pending_layout.view.*

class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    fun bind(model: Model?){

        itemView.txt_owner.text = model?.owner
        itemView.tax_pending.text = model?.tax_payable.toString()
        Picasso.get().load(model?.image).into(itemView.image_pending)

    }
}