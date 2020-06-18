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
import kotlinx.android.synthetic.main.activity_application3.*

class Application3 : AppCompatActivity() {

    private lateinit var mDatabase: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application3)
        auth = FirebaseAuth.getInstance()

        btn_application3.setOnClickListener {
            submitForm()
        }
        mDatabase = FirebaseDatabase.getInstance().getReference("Users")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val data = dataSnapshot.getValue(User::class.java)
                // ...
                txt_assessor3.text = data?.firstName
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        mDatabase.addValueEventListener(postListener)


        btn_logout3.setOnClickListener {
            val arp = intent.getStringExtra("id_number")
            mDatabase = FirebaseDatabase.getInstance().getReference("Property Assessment").child(arp)
            mDatabase.removeValue()
            mDatabase = FirebaseDatabase.getInstance().getReference("Structure Characteristics").child(arp)
            mDatabase.removeValue()
            singOut()
        }
    }

    private fun submitForm() {
        if (!validateForm()) {
            return
        }
        //Data from previous form
        val id_number = intent.getStringExtra("id_number")
        val structures = intent.getStringExtra("structures")
        //end of data
        mDatabase = FirebaseDatabase.getInstance().getReference("Property Information")
        val currentUserDb = mDatabase.child(id_number)

        currentUserDb.child("Structural_Type").setValue(structuretype.text.toString())
        currentUserDb.child("Bldg_Classification").setValue(buildingclasification.text.toString())
        currentUserDb.child("Bldg_Permit").setValue(buildingpermit.text.toString())
        currentUserDb.child("Bldg_Age").setValue(buildingage.text.toString())
        currentUserDb.child("NoOfStoreys").setValue(numberofstoreys.text.toString())
        currentUserDb.child("Date_Constructed").setValue(dateconstructed.text.toString())
        currentUserDb.child("Date_Completed").setValue(datecompleted.text.toString())
        currentUserDb.child("Date_Occupied").setValue(dateoccupied.text.toString())
        currentUserDb.child("AreaOfGroundFloor").setValue(areaofgroundfloor.text.toString())
        //end of data

        val intent = Intent(this, Application4::class.java)
        intent.putExtra("id_number", id_number)
        intent.putExtra("structures", structures)
        startActivity(intent)
        finish()
    }
    private fun validateForm(): Boolean {
        var valid = true

        val Structuretype = structuretype.text.toString()
        if (TextUtils.isEmpty(Structuretype)) {
            structuretype.error = "Required."
            valid = false
        } else {
            structuretype.error = null
        }
        val Buildingclasification = buildingclasification.text.toString()
        if (TextUtils.isEmpty(Buildingclasification)) {
            buildingclasification.error = "Required."
            valid = false
        } else {
            buildingclasification.error = null
        }
        val Buildingpermit = buildingpermit.text.toString()
        if (TextUtils.isEmpty(Buildingpermit)) {
            buildingpermit.error = "Required."
            valid = false
        } else {
            buildingpermit.error = null
        }
        val Buildingage = buildingage.text.toString()
        if (TextUtils.isEmpty(Buildingage)) {
            buildingage.error = "Required."
            valid = false
        } else {
            buildingage.error = null
        }
        val Numberofstoreys = numberofstoreys.text.toString()
        if (TextUtils.isEmpty(Numberofstoreys)) {
            numberofstoreys.error = "Required."
            valid = false
        } else {
            numberofstoreys.error = null
        }
        val Dateconstructed = dateconstructed.text.toString()
        if (TextUtils.isEmpty(Dateconstructed)) {
            dateconstructed.error = "Required."
            valid = false
        } else {
            dateconstructed.error = null
        }
        val Datecompleted = datecompleted.text.toString()
        if (TextUtils.isEmpty(Datecompleted)) {
            datecompleted.error = "Required."
            valid = false
        } else {
            datecompleted.error = null
        }
        val Dateoccupied = dateoccupied.text.toString()
        if (TextUtils.isEmpty(Dateoccupied)) {
            dateoccupied.error = "Required."
            valid = false
        } else {
            dateoccupied.error = null
        }
        val Areaofgroundfloor = areaofgroundfloor.text.toString()
        if (TextUtils.isEmpty(Areaofgroundfloor)) {
            areaofgroundfloor.error = "Required."
            valid = false
        } else {
            areaofgroundfloor.error = null
        }

        return valid
    }

    private fun singOut(){
        auth = FirebaseAuth.getInstance()
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
