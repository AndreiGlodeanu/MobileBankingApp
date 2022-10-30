package com.example.proiectdac

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.security_page_3.*
import kotlinx.android.synthetic.main.security_page_4.*

class CardInfoHandler : AppCompatActivity() {

    private lateinit var dataBaseHandler: DataBaseHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.security_page_4)

        dataBaseHandler = DataBaseHandler(this)
        btnComplete.setOnClickListener{
            addCardInfo()

        }
    }

    private fun addCardInfo()
    {
        val cardNumber=etNrCard.text.toString()
        val cardHolderName = etHolderName.text.toString()
        val cardLastMonth = etLastMonth.text.toString()
        val cardLastYear = etLastYear.text.toString()
        val cardCVV = etCVV.text.toString()

        if(cardNumber.isEmpty() || cardHolderName.isEmpty() || cardLastMonth.isEmpty()
            || cardLastYear.isEmpty() || cardCVV.isEmpty())
        {
            Toast.makeText(this,"Please fill all the fields", Toast.LENGTH_SHORT).show()
        }
        if(cardNumber.length!=16)
            Toast.makeText(this,"Wrong card number, please check", Toast.LENGTH_SHORT).show()
            if(cardCVV.length!=3)
                Toast.makeText(this,"Wrong CVV number, please check", Toast.LENGTH_SHORT).show()
                if(cardLastYear.toInt() <2022)
                    Toast.makeText(this,"Your card has expired", Toast.LENGTH_SHORT).show()
        else
        {
            val card = CardInfo(cardNumber = cardNumber,cardHolder = cardHolderName,cardExpirationMonth = cardLastMonth,cardExpirationYear = cardLastYear,CVV = cardCVV)
            val status  = dataBaseHandler.insertDataCard(card)
            val intent = Intent(this, HomeHandler::class.java)
            startActivity(intent)
        }
    }

}