package com.example.taxation.models

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.schedule_row.view.*

class Appointment_ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(model: Appointment?){

        itemView.row_address.text = model?.Address
        itemView.row_id.text = model?.Arp_Number
        itemView.row_name.text = model?.Name
        itemView.row_schedule.text = model?.Schedule

    }
}