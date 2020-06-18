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
import kotlinx.android.synthetic.main.activity_application.*
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
        mDatabase = FirebaseDatabase.getInstance().getReference("Structure Characteristics")
        val currentUserDb = mDatabase.child(id_number)

        currentUserDb.child("Foundation")
            .child("first_Floor").setValue(foundation1.text.toString())
        currentUserDb.child("Foundation")
            .child("secondFloor").setValue(foundation2.text.toString())
        currentUserDb.child("Columns")
            .child("first_Floor").setValue(column1.text.toString())
        currentUserDb.child("Columns")
            .child("secondFloor").setValue(column2.text.toString())
        currentUserDb.child("Beans")
            .child("first_Floor").setValue(bean1.text.toString())
        currentUserDb.child("Beans")
            .child("secondFloor").setValue(bean2.text.toString())
        currentUserDb.child("Truss Farming")
            .child("first_Floor").setValue(trus1.text.toString())
        currentUserDb.child("Truss Farming")
            .child("secondFloor").setValue(trus2.text.toString())
        currentUserDb.child("Roof")
            .child("first_Floor").setValue(roof1.text.toString())
        currentUserDb.child("Roof")
            .child("secondFloor").setValue(roof2.text.toString())
        currentUserDb.child("Exterior Walls")
            .child("first_Floor").setValue(exterior1.text.toString())
        currentUserDb.child("Exterior Walls")
            .child("secondFloor").setValue(exterior2.text.toString())
        currentUserDb.child("Flooring")
            .child("first_Floor").setValue(flooring1.text.toString())
        currentUserDb.child("Flooring")
            .child("secondFloor").setValue(flooring2.text.toString())
        currentUserDb.child("Doors")
            .child("first_Floor").setValue(door1.text.toString())
        currentUserDb.child("Doors")
            .child("secondFloor").setValue(door2.text.toString())
        currentUserDb.child("Ceiling")
            .child("first_Floor").setValue(ceiling1.text.toString())
        currentUserDb.child("Ceiling")
            .child("secondFloor").setValue(ceiling2.text.toString())
        currentUserDb.child("Windows")
            .child("first_Floor").setValue(window1.text.toString())
        currentUserDb.child("Windows")
            .child("secondFloor").setValue(window2.text.toString())
        currentUserDb.child("Stairs")
            .child("first_Floor").setValue(stairs1.text.toString())
        currentUserDb.child("Stairs")
            .child("secondFloor").setValue(stairs2.text.toString())
        currentUserDb.child("Partition")
            .child("first_Floor").setValue(partition1.text.toString())
        currentUserDb.child("Partition")
            .child("secondFloor").setValue(partition2.text.toString())
        currentUserDb.child("Wall Finish")
            .child("first_Floor").setValue(wallfinish1.text.toString())
        currentUserDb.child("Wall Finish")
            .child("secondFloor").setValue(wallfinish2.text.toString())
        currentUserDb.child("Electrical")
            .child("first_Floor").setValue(electrical1.text.toString())
        currentUserDb.child("Electrical")
            .child("secondFloor").setValue(electrical2.text.toString())
        currentUserDb.child("Toilet & Bath")
            .child("first_Floor").setValue(toiletbath1.text.toString())
        currentUserDb.child("Toilet & Bath")
            .child("secondFloor").setValue(toiletbath2.text.toString())
        currentUserDb.child("Plumbing Sewer")
            .child("first_Floor").setValue(plumbingsewer1.text.toString())
        currentUserDb.child("Plumbing Sewer")
            .child("secondFloor").setValue(plumbingsewer2.text.toString())
        currentUserDb.child("Fixtures")
            .child("first_Floor").setValue(fixtures1.text.toString())
        currentUserDb.child("Fixtures")
            .child("secondFloor").setValue(fixtures2.text.toString())

        val total = foundation1.text.toString().toLong() + foundation2.text.toString().toLong() +
                column1.text.toString().toLong() + column2.text.toString().toLong() + bean1.text.toString().toLong() +
                bean2.text.toString().toLong() + trus1.text.toString().toLong() + trus2.text.toString().toLong() +
                roof1.text.toString().toLong() + roof2.text.toString().toLong() + exterior1.text.toString().toLong() +
                exterior2.text.toString().toLong() + flooring1.text.toString().toLong() + flooring2.text.toString().toLong() +
                door1.text.toString().toLong() + door2.text.toString().toLong() + ceiling1.text.toString().toLong() +
                ceiling2.text.toString().toLong() + window1.text.toString().toLong() + window2.text.toString().toLong() +
                stairs1.text.toString().toLong() + stairs2.text.toString().toLong() + partition1.text.toString().toLong() +
                partition2.text.toString().toLong() + wallfinish1.text.toString().toLong() + wallfinish2.text.toString().toLong() +
                electrical1.text.toString().toLong() + electrical2.text.toString().toLong() + toiletbath1.text.toString().toLong() +
                toiletbath2.text.toString().toLong() + plumbingsewer1.text.toString().toLong() + plumbingsewer2.text.toString().toLong() +
                fixtures1.text.toString().toLong() + fixtures2.text.toString().toLong()

        val intent = Intent(this, Application3::class.java)
        intent.putExtra("id_number", id_number)
        intent.putExtra("structures", "$total")
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
        mDatabase = FirebaseDatabase.getInstance().getReference("Property Assessment").child(arp)
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
