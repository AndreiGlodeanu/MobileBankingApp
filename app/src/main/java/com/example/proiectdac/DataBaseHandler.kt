package com.example.proiectdac

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME="AndroiProiectdDB"
//users table
val TABLE_NAME_USERS="Users"
val COL_NAME="name"
val COL_PASSWORD="password"
val COL_PHONE = "phone"
val COL_ID="id"

//card info table
val TABLE_NAME_CARD = "CardInfo"
val COL_NUMBER_CARD = "number"
val COL_CARD_HOLDER = "name"
val COL_CARD_EXPIRATION_MONTH = "month"
val COL_CARD_EXPIRATION_YEAR = "year"
val COL_CVV = "cvv"
val COL_ID_USER = "id"

//current account table
val TABLE_NAME_ACCOUNT = "CurrentAccount"
val COL_MONEY_AMOUNT = "moneyAmount"
val COL_CURRENCY = "currency"




class DataBaseHandler(var context: Context) :SQLiteOpenHelper(context, DATABASE_NAME,null,1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME_USERS + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " VARCHAR(256)," +
                COL_PASSWORD + " VARCHAR(256)," +
                COL_PHONE + " VARCHAR(256)" + ")"

        val createTableCardInfo = "CREATE TABLE " + TABLE_NAME_CARD + " (" +
                COL_ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NUMBER_CARD + " VARCHAR(256)," +
                COL_CARD_HOLDER + " VARCHAR(256)," +
                COL_CARD_EXPIRATION_MONTH + " VARCHAR(256)," +
                COL_CARD_EXPIRATION_YEAR + " VARCHAR(256)," +
                COL_CVV + " VARCHAR(256)" + ")"

        val createTableCurrentAccount = "CREATE TABLE" + TABLE_NAME_ACCOUNT + " (" +
                COL_ID_USER + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_MONEY_AMOUNT + " INTEGER," +
                COL_CURRENCY + " INTEGER" + ")"

        p0?.execSQL((createTableCurrentAccount))
        p0?.execSQL(createTable)
        p0?.execSQL(createTableCardInfo)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun insertDataCard(cardInfo:CardInfo) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_ID_USER, cardInfo.userId)
        cv.put(COL_NUMBER_CARD,cardInfo.cardNumber )
        cv.put(COL_CARD_HOLDER, cardInfo.cardHolder)
        cv.put(COL_CARD_EXPIRATION_MONTH, cardInfo.cardExpirationMonth)
        cv.put(COL_CARD_EXPIRATION_YEAR, cardInfo.cardExpirationYear)
        cv.put(COL_CVV, cardInfo.CVV)

        var result = db.insert(TABLE_NAME_CARD, null, cv);
        if (result == -1.toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Succes", Toast.LENGTH_SHORT).show()

        }
    }

    fun insertDataUser(user: User) {
        val db = this.writableDatabase

        //insert in user table
        var cv = ContentValues()
        cv.put(COL_NAME, user.name);
        cv.put(COL_PASSWORD, user.password);
        cv.put(COL_PHONE, user.phone);
        var result = db.insert(TABLE_NAME_USERS, null, cv);

        //insert in account info table
        var currentAccount:CurrentAccount
        var cv2 = ContentValues()
        cv2.put(COL_MONEY_AMOUNT, 0)
        cv2.put(COL_CURRENCY, "RON")
        var result2 = db.insert(TABLE_NAME_ACCOUNT, null, cv);

        if (result == (-1).toLong() || result2 == (-1).toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Succes", Toast.LENGTH_SHORT).show()

        }
    }


    @SuppressLint("Range")
    fun GetUserIdByName(name:String): String{
        val selectData="SELECT id FROM Users WHERE name = $name"
        val db = this.readableDatabase
        val cursor: Cursor?
        try {
            cursor = db.rawQuery(selectData, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectData)
            return toString()
        }
        return cursor.getString(cursor.getColumnIndex("id"))
    }
    @SuppressLint("Range")
    fun ReadAccountData(userId: String): String {
        val selectData = "SELECT moneyAmount FROM CurrentAccount WHERE id = userId"
        val db = this.readableDatabase
        val cursor: Cursor?
        try {
            cursor = db.rawQuery(selectData, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectData)
            return toString()
        }
        return cursor.getInt(cursor.getColumnIndex("moneyAmount")).toString()
    }


    @SuppressLint("Range")
    fun logInUser():ArrayList<User>{
        val usrList:ArrayList<User> = ArrayList()
        val selectAllUsersQuery = "SELECT * FROM Users"
        val db = this.readableDatabase

        val cursor: Cursor?

        try{
            cursor = db.rawQuery(selectAllUsersQuery,null)
        }catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectAllUsersQuery)
            return ArrayList()
        }
        var id: Int
        var name: String
        var password: String

        if(cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("name"))
                password = cursor.getString(cursor.getColumnIndex("password"))

                val usr = User(id,name,password)
                usrList.add(usr)
            }while(cursor.moveToNext())
        }
return usrList
    }


}