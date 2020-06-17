package com.example.taxation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taxation.models.ViewHolder
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.sti.taxation.models.Model
import kotlinx.android.synthetic.main.activity_admin_home.*
import kotlinx.android.synthetic.main.pending_layout.view.*


class admin_home : AppCompatActivity() {

    private var mDatabase: DatabaseReference? = null
    private var mAdapter: FirebaseRecyclerAdapter<Model, ViewHolder>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_home)
        listData.setHasFixedSize(true)
        listData.layoutManager = LinearLayoutManager(this)
        mDatabase = FirebaseDatabase.getInstance().getReference("Property Assessment")

        back.setOnClickListener { finish() }
        val filters = resources.getStringArray(R.array.filters)
        filter_search.setOnQueryTextListener(object :  SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                if (newText != ""){
                    firebaseSearch(newText,"arpNumber")
                }else{
                    recycler()
                }
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                // task HERE
                //on submit send entire query
                return false
            }
        })


        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, filters)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    when (position) {
                        1 -> {
                            // Notify the selected item text
                            firebaseSearch("Approve","status")
                        }
                        2 -> {
                            firebaseSearch("Pending","status")
                        }
                        else -> {
                            recycler()
                        }
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }


    }

    fun recycler(){

        mDatabase = FirebaseDatabase.getInstance().getReference("Property Assessment")

        val query = mDatabase!!.limitToLast(30)
        val options = FirebaseRecyclerOptions.Builder<Model>()
            .setQuery(query,Model::class.java)
            .setLifecycleOwner(this)
            .build()

        mAdapter = object : FirebaseRecyclerAdapter<Model, ViewHolder>(options) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                return ViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.pending_layout, parent, false))

            }

            override fun onBindViewHolder(holder: ViewHolder, position: Int, model: Model) {

                holder.bind(model)
                holder.itemView.setOnClickListener{

                    val intent = Intent(holder.itemView.context, Display::class.java)
                    intent.putExtra("Arp number", it.Arp.text)
                    intent.putExtra("status", it.tax_pending.text)
                    startActivity(intent)
                }

            }

            override fun onDataChanged() {
                // If there are no chat messages, show a view that invites the user to add a message.
            }

        }

        listData.adapter = mAdapter
    }

    fun firebaseSearch(text: String,value: String){
        val query = FirebaseDatabase.getInstance().getReference("Property Assessment").orderByChild(value).startAt(text).endAt(text+"\uf8ff").limitToLast(30)
        val options = FirebaseRecyclerOptions.Builder<Model>()
            .setQuery(query,Model::class.java)
            .setLifecycleOwner(this)
            .build()

        mAdapter = object : FirebaseRecyclerAdapter<Model, ViewHolder>(options) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                return ViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.pending_layout, parent, false))

            }

            override fun onBindViewHolder(holder: ViewHolder, position: Int, model: Model) {

                holder.bind(model)
                holder.itemView.setOnClickListener{

                    val intent = Intent(holder.itemView.context, Display::class.java)
                    intent.putExtra("Arp number", it.Arp.text)
                    intent.putExtra("status", it.tax_pending.text)
                    startActivity(intent)
                }

            }

            override fun onDataChanged() {
                // If there are no chat messages, show a view that invites the user to add a message.
            }

        }

        listData.adapter = mAdapter
    }

}
