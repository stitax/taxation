package com.example.taxation

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.view.View.OnTouchListener
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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

        click_agreement.setOnClickListener{
            val window = PopupWindow(this)
            val view = layoutInflater.inflate(R.layout.user_agreement, null)
            window.contentView = view
            val textView = view.findViewById<TextView>(R.id.user_agreement)
            textView.setOnClickListener {
                window.dismiss()
            }
            window.showAtLocation(click_agreement,1,0,0)
        }
        val mDatabase = FirebaseDatabase.getInstance().getReference("Appointment")
        var today = ""

        back_book.setOnClickListener { finish() }

        val calendar = Calendar.getInstance()
        calendar.time = Date()
        val current = SimpleDateFormat("EEE, dd/MMMM/yyyy")
        date.text = current.format(calendar.time)

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

            if (name.text.isNotEmpty() and address.text.isNotEmpty() and book_arpNumber.text.isNotEmpty()) {

                val id = mDatabase.child(today).child(book_arpNumber.text.toString())
                id.child("Arp_Number").setValue(book_arpNumber.text.toString())
                id.child("Name").setValue(name.text.toString())
                id.child("Address").setValue(address.text.toString())
                id.child("Schedule").setValue(book_result.text.toString())

                form.visibility = View.GONE
                appointment.visibility = View.VISIBLE

                transactionId.text = book_arpNumber.text
                appointmentName.text = name.text
                appointmentAddress.text = address.text
                appointment_schedule.text = book_result.text

                book_result.text = "SUCCESS"

            }else{
                name.setError("Please Enter Name")
                address.setError("Please Enter Address")
                book_arpNumber.setError("Please Enter Your Arp Number")
            }

        }

    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    fun Sched(day: Int){
        val mDatabase = FirebaseDatabase.getInstance().getReference("Appointment")

        val day = day


        val dateFormat = SimpleDateFormat("dd/MMMM/yyyy")

        val calendar = Calendar.getInstance()
        calendar.time = Date()

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                calendar.add(Calendar.DATE, day)

                val count = dataSnapshot.childrenCount
                if(count < 4){
                    sched1.text = dateFormat.format(calendar.time)
                }else{
                    sched1.text = "FullyBooked"
                    sched1.setBackgroundResource(R.drawable.delete)
                    sched1.isEnabled = false

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
                calendar.add(Calendar.DATE, 1)
                val count = dataSnapshot.childrenCount
                if(count < 4){
                    sched2.text = dateFormat.format(calendar.time)

                }else{
                    sched2.text = "FullyBooked"
                    sched2.setBackgroundResource(R.drawable.delete)
                    sched2.isEnabled = false

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
                calendar.add(Calendar.DATE, 1)
                val count = dataSnapshot.childrenCount
                if(count < 4){
                    sched3.text = dateFormat.format(calendar.time)

                }else{
                    sched3.text = "FullyBooked"
                    sched3.isEnabled = false
                    sched3.setBackgroundResource(R.drawable.delete)
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
                calendar.add(Calendar.DATE, 1)

                val count = dataSnapshot.childrenCount
                if(count < 4){
                    sched4.text = dateFormat.format(calendar.time)

                }else{
                    sched4.text = "FullyBooked"
                    sched4.setBackgroundResource(R.drawable.delete)
                    sched4.isEnabled = false

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
                calendar.add(Calendar.DATE, 1)

                val count = dataSnapshot.childrenCount
                if(count < 4){
                    sched5.text = dateFormat.format(calendar.time)

                }else{
                    sched5.text = "FullyBooked"
                    sched5.setBackgroundResource(R.drawable.delete)
                    sched5.isEnabled = false
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