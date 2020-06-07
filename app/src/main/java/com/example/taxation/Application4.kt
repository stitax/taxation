package com.example.taxation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.sti.taxation.models.User
import kotlinx.android.synthetic.main.activity_application4.*

class Application4 : AppCompatActivity() {

    private lateinit var mDatabase: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application4)

        mDatabase = FirebaseDatabase.getInstance().getReference("Users")



        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val data = dataSnapshot.getValue(User::class.java)
                // ...
                txt_assessor4.text = data?.firstName
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        mDatabase.addValueEventListener(postListener)

        btn_logout4.setOnClickListener {
            val arp = intent.getStringExtra("id_number")
            mDatabase = FirebaseDatabase.getInstance().getReference("Pending").child(arp)
            mDatabase.removeValue()
            singOut()
        }
        btn_submitFinal.setOnClickListener {
            submitForm()
        }
    }


    private fun submitForm() {
        if (!validateForm()) {
            return
        }
        //Data from previous form
        val id_number = intent.getStringExtra("id_number")
        //end of data
        mDatabase = FirebaseDatabase.getInstance().getReference("Pending")
        val currentUserDb = mDatabase.child(id_number)

        currentUserDb.child("Property Assessment").child("Value Computation").child("Market Value").child("Land")
            .setValue(market_one.text.toString())
        currentUserDb.child("Property Assessment").child("Value Computation").child("Market Value").child("Building")
            .setValue(market_two.text.toString())
        currentUserDb.child("Property Assessment").child("Value Computation").child("Assessed Value").child("Land")
            .setValue(value_one.text.toString())
        currentUserDb.child("Property Assessment").child("Value Computation").child("Assessed Value").child("Building")
            .setValue(value_two.text.toString())

        var assess_one = value_one.text.toString().toInt()
        var assess_two = value_two.text.toString().toInt()

        when(assess_one) {
            in 0..17500 -> assess_one = 0
            in 17501..300000 -> assess_one = 10/100
            in 300001..500000 -> assess_one = 20/100
            in 500001..750000 -> assess_one = 25/100
            in 750001..1000000 -> assess_one = 30/100
            in 1000001..2000000 -> assess_one = 35/100
            in 2000001..5000000 -> assess_one = 40/100
            in 5000000..10000000 -> assess_one = 50/100
            else -> assess_one = 60/100
        }
        when(assess_two) {
            in 0..17500 -> assess_two = 0
            in 17501..300000 -> assess_two = 10/100
            in 300001..500000 -> assess_two = 20/100
            in 500001..750000 -> assess_two = 25/100
            in 750001..1000000 -> assess_two = 30/100
            in 1000001..2000000 -> assess_two = 35/100
            in 2000001..5000000 -> assess_two = 40/100
            in 5000000..10000000 -> assess_two = 50/100
            else -> assess_two = 60/100
        }
        val land = value_one.text.toString().toInt() * assess_one
        val building = value_two.text.toString().toInt() * assess_two
        val tax = (land+building)*0.02
        currentUserDb.child("Property Assessment").child("Value Computation").child("tax_payable").setValue(tax)


        val intent = Intent(this, TransactionId::class.java)
        intent.putExtra("transID", currentUserDb.key.toString())
        intent.putExtra("tax", "$tax")
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

    private fun validateForm(): Boolean {
        var valid = true


        val marketOne = market_one.text.toString()
        if (TextUtils.isEmpty(marketOne)) {
            market_one.error = "Required."
            valid = false
        } else {
            market_one.error = null
        }

        val marketTwo = market_two.text.toString()
        if (TextUtils.isEmpty(marketTwo)) {
            market_two.error = "Required."
            valid = false
        } else {
            market_two.error = null
        }

        val valueOne = value_one.text.toString()
        if (TextUtils.isEmpty(valueOne)) {
            value_one.error = "Required."
            valid = false
        } else {
            value_one.error = null
        }
        val valueTwo = value_two.text.toString()
        if (TextUtils.isEmpty(valueTwo)) {
            value_two.error = "Required."
            valid = false
        } else {
            value_two.error = null
        }

        return valid
    }
    private fun singOut(){
        auth.signOut()
        updateUI(null)
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user == null) {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }


}
