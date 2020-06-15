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

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class Application4 : AppCompatActivity() {

    private lateinit var mDatabase: DatabaseReference
    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application4)

        mDatabase = FirebaseDatabase.getInstance().getReference("Users")

        val structures = intent.getStringExtra("structures")
        value_two.setText(structures)
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
            val arp: String = intent.getStringExtra("id_number")
            mDatabase = FirebaseDatabase.getInstance().getReference("Customer").child(arp)
            mDatabase.removeValue()
            mDatabase = FirebaseDatabase.getInstance().getReference("Property Assessment").child(arp)
            mDatabase.removeValue()
            mDatabase = FirebaseDatabase.getInstance().getReference("Structure Characteristics").child(arp)
            mDatabase.removeValue()
            mDatabase = FirebaseDatabase.getInstance().getReference("Value Computation").child(arp)
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
        val structures = intent.getStringExtra("structures")
        val id_number = intent.getStringExtra("id_number")
        //end of data
        mDatabase = FirebaseDatabase.getInstance().getReference("Value Computation")
        val currentUserDb = mDatabase.child(id_number)

        currentUserDb.child("Market Value").child("Land")
            .setValue(market_one.text.toString())
        currentUserDb.child("Market Value").child("Building")
            .setValue(market_two.text.toString())
        currentUserDb.child("Assessed Value").child("Land")
            .setValue(value_one.text.toString())
        currentUserDb.child("Assessed Value").child("Building")
            .setValue(structures)

        var assess_one = value_one.text.toString().toInt().toFloat()
        var assess_two = structures.toInt().toFloat()

        assess_one = when(assess_one) {
            in 0..175000 -> 0.toFloat()
            in 175001..300000 -> 0.10.toFloat()
            in 300001..500000 -> 0.20.toFloat()
            in 500001..750000 -> 0.25.toFloat()
            in 750001..1000000 -> 0.30.toFloat()
            in 1000001..2000000 -> 0.35.toFloat()
            in 2000001..5000000 -> 0.40.toFloat()
            in 5000000..10000000 -> 0.50.toFloat()
            else -> 0.60.toFloat()
        }
        assess_two = when(assess_two) {
            in 0..175000 -> 0.toFloat()
            in 175001..300000 -> 0.10.toFloat()
            in 300001..500000 -> 0.20.toFloat()
            in 500001..750000 -> 0.25.toFloat()
            in 750001..1000000 -> 0.30.toFloat()
            in 1000001..2000000 -> 0.35.toFloat()
            in 2000001..5000000 -> 0.40.toFloat()
            in 5000000..10000000 -> 0.50.toFloat()
            else -> 0.60.toFloat()
        }
        val land = value_one.text.toString().toInt().toFloat() * assess_one
        val building = structures.toInt().toFloat() * assess_two
        val tax = (land+building)*0.02.toFloat()

        mDatabase = FirebaseDatabase.getInstance().getReference("Property Assessment").child(id_number)
        mDatabase.child("tax_payable").setValue(tax)


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
