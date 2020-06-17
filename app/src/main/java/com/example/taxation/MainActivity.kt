package com.example.taxation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_Assessor.setOnClickListener {
            val intent = Intent(this, Assessor::class.java)
            startActivity(intent)
        }
        btn_Admin.setOnClickListener {
            val intent = Intent(this, admin::class.java)
            startActivity(intent)
        }
        btn_Customer.setOnClickListener{
            val intent = Intent(this, customer::class.java)
            startActivity(intent)
        }


    }

}
