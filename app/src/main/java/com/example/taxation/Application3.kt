package com.example.taxation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
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
        var structure = "Building"
        var building = "Industrial"
        val structure_type = resources.getStringArray(R.array.structure_type)
        val spinner = findViewById<Spinner>(R.id.structuretype)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, structure_type)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    when (position) {
                        0 -> {
                            // Notify the selected item text

                            structure = "Building"
                        }
                        1 -> {
                            // Notify the selected item text
                            structure = "House"
                        }
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                    structure = "Building"
                }
            }
        }
        val building1 = resources.getStringArray(R.array.building_classification)
        val spinner1 = findViewById<Spinner>(R.id.buildingclasification)
        if (spinner1 != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, building1)
            spinner1.adapter = adapter

            spinner1.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {

                    when (position) {
                        0 -> {
                            // Notify the selected item text
                            building = "Industrial"
                        }

                        1 -> {
                            // Notify the selected item text
                            building = "Commercial"
                        }

                        2 -> {
                            // Notify the selected item text
                            building = "Residential"
                        }

                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                    building = "Industrial"

                }
            }
        }

        btn_application3.setOnClickListener {
            val arp = intent.getStringExtra("id_number")
            mDatabase = FirebaseDatabase.getInstance().getReference("Property Information").child(arp)
            mDatabase.child("Structural_Type").setValue(structure)
            mDatabase.child("Bldg_Classification").setValue(building)


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
        val day = intent.getStringExtra("day")
        val AppointID = intent.getStringExtra("AppointmentID")
        val id_number = intent.getStringExtra("id_number")
        val structures = intent.getStringExtra("structures")
        //end of data
        mDatabase = FirebaseDatabase.getInstance().getReference("Property Information")
        val currentUserDb = mDatabase.child(id_number)

        currentUserDb.child("Bldg_Permit").setValue(buildingpermit.text.toString())
        currentUserDb.child("Bldg_Age").setValue(buildingage.text.toString())
        currentUserDb.child("Date_Constructed").setValue(dateconstructed.text.toString())
        currentUserDb.child("Date_Completed").setValue(datecompleted.text.toString())
        currentUserDb.child("Date_Occupied").setValue(dateoccupied.text.toString())
        currentUserDb.child("AreaOfGroundFloor").setValue(areaofgroundfloor.text.toString())
        //end of data

        val intent = Intent(this, Application4::class.java)
        intent.putExtra("id_number", id_number)
        intent.putExtra("day",day)
        intent.putExtra("AppointmentID", AppointID)
        intent.putExtra("structures", structures)
        startActivity(intent)
        finish()
    }
    private fun validateForm(): Boolean {
        var valid = true

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
