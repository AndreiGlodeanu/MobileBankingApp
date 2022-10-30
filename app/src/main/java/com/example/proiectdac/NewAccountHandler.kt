package com.example.proiectdac

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.security_page_3.*

class NewAccountHandler : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.security_page_3)

        btnCreateAccount.setOnClickListener {
            val intent = Intent(this, CardInfoHandler::class.java)
            startActivity(intent)
        }
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true);
    }
}