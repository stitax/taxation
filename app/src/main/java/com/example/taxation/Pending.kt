package com.example.taxation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taxation.models.PendingViewHolder
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import com.sti.taxation.models.Model
import kotlinx.android.synthetic.main.activity_pending.*
import kotlinx.android.synthetic.main.pending_layout.view.*


class Pending : AppCompatActivity() {

    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pending)

        Pending.setHasFixedSize(true)
        Pending.layoutManager = LinearLayoutManager(this)

        val query = FirebaseDatabase.getInstance()
            .reference
            .child("Property Assessment")
            .limitToLast(8)

        val options: FirebaseRecyclerOptions<Model> = FirebaseRecyclerOptions.Builder<Model>()
            .setQuery(query, Model::class.java)
            .build()

        val adapter: FirebaseRecyclerAdapter<Model, PendingViewHolder> =
            object : FirebaseRecyclerAdapter<Model, PendingViewHolder>(options) {
                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingViewHolder {
                    // Create a new instance of the ViewHolder, in this case we are using a custom
                    // layout called R.layout.message for each item
                    val view: View = LayoutInflater.from(parent.context)
                        .inflate(R.layout.pending_layout, parent, false)
                    return PendingViewHolder(view)
                }

                override fun onBindViewHolder(holder: PendingViewHolder, position: Int, model: Model) {
                    holder.bindPending(model)
                }
            }
        Pending.adapter = adapter
    }


}
