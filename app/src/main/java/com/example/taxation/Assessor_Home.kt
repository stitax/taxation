package com.example.taxation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taxation.models.Appointment
import com.example.taxation.models.Appointment_ViewHolder
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_assessor__home.*
import kotlinx.android.synthetic.main.schedule_row.view.*

class Assessor_Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assessor__home)
        val filters = resources.getStringArray(R.array.assess)
        listSchedule.setHasFixedSize(true)
        listSchedule.layoutManager = LinearLayoutManager(this)

        back.setOnClickListener {
            finish()
        }

        display("Monday")

        val spinner = findViewById<Spinner>(R.id.spinner_assessor)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, filters)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    when (position) {
                        0 -> {
                            // Notify the selected item text
                            display("Monday")
                        }
                        1 -> {
                            // Notify the selected item text
                            display("Tuesday")
                        }
                        2 -> {
                            display("Wednesday")
                        }
                        3 -> {
                            display("Thursday")
                        }
                        4 -> {
                            display("Friday")
                        }
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

    }

    fun display(day: String){

        val mDatabase = FirebaseDatabase.getInstance().getReference("Appointment").child(day)

        val query = mDatabase!!.limitToLast(30)
        val options = FirebaseRecyclerOptions.Builder<Appointment>()
            .setQuery(query, Appointment::class.java)
            .setLifecycleOwner(this)
            .build()

        val mAdapter = object : FirebaseRecyclerAdapter<Appointment, Appointment_ViewHolder>(options) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Appointment_ViewHolder {
                return Appointment_ViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.schedule_row, parent, false))

            }

            override fun onBindViewHolder(holder: Appointment_ViewHolder, position: Int, model: Appointment) {

                holder.bind(model)
                holder.itemView.row_delete.setOnClickListener{
                    mDatabase.child(holder.itemView.row_id.text.toString()).removeValue()
                }
                holder.itemView.assess.setOnClickListener {
                    val intent = Intent(holder.itemView.context, Application::class.java)
                    intent.putExtra("AppointmentID", holder.itemView.row_id.text.toString())
                    intent.putExtra("day",day)
                    startActivity(intent)
                }

            }

            override fun onDataChanged() {
                // If there are no chat messages, show a view that invites the user to add a message.
            }

        }

        listSchedule.adapter = mAdapter
    }
}