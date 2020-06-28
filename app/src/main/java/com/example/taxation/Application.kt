package com.example.taxation

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import com.sti.taxation.models.User
import kotlinx.android.synthetic.main.activity_application.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest


@Suppress("DEPRECATION")
class Application : AppCompatActivity() {

    private lateinit var mDatabase: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application)
        auth = FirebaseAuth.getInstance()

        arpnumber.text = intent.getStringExtra("AppointmentID")
        owner.text = intent.getStringExtra("Name")
        address_owner.text = intent.getStringExtra("Address")

        upload.setOnClickListener {
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED){
                    val permissions = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE);
                    requestPermissions(permissions, PERMISION_CODE);

                }
                else{
                    pickImageFromGallery();

                }
            }
            else{
                pickImageFromGallery();

            }
        }

        btn_next.setOnClickListener {
            submitForm()
        }

        mDatabase = FirebaseDatabase.getInstance().getReference("Users")

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
        // [END post_value_event_listener]
        btn_logout.setOnClickListener {
            singOut()
        }
    }
    companion object{
        private val IMAGE_PICK_CODE = 1000;
        private val PERMISION_CODE = 1001;
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            PERMISION_CODE ->{
                if(grantResults.size>0 && grantResults[0]==
                    PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery()
                }
                else{
                    Toast.makeText(this,"Permission denied",Toast.LENGTH_SHORT).show()
                }
            }

        }

    }


    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)

    }
    var selectedPhotoUri: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode== IMAGE_PICK_CODE){
            selectedPhotoUri = data?.data

            Picasso.get().load(data?.data).fit().into(upload)
        }

    }

    fun submitForm() {
        if (!validateForm()) {
            return
        }
        val day = intent.getStringExtra("day")
        val AppointID = intent.getStringExtra("AppointmentID")
        val arp = arpnumber.text.toString()
        mDatabase = FirebaseDatabase.getInstance().getReference("Property Assessment").child(arp)


        val file = mDatabase.key.toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$file")
        ref.putFile(selectedPhotoUri!!).addOnSuccessListener {

            ref.downloadUrl.addOnSuccessListener {
                mDatabase.child("image").setValue(it.toString())
            }
        }
        mDatabase.child("status").setValue("Pending")
        mDatabase.child("arpNumber").setValue(arp)
        mDatabase.child("tin").setValue(pinnumber.text.toString())
        mDatabase.child("owner").setValue(owner.text.toString())
        mDatabase.child("ownerAddress").setValue(address_owner.text.toString())
        mDatabase.child("ownerTelNo").setValue(tellNumber_owner.text.toString())
        mDatabase.child("location").setValue(location.text.toString())
        mDatabase.child("street").setValue(street.text.toString())
        mDatabase.child("barangay").setValue(brgy.text.toString())

        val intent = Intent(this, Application2::class.java)
        intent.putExtra("id_number", arp)
        intent.putExtra("day",day)
        intent.putExtra("AppointmentID", AppointID)
        startActivity(intent)
        finish()
    }

    @SuppressLint("WrongConstant")
    private fun validateForm(): Boolean {
        var valid = true

        if (selectedPhotoUri == null){
            Toast.makeText(this, "Please Upload a Photo",1)
            valid = false
        }

        val arpNumber = arpnumber.text.toString()
        if (TextUtils.isEmpty(arpNumber)) {
            arpnumber.error = "Required."
            valid = false
        } else {
            arpnumber.error = null
        }

        val pinNumber = pinnumber.text.toString()
        if (TextUtils.isEmpty(pinNumber)) {
            pinnumber.error = "Required."
            valid = false
        } else {
            pinnumber.error = null
        }

        val Owner = owner.text.toString()
        if (TextUtils.isEmpty(Owner)) {
            owner.error = "Required."
            valid = false
        } else {
            owner.error = null
        }

        val addi_Owner = address_owner.text.toString()
        if (TextUtils.isEmpty(addi_Owner)) {
            address_owner.error = "Required."
            valid = false
        } else {
            address_owner.error = null
        }

        val tellNumberOwner = tellNumber_owner.text.toString()
        if (TextUtils.isEmpty(tellNumberOwner)) {
            tellNumber_owner.error = "Required."
            valid = false
        } else {
            tellNumber_owner.error = null
        }

        val Location = location.text.toString()
        if (TextUtils.isEmpty(Location)) {
            location.error = "Required."
            valid = false
        } else {
            location.error = null
        }
        val Street = street.text.toString()
        if (TextUtils.isEmpty(Street)) {
            street.error = "Required."
            valid = false
        } else {
            street.error = null
        }
        val Barangay = brgy.text.toString()
        if (TextUtils.isEmpty(Barangay)) {
            brgy.error = "Required."
            valid = false
        } else {
            brgy.error = null
        }

        return valid
    }

    private fun singOut(){
        auth = FirebaseAuth.getInstance()
        auth.signOut()
        updateUI(null)
    }
    override fun onBackPressed() {
        super.onBackPressed()
        return
    }
    private fun updateUI(user: FirebaseUser?) {
        if (user == null) {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

    }

}
