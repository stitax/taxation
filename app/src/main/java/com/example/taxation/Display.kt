package com.example.taxation

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import com.example.taxation.models.Display_Assess
import com.example.taxation.models.Display_Information
import com.example.taxation.models.Display_Structure
import com.example.taxation.models.Display_property
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_display.*
import kotlinx.android.synthetic.main.activity_transaction_id.*

class Display : AppCompatActivity() {

    lateinit var mDatabase: DatabaseReference
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        val status = intent.getStringExtra("status")


        //Get Data Property Assessment
        propertyValue()
        //End

        //Structure Value
        structureValue()
        structureValue1()
        structureValue2()
        structureValue3()
        structureValue4()
        structureValue5()
        structureValue6()
        structureValue7()
        structureValue8()
        structureValue9()
        structureValue10()
        structureValue11()
        structureValue12()
        structureValue13()
        structureValue14()
        structureValue15()
        structureValue16()
        //End

        //Information Value
        informationValue()
        //end

        //Assessed Value
        asseessValue()
      //end

        if (status == "") {
            btnApprove.visibility = View.GONE
            cancel_status.visibility = View.GONE
        } else if(status == "Approved") {
           btnApprove.visibility = View.GONE
            status_display.text = "APPROVED"
        }

        val arp = intent.getStringExtra("Arp number")

        mDatabase = FirebaseDatabase.getInstance().getReference("Property Assessment").child(arp)

