package com.example.taxation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.sti.taxation.models.User
import kotlinx.android.synthetic.main.activity_transaction_id.*

class TransactionId : AppCompatActivity() {
    private lateinit var mDatabase: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_id)
        auth = FirebaseAuth.getInstance()
        val intent = intent
        txt_transID.setText(intent.getStringExtra("transID"))
        tax.setText("Tax Payable : "+intent.getStringExtra("tax")+ " Pesos")
        mDatabase = FirebaseDatabase.getInstance().getReference("Users")

        delete.setOnClickListener {
            val arp: String = intent.getStringExtra("id_number")
            mDatabase = FirebaseDatabase.getInstance().getReference("Property Assessment").child(arp)
            mDatabase.removeValue()
            mDatabase = FirebaseDatabase.getInstance().getReference("Structure Characteristics").child(arp)
            mDatabase.removeValue()
            mDatabase = FirebaseDatabase.getInstance().getReference("Value Computation").child(arp)
            mDatabase.removeValue()
            mDatabase = FirebaseDatabase.getInstance().getReference("Property Information").child(arp)
            mDatabase.removeValue()
            val intent = Intent(this, Application::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
        done.setOnClickListener {
            val intent = Intent(this, Assessor_Home::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val data = dataSnapshot.getValue(User::class.java)
                // ...
                txt_assessor.text = data?.firstName
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        mDatabase.addValueEventListener(postListener)
        btn_logout.setOnClickListener {
            singOut()
        }
    }

    private fun singOut(){
        auth = FirebaseAuth.getInstance()
        auth.signOut()
        updateUI(null)
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {

        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
