package com.example.taxation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_book.*
import java.text.SimpleDateFormat
import java.util.*

class Book : AppCompatActivity() {

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        val mDatabase = FirebaseDatabase.getInstance().getReference("Appointment")
        var today = ""

        back_book.setOnClickListener { finish() }

        val calendar = Calendar.getInstance()
        calendar.time = Date()

        val formatDay = SimpleDateFormat("EEE")
        val day = formatDay.format(calendar.time)
        when (day) {
            "Mon" -> {
                Sched(7)
            }
            "Tue" -> {
                Sched(6)
            }
            "Wed" -> {
                Sched(5)
            }
            "Thu" -> {
                Sched(4)
            }
            "Fri" -> {
                Sched(3)
            }
            else -> {
                schedules.visibility = View.GONE
            }
        }

        sched1.setOnClickListener{
            book_result.text = sched1.text
            schedules.visibility = View.GONE
            form.visibility = View.VISIBLE
            today = "Monday"
        }
        sched2.setOnClickListener{
            book_result.text = sched2.text
            form.visibility = View.VISIBLE
            schedules.visibility = View.GONE
            today = "Tuesday"
        }
        sched3.setOnClickListener{
            book_result.text = sched3.text
            form.visibility = View.VISIBLE
            schedules.visibility = View.GONE
            today = "Wednesday"
        }
        sched4.setOnClickListener{
            book_result.text = sched4.text
            form.visibility = View.VISIBLE
            schedules.visibility = View.GONE
            today = "Thursday"
        }
        sched5.setOnClickListener{
            book_result.text = sched5.text
            form.visibility = View.VISIBLE
            schedules.visibility = View.GONE
            today = "Friday"
        }

        form_submit.setOnClickListener {

            if (name.text.isNotEmpty() and address.text.isNotEmpty()) {

                val id = mDatabase.child(today).push()
                id.child("TransactionId").setValue(id.key.toString())
                id.child("Name").setValue(name.text.toString())
                id.child("Address").setValue(address.text.toString())
                id.child("Schedule").setValue(book_result.text.toString())

                form.visibility = View.GONE
                appointment.visibility = View.VISIBLE

                transactionId.text = id.key.toString()
                appointmentName.text = name.text
                appointmentAddress.text = address.text
                appointment_schedule.text = book_result.text

                book_result.text = "SUCCESS"

            }else{
                name.setError("Please Enter Name")
                address.setError("Please Enter Address")
            }


        }





    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    fun Sched(day: Int){
        val mDatabase = FirebaseDatabase.getInstance().getReference("Appointment")

        val day = day


        val dateFormat = SimpleDateFormat("dd/MMMM/yyyy")
        val formatDay = SimpleDateFormat("EEE")

        val calendar = Calendar.getInstance()
        calendar.time = Date()
        date.text = formatDay.format(calendar.time) + ", " + dateFormat.format(calendar.time)

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val count = dataSnapshot.childrenCount
                if(count < 4){
                    calendar.add(Calendar.DATE, day)
                    sched1.text = dateFormat.format(calendar.time)
                }else{
                    sched1.text = "Fully Book"
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        val postListener2 = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val count = dataSnapshot.childrenCount
                if(count < 4){
                    calendar.add(Calendar.DATE, 1)
                    sched2.text = dateFormat.format(calendar.time)

                }else{
                    sched2.text = "Fully Book"
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        val postListener3 = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val count = dataSnapshot.childrenCount
                if(count < 4){
                    calendar.add(Calendar.DATE, 1)
                    sched3.text = dateFormat.format(calendar.time)

                }else{
                    sched3.text = "Fully Book"
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        val postListener4 = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val count = dataSnapshot.childrenCount
                if(count < 4){
                    calendar.add(Calendar.DATE, 1)
                    sched4.text = dateFormat.format(calendar.time)

                }else{
                    sched4.text = "Fully Book"
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        val postListener5 = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val count = dataSnapshot.childrenCount
                if(count < 4){
                    calendar.add(Calendar.DATE, 1)
                    sched5.text = dateFormat.format(calendar.time)

                }else{
                    sched5.text = "Fully Book"
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }

        mDatabase.child("Monday").addValueEventListener(postListener)
        mDatabase.child("Tuesday").addValueEventListener(postListener2)
        mDatabase.child("Wednesday").addValueEventListener(postListener3)
        mDatabase.child("Thursday").addValueEventListener(postListener4)
        mDatabase.child("Friday").addValueEventListener(postListener5)

        availability.visibility = View.GONE

    }
}