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
import kotlinx.android.synthetic.main.activity_application2.*

class Application2 : AppCompatActivity() {

    private lateinit var mDatabase: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application2)

        btn_application2.setOnClickListener {
            submitForm()
        }
        mDatabase = FirebaseDatabase.getInstance().getReference("Users")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val data = dataSnapshot.getValue(User::class.java)
                // ...
                txt_assessor2.text = data?.firstName
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        mDatabase.addValueEventListener(postListener)
        // [END post_value_event_listener]
        btn_logout2.setOnClickListener {
            singOut()
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

        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Foundation")
            .child("First Floor").setValue(foundation1.text.toString())
        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Foundation")
            .child("Second Floor").setValue(foundation2.text.toString())
        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Columns")
            .child("First Floor").setValue(column1.text.toString())
        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Columns")
            .child("Second Floor").setValue(column2.text.toString())
        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Beans")
            .child("First Floor").setValue(bean1.text.toString())
        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Beans")
            .child("Second Floor").setValue(bean2.text.toString())
        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Truss Farming")
            .child("First Floor").setValue(trus1.text.toString())
        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Truss Farming")
            .child("Second Floor").setValue(trus2.text.toString())
        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Roof")
            .child("First Floor").setValue(roof1.text.toString())
        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Roof")
            .child("Second Floor").setValue(roof2.text.toString())
        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Exterior Walls")
            .child("First Floor").setValue(exterior1.text.toString())
        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Exterior Walls")
            .child("Second Floor").setValue(exterior2.text.toString())
        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Flooring")
            .child("First Floor").setValue(flooring1.text.toString())
        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Flooring")
            .child("Second Floor").setValue(flooring2.text.toString())
        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Doors")
            .child("First Floor").setValue(door1.text.toString())
        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Doors")
            .child("Second Floor").setValue(door2.text.toString())
        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Ceiling")
            .child("First Floor").setValue(ceiling1.text.toString())
        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Ceiling")
            .child("Second Floor").setValue(ceiling2.text.toString())
        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Windows")
            .child("First Floor").setValue(window1.text.toString())
        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Windows")
            .child("Second Floor").setValue(window2.text.toString())
        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Stairs")
            .child("First Floor").setValue(stairs1.text.toString())
        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Stairs")
            .child("Second Floor").setValue(stairs2.text.toString())
        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Partition")
            .child("First Floor").setValue(partition1.text.toString())
        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Partition")
            .child("Second Floor").setValue(partition2.text.toString())
        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Wall Finish")
            .child("First Floor").setValue(wallfinish1.text.toString())
        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Wall Finish")
            .child("Second Floor").setValue(wallfinish2.text.toString())
        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Electrical")
            .child("First Floor").setValue(electrical1.text.toString())
        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Electrical")
            .child("Second Floor").setValue(electrical2.text.toString())
        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Toilet & Bath")
            .child("First Floor").setValue(toiletbath1.text.toString())
        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Toilet & Bath")
            .child("Second Floor").setValue(toiletbath2.text.toString())
        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Plumbing Sewer")
            .child("First Floor").setValue(plumbingsewer1.text.toString())
        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Plumbing Sewer")
            .child("Second Floor").setValue(plumbingsewer2.text.toString())
        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Fixtures")
            .child("First Floor").setValue(fixtures1.text.toString())
        currentUserDb.child("Property Assessment").child("Stucture Characteristics").child("Fixtures")
            .child("Second Floor").setValue(fixtures2.text.toString())

        val intent = Intent(this, Application3::class.java)
        intent.putExtra("id_number", id_number)
        startActivity(intent)
        finish()
    }

    private fun validateForm(): Boolean {
        var valid = true

        val Foundation1 = foundation1.text.toString()
        if (TextUtils.isEmpty(Foundation1)) {
            foundation1.error = "Required."
            valid = false
        } else {
            foundation1.error = null
        }
        val Foundation2 = foundation2.text.toString()
        if (TextUtils.isEmpty(Foundation2)) {
            foundation2.error = "Required."
            valid = false
        } else {
            foundation2.error = null
        }
        val Column1 = column1.text.toString()
        if (TextUtils.isEmpty(Column1)) {
            column1.error = "Required."
            valid = false
        } else {
            column1.error = null
        }
        val Column2 = column2.text.toString()
        if (TextUtils.isEmpty(Column2)) {
            column2.error = "Required."
            valid = false
        } else {
            column2.error = null
        }
        val Bean1 = bean1.text.toString()
        if (TextUtils.isEmpty(Bean1)) {
            bean1.error = "Required."
            valid = false
        } else {
            bean1.error = null
        }
        val Bean2 = bean2.text.toString()
        if (TextUtils.isEmpty(Bean2)) {
            bean2.error = "Required."
            valid = false
        } else {
            bean2.error = null
        }
        val Trus1 = trus1.text.toString()
        if (TextUtils.isEmpty(Trus1)) {
            trus1.error = "Required."
            valid = false
        } else {
            trus1.error = null
        }
        val Trus2 = trus2.text.toString()
        if (TextUtils.isEmpty(Trus2)) {
            trus2.error = "Required."
            valid = false
        } else {
            trus2.error = null
        }
        val Roof1 = roof1.text.toString()
        if (TextUtils.isEmpty(Roof1)) {
            roof1.error = "Required."
            valid = false
        } else {
            roof1.error = null
        }
        val Roof2 = roof2.text.toString()
        if (TextUtils.isEmpty(Roof2)) {
            roof2.error = "Required."
            valid = false
        } else {
            roof2.error = null
        }
        val Exterior1 = exterior1.text.toString()
        if (TextUtils.isEmpty(Exterior1)) {
            exterior1.error = "Required."
            valid = false
        } else {
            exterior1.error = null
        }
        val Exterior2 = exterior2.text.toString()
        if (TextUtils.isEmpty(Exterior2)) {
            exterior2.error = "Required."
            valid = false
        } else {
            exterior2.error = null
        }
        val Flooring1 = flooring1.text.toString()
        if (TextUtils.isEmpty(Flooring1)) {
            flooring1.error = "Required."
            valid = false
        } else {
            flooring1.error = null
        }
        val Flooring2 = flooring2.text.toString()
        if (TextUtils.isEmpty(Flooring2)) {
            flooring2.error = "Required."
            valid = false
        } else {
            flooring2.error = null
        }
        val Door1 = door1.text.toString()
        if (TextUtils.isEmpty(Door1)) {
            door1.error = "Required."
            valid = false
        } else {
            door1.error = null
        }
        val Door2 = door2.text.toString()
        if (TextUtils.isEmpty(Door2)) {
            door2.error = "Required."
            valid = false
        } else {
            door2.error = null
        }
        val Ceiling1 = ceiling1.text.toString()
        if (TextUtils.isEmpty(Ceiling1)) {
            ceiling1.error = "Required."
            valid = false
        } else {
            ceiling1.error = null
        }
        val Ceiling2 = ceiling2.text.toString()
        if (TextUtils.isEmpty(Ceiling2)) {
            ceiling2.error = "Required."
            valid = false
        } else {
            ceiling2.error = null
        }
        val Window1 = window1.text.toString()
        if (TextUtils.isEmpty(Window1)) {
            window1.error = "Required."
            valid = false
        } else {
            window1.error = null
        }
        val Window2 = window2.text.toString()
        if (TextUtils.isEmpty(Window2)) {
            window2.error = "Required."
            valid = false
        } else {
            window2.error = null
        }
        val Stairs1 = stairs1.text.toString()
        if (TextUtils.isEmpty(Stairs1)) {
            stairs1.error = "Required."
            valid = false
        } else {
            stairs1.error = null
        }
        val Stairs2 = stairs2.text.toString()
        if (TextUtils.isEmpty(Stairs2)) {
            stairs2.error = "Required."
            valid = false
        } else {
            stairs2.error = null
        }
        val Partition1 = partition1.text.toString()
        if (TextUtils.isEmpty(Partition1)) {
            partition1.error = "Required."
            valid = false
        } else {
            partition1.error = null
        }
        val Partition2 = partition2.text.toString()
        if (TextUtils.isEmpty(Partition2)) {
            partition2.error = "Required."
            valid = false
        } else {
            partition2.error = null
        }
        val Wallfinish1 = wallfinish1.text.toString()
        if (TextUtils.isEmpty(Wallfinish1)) {
            wallfinish1.error = "Required."
            valid = false
        } else {
            wallfinish1.error = null
        }
        val Wallfinish2 = wallfinish2.text.toString()
        if (TextUtils.isEmpty(Wallfinish2)) {
            wallfinish2.error = "Required."
            valid = false
        } else {
            wallfinish2.error = null
        }
        val Electrical1 = electrical1.text.toString()
        if (TextUtils.isEmpty(Electrical1)) {
            electrical1.error = "Required."
            valid = false
        } else {
            electrical1.error = null
        }
        val Electrical2 = electrical2.text.toString()
        if (TextUtils.isEmpty(Electrical2)) {
            electrical2.error = "Required."
            valid = false
        } else {
            electrical2.error = null
        }
        val Toiletbath1 = toiletbath1.text.toString()
        if (TextUtils.isEmpty(Toiletbath1)) {
            toiletbath1.error = "Required."
            valid = false
        } else {
            toiletbath1.error = null
        }
        val Toiletbath2 = toiletbath2.text.toString()
        if (TextUtils.isEmpty(Toiletbath2)) {
            toiletbath2.error = "Required."
            valid = false
        } else {
            toiletbath2.error = null
        }
        val Plumbingsewer1 = plumbingsewer1.text.toString()
        if (TextUtils.isEmpty(Plumbingsewer1)) {
            plumbingsewer1.error = "Required."
            valid = false
        } else {
            plumbingsewer1.error = null
        }
        val Plumbingsewer2 = plumbingsewer2.text.toString()
        if (TextUtils.isEmpty(Plumbingsewer2)) {
            plumbingsewer2.error = "Required."
            valid = false
        } else {
            plumbingsewer2.error = null
        }
        val Fixtures1 = fixtures1.text.toString()
        if (TextUtils.isEmpty(Fixtures1)) {
            fixtures1.error = "Required."
            valid = false
        } else {
            fixtures1.error = null
        }
        val Fixtures2 = fixtures2.text.toString()
        if (TextUtils.isEmpty(Fixtures2)) {
            fixtures2.error = "Required."
            valid = false
        } else {
            fixtures2.error = null
        }

        return valid
    }
    private fun singOut(){
        auth.signOut()
        val arp = intent.getStringExtra("id_number")
        mDatabase = FirebaseDatabase.getInstance().getReference("Pending").child(arp)
        mDatabase.removeValue()
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
