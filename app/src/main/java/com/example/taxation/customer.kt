package com.example.taxation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_customer.*

class customer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer)

        val mDatabase = FirebaseDatabase.getInstance().getReference("Property Assessment")

        book.setOnClickListener {
            val intent = Intent(this, Book::class.java)
            startActivity(intent)
        }

        back_customer.setOnClickListener { finish() }

        Picasso.get().load(R.drawable.logo).into(imgCustomer)
        val intent = Intent(this, Display::class.java)


        btnCtmSearch.setOnClickListener {

            if (ctm_search.text.toString() != ""){
                val property = mDatabase.child(ctm_search.text.toString())
                val postListener = object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        // Get Post object and use the values to update the UI

                        if(dataSnapshot.exists()){
                            intent.putExtra("Arp number",ctm_search.text.toString())
                            intent.putExtra("status","")
                            startActivity(intent)

                        }else{
                            result.visibility = View.VISIBLE
                        }


                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        // Getting Post failed, log a message
                        Log.w("DISPLAY", "loadPost:onCancelled", databaseError.toException())
                        // ...
                    }
                }
                property.addValueEventListener(postListener)
            }else{
                ctm_search.setError("ARP Number is Required")
            }


        }
    }
}