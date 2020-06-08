package com.example.taxation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.ChangeEventListener
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import com.sti.taxation.models.Model
import kotlinx.android.synthetic.main.activity_pending.*
import kotlinx.android.synthetic.main.pending_layout.view.*

class Pending : AppCompatActivity() {

    lateinit var mDatabase: DatabaseReference

    private var mAdapter: FirebaseRecyclerAdapter<Model, PendingViewHolder>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pending)

        mDatabase = FirebaseDatabase.getInstance().getReference("Pending")

        Pending.setHasFixedSize(true)
        Pending.layoutManager = LinearLayoutManager(this)

        val query = mDatabase.limitToLast(8)

        mAdapter = object : FirebaseRecyclerAdapter<Model, PendingViewHolder>(
            Model::class.java, R.layout.pending_layout, PendingViewHolder::class.java, query
        ){
            override fun populateViewHolder(p0: PendingViewHolder?, p1: Model?, p2: Int) {
                TODO("Not yet implemented")
                p0?.bindPending(p1!!)
            }

            override fun onChildChanged(
                type: ChangeEventListener.EventType?,
                snapshot: DataSnapshot?,
                index: Int,
                oldIndex: Int
            ) {
                super.onChildChanged(type, snapshot, index, oldIndex)

                Pending.scrollToPosition(index)
            }
        }
        Pending.adapter = mAdapter
    }

}
class PendingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bindPending(model: Model){
        with(model){
            itemView.txt_owner.text = Owner
            itemView.tax_pending.text = tax_payable
            Picasso.get().load(model.image).into(itemView.image_pending)
        }
    }

}
