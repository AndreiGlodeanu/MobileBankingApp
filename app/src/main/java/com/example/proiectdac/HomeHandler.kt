package com.example.proiectdac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class HomeHandler : AppCompatActivity() {
    private lateinit var dataBaseHandler: DataBaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dataBaseHandler= DataBaseHandler(this)
        val userData:ExistingAccountHandler = ExistingAccountHandler()
//Toast.makeText(this,userData.getUserName(),Toast.LENGTH_SHORT).show()

      //  textViewAmount.text = dataBaseHandler.ReadAccountData(dataBaseHandler.GetUserIdByName(userData.getUserName()))
    }
}