        btnApprove.setOnClickListener{
            mDatabase.child("status").setValue("Approved")
            finish()
        }
        cancel_status.setOnClickListener {
            mDatabase = FirebaseDatabase.getInstance().getReference("Property Assessment").child(arp)
            mDatabase.removeValue()
            mDatabase = FirebaseDatabase.getInstance().getReference("Structure Characteristics").child(arp)
            mDatabase.removeValue()
            mDatabase = FirebaseDatabase.getInstance().getReference("Value Computation").child(arp)
            mDatabase.removeValue()
            mDatabase = FirebaseDatabase.getInstance().getReference("Property Information").child(arp)
            mDatabase.removeValue()
            finish()
        }
    }

    fun structureValue(){
        val arp = intent.getStringExtra("Arp number")
        val structure = FirebaseDatabase.getInstance().getReference("Structure Characteristics")
            .child(arp).child("Foundation")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val data = dataSnapshot.getValue(Display_Structure::class.java)
                // ...
                display_foundation1.text = data?.first_Floor
                display_foundation2.text = data?.secondFloor

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("DISPLAY", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        structure.addValueEventListener(postListener)
    }
    fun structureValue1(){
        val arp = intent.getStringExtra("Arp number")
        val structure = FirebaseDatabase.getInstance().getReference("Structure Characteristics")
            .child(arp).child("Columns")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val data = dataSnapshot.getValue(Display_Structure::class.java)
                // ...
                display_column1.text = data?.first_Floor.toString()
                display_column2.text = data?.secondFloor.toString()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("DISPLAY", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        structure.addValueEventListener(postListener)
    }
   fun structureValue2(){
        val arp = intent.getStringExtra("Arp number")
        val structure = FirebaseDatabase.getInstance().getReference("Structure Characteristics")
            .child(arp).child("Beans")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val data = dataSnapshot.getValue(Display_Structure::class.java)
                // ...
                display_beans1.text = data?.first_Floor.toString()
                display_beans2.text = data?.secondFloor.toString()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("DISPLAY", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        structure.addValueEventListener(postListener)
    }
   fun structureValue3(){
        val arp = intent.getStringExtra("Arp number")
        val structure = FirebaseDatabase.getInstance().getReference("Structure Characteristics")
            .child(arp).child("Truss Farming")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val data = dataSnapshot.getValue(Display_Structure::class.java)
                // ...
                display_trus1.text = data?.first_Floor.toString()
                display_trus2.text = data?.secondFloor.toString()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("DISPLAY", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        structure.addValueEventListener(postListener)
    }

   fun structureValue4(){
        val arp = intent.getStringExtra("Arp number")
        val structure = FirebaseDatabase.getInstance().getReference("Structure Characteristics")
            .child(arp).child("Roof")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val data = dataSnapshot.getValue(Display_Structure::class.java)
                // ...
                display_roof1.text = data?.first_Floor.toString()
                display_roof2.text = data?.secondFloor.toString()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("DISPLAY", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        structure.addValueEventListener(postListener)
    }

   fun structureValue5(){
        val arp = intent.getStringExtra("Arp number")
        val structure = FirebaseDatabase.getInstance().getReference("Structure Characteristics")
            .child(arp).child("Exterior Walls")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val data = dataSnapshot.getValue(Display_Structure::class.java)
                // ...
                display_exterior1.text = data?.first_Floor.toString()
                display_exterior2.text = data?.secondFloor.toString()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("DISPLAY", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        structure.addValueEventListener(postListener)
    }
  fun structureValue6(){
        val arp = intent.getStringExtra("Arp number")
        val structure = FirebaseDatabase.getInstance().getReference("Structure Characteristics")
            .child(arp).child("Flooring")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val data = dataSnapshot.getValue(Display_Structure::class.java)
                // ...
                display_flooring1.text = data?.first_Floor.toString()
                display_flooring2.text = data?.secondFloor.toString()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("DISPLAY", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        structure.addValueEventListener(postListener)
    }

  fun structureValue7(){
        val arp = intent.getStringExtra("Arp number")
        val structure = FirebaseDatabase.getInstance().getReference("Structure Characteristics")
            .child(arp).child("Doors")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val data = dataSnapshot.getValue(Display_Structure::class.java)
                // ...
                display_door1.text = data?.first_Floor.toString()
                display_door2.text = data?.secondFloor.toString()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("DISPLAY", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        structure.addValueEventListener(postListener)
    }

  fun structureValue8(){
        val arp = intent.getStringExtra("Arp number")
        val structure = FirebaseDatabase.getInstance().getReference("Structure Characteristics")
            .child(arp).child("Ceiling")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val data = dataSnapshot.getValue(Display_Structure::class.java)
                // ...
                display_ceiling1.text = data?.first_Floor.toString()
                display_ceiling2.text = data?.secondFloor.toString()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("DISPLAY", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        structure.addValueEventListener(postListener)
    }

  fun structureValue9(){
        val arp = intent.getStringExtra("Arp number")
        val structure = FirebaseDatabase.getInstance().getReference("Structure Characteristics")
            .child(arp).child("Windows")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val data = dataSnapshot.getValue(Display_Structure::class.java)
                // ...
                display_windows1.text = data?.first_Floor.toString()
                display_windows2.text = data?.secondFloor.toString()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("DISPLAY", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        structure.addValueEventListener(postListener)
    }

  fun structureValue10(){
        val arp = intent.getStringExtra("Arp number")
        val structure = FirebaseDatabase.getInstance().getReference("Structure Characteristics")
            .child(arp).child("Stairs")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val data = dataSnapshot.getValue(Display_Structure::class.java)
                // ...
                display_stairs1.text = data?.first_Floor.toString()
                display_stairs2.text = data?.secondFloor.toString()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("DISPLAY", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        structure.addValueEventListener(postListener)
    }

  fun structureValue11(){
        val arp = intent.getStringExtra("Arp number")
        val structure = FirebaseDatabase.getInstance().getReference("Structure Characteristics")
            .child(arp).child("Partition")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val data = dataSnapshot.getValue(Display_Structure::class.java)
                // ...
                display_partition1.text = data?.first_Floor.toString()
                display_partition2.text = data?.secondFloor.toString()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("DISPLAY", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        structure.addValueEventListener(postListener)
    }

  fun structureValue12(){
        val arp = intent.getStringExtra("Arp number")
        val structure = FirebaseDatabase.getInstance().getReference("Structure Characteristics")
            .child(arp).child("Wall Finish")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val data = dataSnapshot.getValue(Display_Structure::class.java)
                // ...
                display_wall_finish1.text = data?.first_Floor.toString()
                display_wall_finish2.text = data?.secondFloor.toString()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("DISPLAY", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        structure.addValueEventListener(postListener)
    }

  fun structureValue13(){
        val arp = intent.getStringExtra("Arp number")
        val structure = FirebaseDatabase.getInstance().getReference("Structure Characteristics")
            .child(arp).child("Electrical")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val data = dataSnapshot.getValue(Display_Structure::class.java)
                // ...
                display_electrical1.text = data?.first_Floor.toString()
                display_electrical2.text = data?.secondFloor.toString()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("DISPLAY", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        structure.addValueEventListener(postListener)
    }

  fun structureValue14(){
        val arp = intent.getStringExtra("Arp number")
        val structure = FirebaseDatabase.getInstance().getReference("Structure Characteristics")
            .child(arp).child("Toilet & Bath")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val data = dataSnapshot.getValue(Display_Structure::class.java)
                // ...
                display_toilet1.text = data?.first_Floor.toString()
                display_toilet2.text = data?.secondFloor.toString()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("DISPLAY", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        structure.addValueEventListener(postListener)
    }

  fun structureValue15(){
        val arp = intent.getStringExtra("Arp number")
        val structure = FirebaseDatabase.getInstance().getReference("Structure Characteristics")
            .child(arp).child("Plumbing Sewer")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val data = dataSnapshot.getValue(Display_Structure::class.java)
                // ...
                display_plumbing1.text = data?.first_Floor.toString()
                display_plumbing2.text = data?.secondFloor.toString()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("DISPLAY", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        structure.addValueEventListener(postListener)
    }

  fun structureValue16(){
        val arp = intent.getStringExtra("Arp number")
        val structure = FirebaseDatabase.getInstance().getReference("Structure Characteristics")
            .child(arp).child("Fixtures")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val data = dataSnapshot.getValue(Display_Structure::class.java)
                // ...
                display_fixture1.text = data?.first_Floor.toString()
                display_fixture2.text = data?.secondFloor.toString()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("DISPLAY", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        structure.addValueEventListener(postListener)
    }

    fun propertyValue(){
        val arp = intent.getStringExtra("Arp number")

        val property = FirebaseDatabase.getInstance().getReference("Property Assessment").child(arp)
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val data = dataSnapshot.getValue(Display_property::class.java)
                // ...
                display_arp.text = data?.arpNumber
                Picasso.get().load(data?.image).fit().into(img_display)
                display_tin.text = data?.tin
                display_owner.text = data?.owner
                display_owner_address.text = data?.ownerAddress
                display_owner_tel.text = data?.ownerTelNo
                display_admin.text = data?.occupant
                display_admin_address.text = data?.occupantAddress
                display_admin_tel.text = data?.occupantTelNo
                display_bldg_location.text = data?.location
                display_street.text = data?.street
                display_barangay.text = data?.barangay
                tax_payable.text = data?.tax_payable.toString()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("DISPLAY", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        property.addValueEventListener(postListener)
    }

    fun informationValue(){
        val arp = intent.getStringExtra("Arp number")

        val property = FirebaseDatabase.getInstance().getReference("Property Information").child(arp)
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val data = dataSnapshot.getValue(Display_Information::class.java)
                // ...
                type.text = data?.Structural_Type
                classification.text = data?.Bldg_Classification
                permit.text = data?.Bldg_Permit
                age.text = data?.Bldg_Age
                stories.text = data?.NoOfStoreys
                date_occupied.text = data?.Date_Occupied
                date_completed.text = data?.Date_Completed
                date_constructed.text = data?.Date_Constructed
                total_area.text = data?.AreaOfGroundFloor
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("DISPLAY", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        property.addValueEventListener(postListener)
    }
    fun asseessValue(){
        val arp = intent.getStringExtra("Arp number")

        val property = FirebaseDatabase.getInstance().getReference("Value Computation")
            .child(arp).child("Assessed Value")
       val market = FirebaseDatabase.getInstance().getReference("Value Computation")
            .child(arp).child("Market Value")
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val data = dataSnapshot.getValue(Display_Assess::class.java)
                // ...
                assess_building.text = data?.Building
                assess_land.text = data?.Land
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("DISPLAY", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        val postListener1 = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val data = dataSnapshot.getValue(Display_Assess::class.java)
                // ...
                market_building.text = data?.Building
                market_land.text = data?.Land
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("DISPLAY", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        property.addValueEventListener(postListener)
        market.addValueEventListener(postListener1)
    }
}